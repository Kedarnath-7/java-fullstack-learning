# Implementation Notes

## loadBooks()

### 1. Method Purpose
`loadBooks(List<String> records)` reads raw text records, validates each one, converts the
valid ones into immutable `Book` objects, and stores them in the `books` map keyed by `BOOK_ID`.
It is the entry point that "cleans" messy input data so that every other analytics method can
safely assume the map contains only valid, de-duplicated books.

Each record has the format:

```
BOOK_ID|TITLE|AUTHOR|CATEGORY|BORROW_COUNT|RATING
```

### 2. Concepts Used / Data Structures Used
- **HashMap (`Map<String, Book>`)** — stores the final books with `BOOK_ID` as the unique key.
  Gives O(1) average insertion and lookup, which matters for "millions of transactions".
- **Stream API** — used to process the list in a declarative pipeline (filter → map → collect)
  instead of manual loops.
- **Comparator** — encodes the "which duplicate wins" rule in one reusable object, chained with
  `thenComparing`.
- **Optional** — guards against a `null` input list (`Optional.ofNullable(...).orElseGet(...)`).
- **Map.merge()** — handles the "insert new or resolve duplicate" decision in a single step.
- **Immutable object (`Book`)** — once created a `Book` never changes, so stored data stays safe.
- **String manipulation** — `split("\\|", -1)` and `trim()` for parsing and cleaning fields.

### 3. Step-by-Step Execution Flow
1. If the input list is `null`, treat it as an empty list (no crash).
2. Stream over the records, skipping any `null` lines.
3. Split each line on the `|` character. The limit `-1` is used so that **trailing/empty fields
   are preserved** (e.g. `B204||Author|...` still produces an empty title slot instead of being
   silently dropped).
4. Keep only lines that have **exactly 6 fields** — anything else is an invalid format.
5. For each line, validate every field:
   - reject if any field is empty after `trim()`,
   - parse `borrowCount` as `int` and `rating` as `double`,
   - reject if parsing throws `NumberFormatException`,
   - reject if `borrowCount < 0`, `rating < 0`, or `rating > 5`.
6. Valid lines become `Book` objects; invalid lines become `null` and are filtered out.
7. Each valid `Book` is inserted into the map using `merge`. If the `BOOK_ID` already exists,
   a `Comparator` decides which record to keep.

### 4. Validation Rules Applied
A record is **ignored** if any of the following is true:
- The line is `null` or does not split into exactly 6 fields (invalid format).
- Any field is empty or only whitespace after `trim()`.
- `BORROW_COUNT` or `RATING` cannot be parsed as a number.
- `borrowCount < 0`.
- `rating < 0` or `rating > 5`.

Only records passing **all** checks are stored.

### 5. Duplicate Resolution Logic
`BOOK_ID` must be unique. When two records share the same ID, the "better" record is kept using
this priority order:
1. **Higher rating** wins.
2. If ratings are equal → **higher borrow count** wins.
3. If both are equal → **lexicographically smaller title** wins.

This is expressed with a `Comparator`:

```java
Comparator.comparingDouble(Book::getRating)
          .thenComparingInt(Book::getBorrowCount)
          .thenComparing(Book::getTitle, Comparator.reverseOrder());
```

The first two keys are natural ("bigger is better"). Title uses `reverseOrder()` because a
**smaller** title should win, but the comparator framework keeps the "greater" element — so we
flip the title ordering. During `merge`, if `compare(incoming, existing) > 0` the incoming record
replaces the existing one; otherwise the existing record stays.

### 6. Why These Concepts Were Chosen
- **HashMap** — the problem needs unique keys and fast lookups at scale; a hash map is the
  natural fit and gives average O(1) operations.
- **Stream pipeline** — makes the validate-then-transform flow readable and avoids error-prone
  manual loops and index handling.
- **Comparator chaining** — the duplicate rule is a multi-level tie-break; `thenComparing`
  expresses exactly that without nested `if/else`.
- **Map.merge** — combines "add if absent" and "resolve conflict" cleanly in one call.
- **Immutable Book** — analytics code reads the same objects from many methods; immutability
  removes any risk of accidental modification or threading bugs.
- **Optional for the input** — defends against a `null` list without an explicit null check block.

