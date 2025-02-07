package edu.eci.cvds.tdd;

import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.Loan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library library = new Library();
    Book book = new Book("El principito", "Antoine de Saint-Exupéry", "978-987-718-117-3");
    User user = new User("Wilson Rodriguez", "wilson.r@gmail.com");

    @Test
    public void testAddBook() {
        assertTrue(library.addBook(book));
    }

    @Test
    public void testAddBookRepeated() {
        library.addBook(book);
        assertFalse(library.addBook(book));
    }

    @Test
    public void testAddBookNull() {
        assertFalse(library.addBook(null));
    }

    @Test
    public void testUseradd(){
        assertTrue(library.addUser(user));
    }
}
