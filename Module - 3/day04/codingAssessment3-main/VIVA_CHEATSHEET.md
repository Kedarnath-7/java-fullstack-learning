# Viva Cheat Sheet - LibraryAnalytics

Quick, low-verbosity revision. One block per method: what it does, key idea, gotcha.

---

## Data store
`private Map<String, Book> books = new HashMap<>();`
- Key = BOOK_ID (unique). `Book` is immutable (all fields `final`).

---

## 1. loadBooks(List<String> records)
**Does:** Parse `ID|TITLE|AUTHOR|CATEGORY|BORROW|RATING`, validate, dedupe, store in map.
**Key idea:** stream -> `split("\\|", -1)` -> validate -> `Book` -> `map.merge(id, book, keepBetter)`.
**Validation (reject if):** not 6 fields, any field blank after `trim()`, borrow<0, rating<0, rating>5, parse fails (`NumberFormatException`).
**Dedupe (keep better):** higher rating -> higher borrow -> smaller title.
**Gotchas:**
- `split(..., -1)` keeps trailing empty fields (else `B204||...` slips through).
- `\\|` because `|` is regex OR.
- Title uses `reverseOrder()` so *smaller* title wins under `merge` (merge keeps the "greater").

---

## 2. topRatedBooks(int n)
**Does:** Return top `n` books.
**Key idea:** `stream().sorted(cmp).limit(n).collect(toList())`.
**Order:** rating DESC -> borrow DESC -> title DESC.
**Gotchas:**
- `n <= 0` -> `Collections.emptyList()`.
- `limit(n)` returns all if n > size.
- Wrap each `.reversed()` per key, else it reverses the whole chain.
- Title is DESC here (test expects larger title first on full tie).

---

## 3. averageRatingByCategory()
**Does:** Category -> average rating, rounded 2 dp, sorted by category.
**Key idea:** `groupingBy(category, TreeMap::new, collectingAndThen(averagingDouble(rating), round2))`.
**Round:** `Math.round(avg*100.0)/100.0`.
**Gotchas:**
- `TreeMap::new` supplier gives alphabetical keys.
- `collectingAndThen` rounds inside the collector (no second pass).

---

## 4. mostBorrowedBook()
**Does:** Single most-borrowed book.
**Key idea:** `stream().max(cmp)` -> `Optional<Book>`.
**Order:** borrow -> rating -> bookId (smaller wins).
**Gotchas:**
- `max` is O(n), no full sort.
- bookId uses `reverseOrder()` so smaller id wins under `max`.
- Returns `Optional.empty()` when map empty.

---

## 5. authorsWithMultipleCategories()
**Does:** Authors with books in >1 category, sorted, unique.
**Key idea:** `groupingBy(author, mapping(category, toSet()))` -> filter `set.size() > 1` -> keys to `TreeSet`.
**Gotchas:**
- Categories collected into a **Set** so duplicates don't inflate the count.
- `TreeSet` = unique + alphabetical.

---

## 6. groupBooksByAuthor()
**Does:** Author -> list of their books. Authors A-Z; each list rating DESC, borrow DESC.
**Key idea:** `stream().sorted(author ASC, then rating DESC, borrow DESC).collect(groupingBy(author, LinkedHashMap::new, toList()))`.
**Gotchas:**
- Sort the whole stream **before** grouping -> fixes both key order and list order in one pass.
- `LinkedHashMap` preserves insertion (alphabetical) order; grouping keeps encounter order.

---

## 7. suspiciousBooks()
**Does:** Distinct, sorted titles of books flagged by ANY of 4 conditions. Streams, no loops.
**Precompute (per category):** count, borrow sum, rating sum (3 `groupingBy` maps).
**Conditions:**
1. Title has consecutive repeated word -> regex `\b(\w+)\s+\1\b` (CASE_INSENSITIVE).
2. Author appears in title as **whole words** -> regex `\b + quote(author) + \b` (so author "A" doesn't match the 'a' in "Base").
3. `borrow > baselineAvgBorrow * 4`.
4. `rating < baselineAvgRating` AND `borrow > baselineAvgBorrow`.
**Baseline = leave-one-out:** average of the *other* books in the category = `(sum - thisBook) / (count - 1)`.
**Gotchas:**
- Leave-one-out is why test passes (e.g. baseline 100, borrow 401 > 400).
- Only run cond 3/4 when `count > 1` (no baseline / avoids divide-by-zero).
- `distinct().sorted()` for output.

---

## 8. categoryWiseTopRatedBookByEachAuthor()
**Does:** `category -> (author -> top book)`.
**Key idea:** nested `groupingBy(category, groupingBy(author, collectingAndThen(maxBy(cmp), Optional::get)))`.
**Order (top book):** rating -> borrow -> bookId (smaller wins).
**Gotchas:**
- `maxBy` returns `Optional`; `Optional::get` is safe because groupingBy never makes empty groups.
- bookId `reverseOrder()` so smaller id wins under `maxBy`.

---

## One-line concept map (for "where did you use X?")
- **HashMap:** book store (O(1), unique key).
- **TreeMap:** averageRatingByCategory (sorted keys).
- **LinkedHashMap:** groupBooksByAuthor (insertion order).
- **TreeSet:** authorsWithMultipleCategories (sorted unique).
- **Optional:** mostBorrowedBook, maxBy unwrap.
- **Comparator chaining:** every ranking/tie-break.
- **Custom collector / collectingAndThen:** rounding + Optional unwrap.
- **Regex:** suspiciousBooks conditions 1 & 2.
- **Immutable object:** Book.
- **Stream API:** all query methods.

## Two reusable "tricks" to remember
1. **`reverseOrder()` on a key** = make the *smaller* value win when using `max`/`maxBy`/`merge` (which keep the greatest).
2. **Sort-then-groupingBy** = one sort fixes both map-key order and inner-list order.