### 7. Edge Cases Handled
- `records` is `null` → treated as empty, no exception.
- Individual `null` lines inside the list → skipped.
- Empty title or other empty fields, including blank spaces (`"   "`) → rejected via `trim()`.
- Trailing empty field (`...|`) → preserved by `split(..., -1)` and then rejected as empty.
- Lines with too few or too many `|` separators → rejected by the `length == 6` check.
- Non-numeric borrow count or rating → caught by `NumberFormatException`.
- Boundary ratings exactly `0` and `5` are **accepted**; `-0.1` and `5.1` are rejected.
- Duplicate IDs across the input → resolved deterministically by the comparator.

### 8. Possible Viva / Interview Questions and Answers
**Q: Why use `split("\\|", -1)` instead of `split("\\|")`?**
A: The default `split` drops trailing empty strings, so a record ending in an empty field would
lose that field and might pass the 6-field check by accident. Using `-1` keeps all empty fields,
so the empty-field validation works correctly.

**Q: Why is `|` written as `"\\|"`?**
A: `split` takes a regular expression, and `|` means "OR" in regex. Escaping it as `\\|` matches a
literal pipe character.

**Q: Why a HashMap and not a TreeMap or LinkedHashMap here?**
A: `loadBooks` only needs uniqueness and fast insertion by key; ordering is not required at load
time. HashMap gives O(1) average performance. Ordering is handled later by the specific analytics
methods that need it.

**Q: How does `Map.merge` decide which record to keep?**
A: If the key is new, the value is inserted directly. If the key exists, the remapping function
runs with the old and new values, and the comparator picks the better one.

**Q: Why does the title use `Comparator.reverseOrder()`?**
A: The rule says the **smaller** title should win, but `merge` keeps the element the comparator
considers "greater". Reversing the title order makes the smaller title compare as greater, so it
wins the tie.

**Q: Why is `Book` immutable and why does that help?**
A: All fields are `final` and set only in the constructor. Immutable objects are safe to share
across many analytics methods and across threads, and they cannot be corrupted after validation.

**Q: What happens if the rating is exactly 0 or exactly 5?**
A: They are valid. The checks reject only `rating < 0` and `rating > 5`, so the boundaries are
inclusive.

**Q: Where are invalid records reported?**
A: They are silently ignored, as the rules require. Invalid lines map to `null` and are removed by
`filter(Objects::nonNull)` before reaching the map.

## topRatedBooks(int n)

### 1. Method Purpose
`topRatedBooks(int n)` returns the best `n` books ranked by quality. It takes the books already
loaded in the map, sorts them by a clear priority order, and returns the top `n` as a
`List<Book>`. It is a read-only query method - it never changes the stored data.

### 2. Concepts Used / Data Structures Used
- **Stream API** - `books.values().stream()` to process all books in a pipeline.
- **Comparator chaining** - `comparingDouble`, `reversed`, and `thenComparing` to express a
  multi-level sort in one object.
- **`limit(n)`** - to keep only the first `n` results after sorting.
- **Collectors.toList()** - to gather the result into a `List<Book>`.
- **`Collections.emptyList()`** - to return a safe empty list for the `n <= 0` case.

### 3. Step-by-Step Execution Flow
1. If `n <= 0`, immediately return an empty list (nothing to rank).
2. Build a comparator that ranks books by rating DESC, then borrow count DESC, then title DESC.
3. Stream over all book values in the map.
4. Sort the stream using the comparator.
5. Apply `limit(n)` to take only the top `n` books. If `n` is larger than the number of books,
   `limit` simply returns all of them.
6. Collect the sorted, limited stream into a `List<Book>` and return it.

### 4. Sorting Rules Applied
Books are ordered by this cascading priority:
1. **Rating** - highest first (DESC).
2. If ratings tie -> **Borrow count** - highest first (DESC).
3. If borrow counts also tie -> **Title** - descending (DESC): larger title first.

Expressed as:

```java
Comparator.comparingDouble(Book::getRating).reversed()
          .thenComparing(Comparator.comparingInt(Book::getBorrowCount).reversed())
          .thenComparing(Book::getTitle, Comparator.reverseOrder());
```

### 5. Why These Concepts Were Chosen
- **Comparator chaining** maps directly to the multi-level tie-break requirement and avoids
  nested `if/else` comparisons.
- **Streams + sorted + limit** make "sort then take top n" read like the requirement itself.
- **`Collections.emptyList()`** for the invalid `n` keeps the method null-safe; callers never get
  a `null` back.

