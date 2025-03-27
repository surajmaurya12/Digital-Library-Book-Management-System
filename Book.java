import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(int id, String title, String author, String genre, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailability(boolean available) { this.isAvailable = available; }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Genre: " + genre + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book Record");
            System.out.println("6. Exit System");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewAllBooks(); break;
                case 3: searchBook(); break;
                case 4: updateBook(); break;
                case 5: deleteBook(); break;
                case 6: 
                    System.out.println("Exiting System...");
                    return;
                default: 
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Is Available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        books.add(new Book(id, title, author, genre, isAvailable));
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String query = scanner.nextLine();

        for (Book book : books) {
            if (String.valueOf(book.getId()).equals(query) || book.getTitle().equalsIgnoreCase(query)) {
                System.out.println("Book Found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Book book : books) {
            if (book.getId() == id) {
                System.out.print("Enter new Title (leave blank to keep current): ");
                String title = scanner.nextLine();
                if (!title.isBlank()) book.setTitle(title);

                System.out.print("Enter new Author (leave blank to keep current): ");
                String author = scanner.nextLine();
                if (!author.isBlank()) book.setAuthor(author);

                System.out.print("Enter new Genre (leave blank to keep current): ");
                String genre = scanner.nextLine();
                if (!genre.isBlank()) book.setGenre(genre);

                System.out.print("Is Available (true/false): ");
                boolean isAvailable = scanner.nextBoolean();
                book.setAvailability(isAvailable);

                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();

        books.removeIf(book -> book.getId() == id);
        System.out.println("Book deleted successfully (if existed).");
    }
}
