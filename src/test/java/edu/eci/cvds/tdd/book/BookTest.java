package edu.eci.cvds.tdd.book;

import edu.eci.cvds.tdd.library.book.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book libro = new Book("El principito", "Antoine de Saint-Exuperv", "9783140464079");

    @Test
    public void testGetTittle() {
        assertEquals("El principito", libro.getTittle(), "Tiulo:'El principito'");
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Antoine de Saint-Exuperv", libro.getAuthor(), "Autor: 'Antoine de Saint-Exuperv'");
    }

    @Test
    public void testGetIsbn() {
        assertEquals("9783140464079", libro.getIsbn(), "ISBN: '9783140464079'");
    }
}