### 6. Edge Cases Handled
- `n <= 0` -> returns an empty list, not `null`.
- `n` larger than the number of books -> `limit` returns all available books.
- Empty map -> returns an empty list.
- Ties on rating and borrow count -> resolved deterministically by reverse-alphabetical title (larger title first).

### 7. Possible Viva / Interview Questions and Answers
**Q: Why is the title comparator reversed (DESC) too?**
A: All three keys are descending here. Rating and borrow count are descending because higher is
better. The title tiebreaker is also reversed so that, on a full tie, the lexicographically larger
title comes first, which is what the expected ordering requires.

**Q: Why use `thenComparing(Comparator.comparingInt(...).reversed())` instead of
`thenComparingInt(...).reversed()`?**
A: Calling `.reversed()` directly after `thenComparingInt` would reverse the **entire** chain
built so far, not just the borrow count. Wrapping the borrow-count comparator and reversing it in
isolation keeps each level independent.

**Q: What happens if `n` is bigger than the number of books?**
A: `limit(n)` only caps the stream; if there are fewer elements it returns all of them, so the
method safely returns the full sorted list.

**Q: Does this method modify the stored books?**
A: No. Streaming and sorting operate on a temporary pipeline; the underlying map and the `Book`
objects (which are immutable) are unchanged.

**Q: Why return `Collections.emptyList()` instead of `null` for `n <= 0`?**
A: Returning an empty list lets callers iterate safely without null checks and avoids
`NullPointerException`.
## averageRatingByCategory()

### 1. Method Purpose
`averageRatingByCategory()` reports, for every category, the average rating of its books rounded
to 2 decimal places. The result is a `TreeMap<String, Double>` so categories come out in
alphabetical order. It is a read-only summary query.

### 2. Concepts Used / Data Structures Used
- **Stream API** - to process all books in one pipeline.
- **Collectors.groupingBy** - to bucket books by category.
- **TreeMap (via the map supplier)** - to keep category keys sorted alphabetically.
- **Collectors.averagingDouble** - to compute the mean rating per group.
- **Collectors.collectingAndThen** - to post-process each average (rounding) after it is computed.
- **`Math.round`** - to round to 2 decimals.

### 3. Step-by-Step Execution Flow
1. Stream over all book values.
2. Group them by category using `groupingBy`.
3. Use `TreeMap::new` as the map supplier so the resulting map is sorted by category name.
4. For each category group, compute the average rating with `averagingDouble(Book::getRating)`.
5. Wrap that with `collectingAndThen` to round the average to 2 decimals.
6. The collector produces a `TreeMap<String, Double>` of category to rounded average.

### 4. Rounding Logic
The rounding uses `Math.round(avg * 100.0) / 100.0`: multiply by 100, round to the nearest whole
number, then divide back by 100. For example `4.766...` becomes `476.6...` -> `477` -> `4.77`.

### 5. Why These Concepts Were Chosen
- **groupingBy with a downstream collector** is the idiomatic way to "group and aggregate" in one
  pass, instead of building maps and lists manually.
- **TreeMap supplier** satisfies the alphabetical-ordering requirement directly inside the
  collector, so no extra sorting step is needed.
- **collectingAndThen** lets us round the computed average without an extra loop over the map.

### 6. Edge Cases Handled
- Empty map -> returns an empty `TreeMap`.
- A category with a single book -> average equals that book's rating.
- Averages with long decimal tails -> cleanly rounded to 2 places.

### 7. Possible Viva / Interview Questions and Answers
**Q: How are the categories sorted alphabetically?**
A: The three-argument `groupingBy` takes a map supplier; passing `TreeMap::new` makes the result a
`TreeMap`, whose keys are kept in natural (alphabetical) order automatically.

**Q: Why use `collectingAndThen` instead of rounding afterwards?**
A: It applies the rounding as part of the same collector, keeping everything in one stream pass
and avoiding a second iteration over the result map.

**Q: Why `averagingDouble` and not summing and dividing manually?**
A: `averagingDouble` is a built-in collector that handles the count and sum internally and returns
the mean, which is cleaner and less error-prone.

**Q: Could the rounding lose precision?**
A: `double` rounding to 2 decimals is adequate for ratings here. For exact decimal money-style
values `BigDecimal` would be preferred, but ratings do not need that.


