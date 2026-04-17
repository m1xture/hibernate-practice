import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LibraryTest {

    private Library lib;

    @BeforeEach
    public void initLibrary() {
        lib = new Library();
    }

    @Test
    @Tag("library")
    @Description("adding book")
    public void testAddBook() {
        Book book = new Book("some-title-here", "some-author-here");
        lib.addBook(book);
        List<Book> testBooks = new ArrayList<Book>();
        testBooks.add(book);
        assertEquals(lib.getBooks(), testBooks);
        assertDoesNotThrow(() -> lib.addBook(book));
    }

    @Test
    @Tag("library")
    @Description("adding null to library")
    public void testAddNullBook() {
        assertThrows(IllegalArgumentException.class, () -> lib.addBook(null));
    }

    @Test
    @Tag("library")
    @Description("getting all books")
    public void testGetBooks() {
        Book book = new Book("some-title-here", "some-author-here");
        List<Book> books = new ArrayList<Book>();
        books.add(book);
        lib.addBook(book);
        assertEquals(books, lib.getBooks());
    }

    @Test
    @Tag("library")
    @Description("getting all books when lib is empty")
    public void testGetBooksEmpty() {
        assertEquals(new ArrayList<Book>(), lib.getBooks());
    }

    @Test
    @Tag("library")
    @Description("counting books")
    public void testGetBookCount() {
        Book[] booksArr = {new Book("first title", "1-author-here"),
                new Book("second title", "2-author-here"),
                new Book("third title", "3-author-here")};
        for (int i = 0; i < booksArr.length; i++) {
            lib.addBook(booksArr[i]);
        }
        assertEquals(booksArr.length, lib.getBookCount());
    }

    @Test
    @Tag("library")
    @Description("counting books when the library is empty")
    public void testGetBookCountEmpty() {
        assertEquals(0, lib.getBookCount());
    }

    @Test
    @Tag("library")
    @Description("removing book from the lib")
    public void testRemoveBook() {
        Book book = new Book("some-title-here", "some-author-here");
        lib.addBook(book);
        boolean result = lib.removeBook(book);
        assertTrue(result);
        assertEquals(new ArrayList<Book>(), lib.getBooks());
    }

    @Test
    @Tag("library")
    @Description("removing null from the lib")
    public void testRemoveNullBook() {
        assertThrows(IllegalArgumentException.class, () -> lib.removeBook(null));
    }

    @Test
    @Tag("library")
    @Description("removing book that doesnt exist in the lib")
    public void testRemoveNonExistentBook() {
        Book book = new Book("some-title-here", "some-author-here");
        boolean result = lib.removeBook(book);
        assertFalse(result);
        assertEquals(0, lib.getBookCount());
    }

    @Test
    @Tag("library")
    @Description("removing one book does not affect others")
    public void testRemoveBookKeepsOthers() {
        Book firstBook = new Book("first title", "1-author-here");
        Book secondBook = new Book("second title", "2-author-here");
        lib.addBook(firstBook);
        lib.addBook(secondBook);

        lib.removeBook(firstBook);

        List<Book> expected = new ArrayList<Book>();
        expected.add(secondBook);
        assertEquals(expected, lib.getBooks());
    }
}
