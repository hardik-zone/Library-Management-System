import java.util.Scanner;
class Book {
    private String title;
    private String author;
    private boolean issued;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    // Mark the book as issued
    public boolean issue() {
        if (!issued) {
            issued = true;
            return true;
        }
        return false;
    }

    // Mark the book as returned
    public boolean returnBook() {
        if (issued) {
            issued = false;
            return true;
        }
        return false;
    }

    // Get the book title
    public String getTitle() {
        return title;
    }

    // Display book details
    public void showDetails() {
        System.out.println("Title: " + title + " | Author: " + author + " | Issued: " + (issued ? "Yes" : "No"));
    }
}
class Library {
    private Book[] collection;
    private int totalBooks;

    // Constructor
    public Library(int capacity) {
        collection = new Book[capacity];
        totalBooks = 0;
    }

    // Add a new book
    public void addNewBook(String title, String author) {
        if (totalBooks >= collection.length) {
            System.out.println("Oops! The library is full, cannot add more books.");
            return;
        }
        collection[totalBooks] = new Book(title, author);
        totalBooks++;
        System.out.println("Book \"" + title + "\" added successfully!");
    }

    // Display all books
    public void listBooks() {
        if (totalBooks == 0) {
            System.out.println("The library has no books yet.");
            return;
        }
        System.out.println("\n--- List of Books ---");
        for (int i = 0; i < totalBooks; i++) {
            collection[i].showDetails();
        }
    }

    // Issue a book by title
    public void borrowBook(String title) {
        for (int i = 0; i < totalBooks; i++) {
            if (collection[i].getTitle().equalsIgnoreCase(title)) {
                if (collection[i].issue()) {
                    System.out.println("You have borrowed \"" + title + "\" successfully.");
                } else {
                    System.out.println("Sorry, \"" + title + "\" is already issued.");
                }
                return;
            }
        }
        System.out.println("Book \"" + title + "\" is not found in the library.");
    }

    // Return a book by title
    public void returnBook(String title) {
        for (int i = 0; i < totalBooks; i++) {
            if (collection[i].getTitle().equalsIgnoreCase(title)) {
                if (collection[i].returnBook()) {
                    System.out.println("You have returned \"" + title + "\" successfully.");
                } else {
                    System.out.println("This book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book \"" + title + "\" is not found in the library.");
    }
}
public class LibraryMan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library myLibrary = new Library(10);
        int option;

        System.out.println("=== Welcome to Your Library System ===");

        do {
            System.out.println("\n1. Add a Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (option) {
                case 1 -> {
                    System.out.print("Book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Book author: ");
                    String author = scanner.nextLine();
                    myLibrary.addNewBook(title, author);
                }
                case 2 -> myLibrary.listBooks();
                case 3 -> {
                    System.out.print("Enter title to borrow: ");
                    String title = scanner.nextLine();
                    myLibrary.borrowBook(title);
                }
                case 4 -> {
                    System.out.print("Enter title to return: ");
                    String title = scanner.nextLine();
                    myLibrary.returnBook(title);
                }
                case 5 -> System.out.println("Thanks for visiting the library!");
                default -> System.out.println("Invalid option. Try again!");
            }

        } while (option != 5);

        scanner.close();
    }
}