## mostBorrowedBook()

### 1. Method Purpose
`mostBorrowedBook()` returns the single most popular book by borrow count, wrapped in an
`Optional<Book>` so the caller can handle the "no books" case safely. Ties are broken
deterministically.

### 2. Concepts Used / Data Structures Used
- **Stream API** - `books.values().stream()` to scan all books.
- **Comparator chaining** - `comparingInt`, `thenComparingDouble`, `thenComparing` for the
  multi-level tie-break.
- **`Stream.max`** - to pick the single greatest element by the comparator.
- **Optional** - `max` returns `Optional<Book>`, empty when there are no books.

### 3. Step-by-Step Execution Flow
1. Build a comparator ordering books by borrow count, then rating, then bookId.
2. Stream over all book values.
3. Use `max(comparator)` to find the "greatest" book according to that order.
4. Return the result as an `Optional<Book>` (empty if the map is empty).

### 4. Selection / Tie-Break Rules
1. **Highest borrow count** wins.
2. If borrow counts tie -> **highest rating** wins.
3. If ratings also tie -> **smallest bookId** wins.

Expressed as:

```java
Comparator.comparingInt(Book::getBorrowCount)
          .thenComparingDouble(Book::getRating)
          .thenComparing(Book::getBookId, Comparator.reverseOrder());
```

### 5. Why These Concepts Were Chosen
- **`max` over `sorted().findFirst()`** is more efficient: it makes a single linear pass instead
  of sorting the whole collection.
- **Comparator chaining** expresses the three-level tie-break cleanly.
- **Optional** is the natural return type for "there might be no result".

### 6. The bookId Tie-Break Detail
Borrow count and rating use natural ascending order, and `max` returns the greatest - so higher
values win, which is what we want. But for bookId the **smaller** id should win. Since `max` keeps
the greatest element, we reverse the bookId ordering with `Comparator.reverseOrder()` so the
smaller id compares as "greater" and is selected.

### 7. Edge Cases Handled
- Empty map -> `max` returns `Optional.empty()`.
- Full tie on borrow count and rating -> resolved by smallest bookId.
- Single book -> that book is returned.

### 8. Possible Viva / Interview Questions and Answers
**Q: Why use `max` instead of sorting and taking the first element?**
A: `max` runs in a single O(n) pass and does not need to order the entire collection, so it is
faster and uses less memory for a "find the best one" query.

**Q: Why `reverseOrder()` only on bookId?**
A: `max` returns the comparator's greatest element. Borrow count and rating want the largest value
(natural order works). For bookId we want the smallest, so reversing its order makes the smallest
id win under `max`.

**Q: Why return `Optional<Book>`?**
A: When the map is empty there is no answer; `Optional` forces the caller to handle that case
instead of risking a `null` and a `NullPointerException`.

**Q: Is the result deterministic when everything ties?**
A: Yes. bookIds are unique, so the final tie-break on bookId always produces one definite winner.
## authorsWithMultipleCategories()

### 1. Method Purpose
`authorsWithMultipleCategories()` finds authors who have written books in more than one category.
The result is a `TreeSet<String>` so the author names come out sorted alphabetically and unique.

### 2. Concepts Used / Data Structures Used
- **Stream API** - two pipelines: one to build the data, one to filter it.
- **Collectors.groupingBy** - to group books by author.
- **Collectors.mapping + toSet** - to collect each author's distinct categories into a `Set`.
- **TreeSet (via `toCollection`)** - to return sorted, unique author names.

### 3. Step-by-Step Execution Flow
1. Stream over all books and group by author.
2. For each author, map each book to its category and collect those categories into a `Set`.
   A set automatically removes duplicate categories, so size reflects distinct categories only.
3. Stream over the resulting `author -> Set<category>` entries.
4. Keep only authors whose category set has more than one element.
5. Take the author names (keys) and collect them into a `TreeSet`, giving sorted unique output.

### 4. Why a Set of Categories
Using a `Set` for the categories means that an author with three books all in "Programming"
counts as a single category. Only genuinely different categories increase the size, which is
exactly what "more than one category" requires.

### 5. Why These Concepts Were Chosen
- **groupingBy + mapping + toSet** builds the author-to-categories view in one pass.
- **TreeSet** satisfies both requirements at once: uniqueness and alphabetical ordering.

