package org.agoncal.training.javaee.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.agoncal.training.javaee.repository.BookRepository;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Antonio Goncalves, Mahendran Mookkiah
 */
@RunWith(CdiTestRunner.class)
public class BookTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private BookRepository bookRepository;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Before
    public void init() {
        
    }

    @After
    public void close() {
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateABook() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        bookRepository.save(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = bookRepository.findBy(id);
        assertEquals(book.getTitle(), "H2G2");

        // Updates the book
        book.setTitle("Hitchhiker's Guide");

        // Finds the book by primary key
        book = bookRepository.findBy(id);
        assertEquals(book.getTitle(), "Hitchhiker's Guide");

        // Deletes the book
        bookRepository.remove(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", bookRepository.findBy(id));
    }

    @Test
    public void shouldCreateABookWithTags() {

        // Creates a book with tags
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);
        List<String> tags = new ArrayList<String>();
        tags.add("scifi");
        tags.add("french");
        book.setTags(tags);

        // Persists the book
        bookRepository.save(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = bookRepository.findBy(id);
        assertEquals(book.getTitle(), "H2G2");

        // Checks the number of tags
        assertEquals(book.getTags().size(), 2);

        // Deletes the book
        bookRepository.remove(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", bookRepository.findBy(id));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateABookWithANullTitle() {

        // Creates a book with null title
        Book book = new Book(null, 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        bookRepository.save(book);
    }

    @Test
    public void shouldUpdateTheBookLanguage() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false, Language.ENGLISH);

        // Persists the book
        bookRepository.save(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = bookRepository.findBy(id);
        assertEquals(book.getContentLanguage(), Language.ENGLISH);

        // Updates the book
        book.setContentLanguage(Language.FRENCH);

        // Finds the book by primary key
        book = bookRepository.findBy(id);
        assertEquals(book.getContentLanguage(), Language.FRENCH);

        // Deletes the book
        bookRepository.remove(book);

        assertNull("Book should has been deleted", bookRepository.findBy(id));
    }
}