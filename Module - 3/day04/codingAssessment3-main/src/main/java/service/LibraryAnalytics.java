package service;
import entity.Book;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LibraryAnalytics {

    private Map<String, Book> books = new HashMap<>();

    public void loadBooks(List<String> records) {

        Comparator<Book> betterRecord = Comparator.comparingDouble(Book::getRating)
                .thenComparingInt(Book::getBorrowCount)
                .thenComparing(Book::getTitle, Comparator.reverseOrder());

        Optional.ofNullable(records)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(record -> record.split("\\|", -1))
                .filter(parts -> parts.length == 6)
                .map(parts -> {
                    for (String field : parts) {
                        if (field == null || field.trim().isEmpty()) {
                            return null;
                        }
                    }
                    try {
                        String bookId = parts[0].trim();
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        String category = parts[3].trim();
                        int borrowCount = Integer.parseInt(parts[4].trim());
                        double rating = Double.parseDouble(parts[5].trim());

                        if (borrowCount < 0 || rating < 0 || rating > 5) {
                            return null;
                        }
                        return new Book(bookId, title, author, category, borrowCount, rating);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(book -> books.merge(book.getBookId(), book,
                        (existing, incoming) ->
                                betterRecord.compare(incoming, existing) > 0 ? incoming : existing));
    }

    public List<Book> topRatedBooks(int n) {

        if (n <= 0) {
            return Collections.emptyList();
        }

        Comparator<Book> ranking = Comparator
                .comparingDouble(Book::getRating).reversed()
                .thenComparing(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .thenComparing(Book::getTitle, Comparator.reverseOrder());

        return books.values().stream()
                .sorted(ranking)
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<String, Double> averageRatingByCategory() {

        return books.values().stream()
                .collect(Collectors.groupingBy(
                        Book::getCategory,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.averagingDouble(Book::getRating),
                                avg -> Math.round(avg * 100.0) / 100.0)));
    }

    public Optional<Book> mostBorrowedBook() {

        Comparator<Book> ranking = Comparator
                .comparingInt(Book::getBorrowCount)
                .thenComparingDouble(Book::getRating)
                .thenComparing(Book::getBookId, Comparator.reverseOrder());

        return books.values().stream().max(ranking);
    }

    public Set<String> authorsWithMultipleCategories() {

        return books.values().stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.mapping(Book::getCategory, Collectors.toSet())))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String,List<Book>> groupBooksByAuthor() {

        Comparator<Book> withinAuthor = Comparator
                .comparingDouble(Book::getRating).reversed()
                .thenComparing(Comparator.comparingInt(Book::getBorrowCount).reversed());

        return books.values().stream()
                .sorted(Comparator.comparing(Book::getAuthor).thenComparing(withinAuthor))
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        LinkedHashMap::new,
                        Collectors.toList()));
    }

    public List<String> suspiciousBooks() {

        Map<String, Long> categoryCount = books.values().stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

        Map<String, Integer> categoryBorrowSum = books.values().stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.summingInt(Book::getBorrowCount)));

        Map<String, Double> categoryRatingSum = books.values().stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.summingDouble(Book::getRating)));

        Pattern repeatedWord = Pattern.compile("\\b(\\w+)\\s+\\1\\b", Pattern.CASE_INSENSITIVE);

        return books.values().stream()
                .filter(book -> isSuspicious(book, repeatedWord,
                        categoryCount, categoryBorrowSum, categoryRatingSum))
                .map(Book::getTitle)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private boolean isSuspicious(Book book,
                                 Pattern repeatedWord,
                                 Map<String, Long> categoryCount,
                                 Map<String, Integer> categoryBorrowSum,
                                 Map<String, Double> categoryRatingSum) {

        String title = book.getTitle();

        boolean condition1 = repeatedWord.matcher(title).find();

        boolean condition2 = Pattern
                .compile("\\b" + Pattern.quote(book.getAuthor()) + "\\b", Pattern.CASE_INSENSITIVE)
                .matcher(title)
                .find();

        long count = categoryCount.get(book.getCategory());
        boolean condition3 = false;
        boolean condition4 = false;

        if (count > 1) {
            double othersBorrowAvg =
                    (categoryBorrowSum.get(book.getCategory()) - book.getBorrowCount()) / (double) (count - 1);
            double othersRatingAvg =
                    (categoryRatingSum.get(book.getCategory()) - book.getRating()) / (count - 1);

            condition3 = book.getBorrowCount() > othersBorrowAvg * 4;
            condition4 = book.getRating() < othersRatingAvg
                    && book.getBorrowCount() > othersBorrowAvg;
        }

        return condition1 || condition2 || condition3 || condition4;
    }

    public Map<String, Map<String, Book>> categoryWiseTopRatedBookByEachAuthor() {
        return Map.of();
    }
}