### 6. Edge Cases Handled
- Author with multiple books in the same category -> not included (still one distinct category).
- Empty map -> returns an empty `TreeSet`.
- Author with exactly one category -> excluded by the `size() > 1` filter.

### 7. Possible Viva / Interview Questions and Answers
**Q: Why collect categories into a Set instead of a List?**
A: A set ignores duplicates, so counting its size gives the number of *distinct* categories. A
list would count repeats and wrongly flag single-category authors.

**Q: Why return a TreeSet?**
A: The requirement asks for unique author names in alphabetical order; `TreeSet` provides both
automatically.

**Q: Why two stream stages instead of one?**
A: The first stage builds the grouped data structure; the second filters and reshapes it. Doing it
in two clear steps keeps the logic readable.


## groupBooksByAuthor()

### 1. Method Purpose
`groupBooksByAuthor()` returns each author mapped to their list of books. Authors appear in
alphabetical order and each author's books are sorted by rating then borrow count. The return type
is `LinkedHashMap<String, List<Book>>` to preserve that author ordering.

### 2. Concepts Used / Data Structures Used
- **Stream API** - to sort and group in one pipeline.
- **Comparator chaining** - for both the author ordering and the within-list ordering.
- **Collectors.groupingBy with a LinkedHashMap supplier** - to keep insertion (alphabetical) order.
- **LinkedHashMap** - remembers the order keys were inserted.

### 3. Step-by-Step Execution Flow
1. Build a `withinAuthor` comparator: rating DESC, then borrow count DESC.
2. Stream over all books.
3. Sort the whole stream first by author ASC, then by the `withinAuthor` comparator.
   After this single sort, books are globally ordered: authors alphabetically, and within each
   author by rating/borrow.
4. Group by author into a `LinkedHashMap`. Because grouping preserves encounter order, authors are
   inserted alphabetically and each list keeps the already-sorted order.
5. Return the `LinkedHashMap<String, List<Book>>`.

### 4. Ordering Rules Applied
- **Map key order (authors):** alphabetical ASC.
- **Within each author list:** rating DESC, then borrow count DESC.

### 5. Why Sort Before Grouping
Sorting the entire stream up front does double duty: it fixes both the author key order and the
per-author list order in one step. Since `groupingBy` keeps the encounter order, no extra sorting
of the lists is needed afterwards.

### 6. Why a LinkedHashMap
A plain `HashMap` would lose the alphabetical author order. `LinkedHashMap` preserves the order in
which authors were first inserted - which, thanks to the pre-sort, is alphabetical.

### 7. Edge Cases Handled
- Empty map -> returns an empty `LinkedHashMap`.
- Author with one book -> a single-element list.
- Ties on rating and borrow count -> books keep a stable relative order (sort is stable).

### 8. Possible Viva / Interview Questions and Answers
**Q: Why sort the stream before grouping instead of sorting each list afterwards?**
A: One global sort handles both the author key order and the within-list order. Grouping preserves
encounter order, so the lists come out already sorted without a second pass.

**Q: Why a LinkedHashMap and not a TreeMap?**
A: Either could give sorted keys, but the requirement specifies `LinkedHashMap`. We achieve sorted
keys by inserting authors in alphabetical order (from the pre-sort), and `LinkedHashMap` preserves
that insertion order.

**Q: Why doesn't the within-author comparator include a title tie-break?**
A: The requirement only specifies rating DESC then borrow count DESC. Java's stream sort is stable,
so equal elements keep their prior relative order.

**Q: Could you have sorted only inside each group's list?**
A: Yes, but that would require sorting every list separately after grouping. Pre-sorting once is
simpler and covers both ordering requirements together.
## suspiciousBooks()

### 1. Method Purpose
`suspiciousBooks()` scans all loaded books and returns the **distinct, alphabetically sorted**
titles of books that look suspicious by at least one of four rules. The whole thing is done with
streams and no explicit loops.

### 2. Concepts Used / Data Structures Used
- **Stream API** - for both precomputing category statistics and filtering the books.
- **Collectors.groupingBy** with `counting`, `summingInt`, `summingDouble` - to precompute per
  category: number of books, total borrow count, total rating.
