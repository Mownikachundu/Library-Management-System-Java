import model.Book;
import model.User;
import service.BookService;
import service.UserService;
import service.BorrowService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        UserService userService = new UserService();
        BorrowService borrowService = new BorrowService();

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    bookService.addBook(new Book(0, title, author, true));
                    break;

                case 2:
                    List<Book> books = bookService.getAllBooks();
                    for (Book b : books)
                        System.out.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + (b.isAvailable() ? "Available" : "Not Available"));
                    break;

                case 3:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    userService.addUser(new User(0, name, email));
                    break;

                case 4:
                    System.out.print("User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();
                    borrowService.borrowBook(uid, bid);
                    bookService.updateBookAvailability(bid, false);
                    break;

                case 5:
                    System.out.print("Record ID: ");
                    int rid = sc.nextInt();
                    borrowService.returnBook(rid);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
