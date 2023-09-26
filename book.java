import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Book> bookInventory = new ArrayList<>();
    private static ArrayList<SalesTransaction> salesHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Book Store Menu:");
            System.out.println("1. Add Book to Inventory");
            System.out.println("2. Make a Sale");
            System.out.println("3. View Sales History");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addBookToInventory(scanner);
                    break;
                case 2:
                    makeSale(scanner);
                    break;
                case 3:
                    viewSalesHistory();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                     System.out.println();
         System.out.println("************************************************THANK YOU********************************************************");
         System.out.println();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                     System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
            }
        }
    }

    private static void addBookToInventory(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();

        Book book = new Book(title, author, price);
        bookInventory.add(book);
        System.out.println("Book added to inventory.");
         System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
    }

    private static void makeSale(Scanner scanner) {
        if (bookInventory.isEmpty()) {
            System.out.println("No books available in inventory.");
             System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
            return;
        }

        System.out.println("Available Books in Inventory:");
        for (int i = 0; i < bookInventory.size(); i++) {
            System.out.println((i + 1) + ". " + bookInventory.get(i).getTitle());
        }

        System.out.print("Select a book (by number): ");
        int bookIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (bookIndex < 1 || bookIndex > bookInventory.size()) {
            System.out.println("Invalid book selection.");
             System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
            return;
        }

        System.out.print("Enter quantity sold: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
            return;
        }

        Book selectedBook = bookInventory.get(bookIndex - 1);
        SalesTransaction transaction = new SalesTransaction(selectedBook, quantity);
        salesHistory.add(transaction);

        System.out.println("Sale recorded:");
        System.out.println(transaction.getTransactionDetails());
        System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
    }

    private static void viewSalesHistory() {
        if (salesHistory.isEmpty()) {
            System.out.println("No sales recorded.");
             System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
        } else {
            System.out.println("Sales History:");
            for (SalesTransaction transaction : salesHistory) {
                System.out.println(transaction.getTransactionDetails());
            }
            System.out.println();
         System.out.println("-----------------------------------------------------------------------------------------------------");
         System.out.println();
        }
    }

    static class Book {
        private String title;
        private String author;
        private double price;

        public Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public double getPrice() {
            return price;
        }
    }

    static class SalesTransaction {
        private Book book;
        private int quantity;

        public SalesTransaction(Book book, int quantity) {
            this.book = book;
            this.quantity = quantity;
        }

        public double getTransactionAmount() {
            return book.getPrice() * quantity;
        }

        public String getTransactionDetails() {
            return book.getTitle() + " by " + book.getAuthor() +
                    " x" + quantity + " = $" + getTransactionAmount();
        }
    }
}