- **Regular expressions (Pattern / Matcher)** - for the word-based text checks.
- **`distinct()` and `sorted()`** - to produce unique titles in alphabetical order.
- **A small private helper** (`isSuspicious`) - keeps the stream readable; it returns a boolean.

### 3. Precomputed Data (why and what)
Conditions 3 and 4 compare a book against its category's average. Recomputing the average inside
the filter for every book would be wasteful, so three lookup maps are built once up front:
- `categoryCount` - how many books are in each category.
- `categoryBorrowSum` - total borrow count per category.
- `categoryRatingSum` - total rating per category.
From these sums we can cheaply derive any "average of the other books" value.

### 4. The Four Suspicion Conditions
A book is suspicious if **any** condition is true:

**Condition 1 - Repeated consecutive word in title (case-insensitive).**
Detected with the regex `\b(\w+)\s+\1\b`. The group `(\w+)` captures a word and `\1` is a
back-reference that must match the same word again right after it. With `CASE_INSENSITIVE`,
"Java Java Mastery" and "Clean Clean Code" both match.

**Condition 2 - Author name appears in the title as whole words (case-insensitive).**
A naive `title.contains(author)` is wrong: author "A" would match the letter 'a' inside "Base".
Instead the author is matched with word boundaries: `\b` + quoted author + `\b`. So "James Clear"
matches inside "The James Clear Method", but "A" does not match inside "Base".

**Condition 3 - Borrow surge.**
The book's borrow count is more than 300% above the category baseline, i.e.
`borrowCount > baselineAvgBorrow * 4`. The baseline is the **leave-one-out** average - the average
of the *other* books in the category, computed as `(categoryBorrowSum - thisBook) / (count - 1)`.

**Condition 4 - Underperforming but high traffic.**
`rating < baselineAvgRating` **AND** `borrowCount > baselineAvgBorrow`, again using the
leave-one-out baselines. A book read a lot but rated below its peers is suspicious.

### 5. Why "Leave-One-Out" Averages
The tests treat the category average as the baseline formed by the *other* books, not including
the book being judged. For example, three Tech books with borrows 100, 100 and 401: the suspicious
one (401) is compared against the average of the remaining two (100), giving a threshold of
`100 * 4 = 400`, so 401 is flagged. Including the book itself would distort the baseline and the
expected results would not match. When a category has only one book there is no baseline, so
conditions 3 and 4 are skipped (this also avoids dividing by zero).

### 6. Output Shaping
After filtering, the stream maps each suspicious book to its title, applies `distinct()` to remove
duplicate titles (two different book IDs can share a title), `sorted()` for alphabetical order, and
collects to a `List<String>`.

### 7. Why These Concepts Were Chosen
- **Precomputed grouping maps** avoid recomputing category stats per book (one pass instead of
  many).
- **Regex with back-references and word boundaries** expresses the text rules precisely and
  case-insensitively without manual character scanning.
- **Streams with distinct + sorted** satisfy the "streams only, no loops, distinct, sorted"
  requirement directly.

### 8. Edge Cases Handled
- Category with a single book -> conditions 3 and 4 are skipped (no baseline, no divide-by-zero).
- Author as a short string (e.g. "A") -> whole-word matching avoids false positives inside other
  words.
- Two books sharing the same title -> output keeps the title only once via `distinct()`.
- A book satisfying several conditions at once -> still appears once.
- Empty map -> returns an empty list.

### 9. Possible Viva / Interview Questions and Answers
**Q: Why not use `title.contains(author)` for condition 2?**
A: A plain substring match gives false positives - author "A" would match the 'a' inside "Base".
Word-boundary regex matches the author only as complete words.

**Q: What does `\b(\w+)\s+\1\b` mean?**
A: `(\w+)` captures a word, `\s+` is the space(s) after it, and `\1` is a back-reference requiring
the same word immediately again - i.e. a repeated consecutive word. `\b` anchors at word
boundaries so partial words do not match.

**Q: Why leave-one-out averages instead of the full category average?**
A: The expected results compare each book against the baseline formed by the *other* books. Using
the full average (including the book itself) would change the threshold and fail the cases.

**Q: How do you avoid divide-by-zero for single-book categories?**
A: Conditions 3 and 4 only run when the category has more than one book (`count > 1`), so
`count - 1` is never zero.

**Q: Why precompute the category maps before the main stream?**
A: To compute each book's baseline in O(1) using simple subtraction, instead of re-scanning the
category for every book.

**Q: Did you use any loops?**
A: No. All processing is done with stream operations (groupingBy, filter, map, distinct, sorted);
the only branching is inside the boolean helper, which uses simple `if`/logical-OR, not loops.

**Q: Why is `distinct()` needed?**
A: Different book IDs can share the same title, and the output must list distinct titles only.
## categoryWiseTopRatedBookByEachAuthor()

### 1. Method Purpose
This method builds a two-level grouped view: for every category, and within it for every author,
it keeps that author's single top-rated book. The result is a
`Map<String, Map<String, Book>>` shaped as `category -> (author -> best Book)`.

### 2. Concepts Used / Data Structures Used
- **Stream API** - one pipeline produces the whole nested structure.
- **Nested Collectors.groupingBy** - outer grouping by category, inner grouping by author.
- **Collectors.maxBy** - picks the best book per author using a comparator.
- **Collectors.collectingAndThen** - unwraps the `Optional<Book>` from `maxBy` into a plain `Book`.
- **Comparator chaining** - encodes the multi-level tie-break for "top book".

### 3. Step-by-Step Execution Flow
1. Build a `ranking` comparator: rating, then borrow count, then bookId.
2. Stream over all books.
3. Outer `groupingBy(Book::getCategory)` splits books into category buckets.
4. Inner `groupingBy(Book::getAuthor)` splits each category bucket into author buckets.
5. For each author bucket, `maxBy(ranking)` finds the best book, returning `Optional<Book>`.
6. `collectingAndThen(..., Optional::get)` unwraps that into a plain `Book`.
7. The result is the nested `category -> author -> Book` map.

### 4. Top-Book Selection Rules
1. **Higher rating** wins.
2. If ratings tie -> **higher borrow count** wins.
3. If still tied -> **smaller bookId** wins.

```java
Comparator.comparingDouble(Book::getRating)
          .thenComparingInt(Book::getBorrowCount)
          .thenComparing(Book::getBookId, Comparator.reverseOrder());
```

### 5. Why `Optional::get` Is Safe Here
`maxBy` returns `Optional<Book>` because, in general, a group could be empty. But these groups are
created by `groupingBy` only when at least one book falls into them, so every author bucket has at
least one book. Therefore the `Optional` is always present and `Optional::get` never fails.

### 6. The bookId Tie-Break Detail
`maxBy` returns the element the comparator considers greatest. Rating and borrow count use natural
ascending order, so higher values are "greater" - exactly what we want. For bookId the **smaller**
id should win, so `Comparator.reverseOrder()` flips its ordering, making the smaller id compare as
greater and therefore be chosen.

### 7. Why These Concepts Were Chosen
- **Nested groupingBy** directly mirrors the two-level "category then author" requirement.
- **maxBy + collectingAndThen** is the idiomatic "reduce each group to its best element" pattern.
- **Comparator chaining** cleanly expresses the three-level tie-break.

### 8. Edge Cases Handled
- Empty map -> returns an empty map.
- A category with one author -> inner map has a single entry.
- An author with one book -> that book is selected trivially.
- Ties on rating and borrow count -> resolved deterministically by smallest bookId.

### 9. Possible Viva / Interview Questions and Answers
**Q: Why is it safe to call `Optional::get` after `maxBy`?**
A: `maxBy` only returns empty for an empty group, but `groupingBy` never creates empty groups, so
each author group always has at least one book. The optional is guaranteed present.

**Q: Why use `collectingAndThen`?**
A: `maxBy` produces `Optional<Book>`, but the required value type is `Book`. `collectingAndThen`
applies `Optional::get` as a finishing step so the inner map holds plain `Book` values.

**Q: How does the two-level grouping work?**
A: The outer `groupingBy` keys by category; its downstream collector is itself a `groupingBy`
keyed by author, whose downstream collector reduces each author's books to the best one.

**Q: Why `reverseOrder()` on bookId only?**
A: `maxBy` keeps the greatest element. Rating and borrow count want the largest value (natural
order). bookId wants the smallest, so reversing its order makes the smallest id win under `maxBy`.

**Q: Could you use TreeMap to also sort categories/authors?**
A: Yes - supplying `TreeMap::new` to each `groupingBy` would sort the keys. The current
requirement only asks for the nested structure, so plain maps are used.

