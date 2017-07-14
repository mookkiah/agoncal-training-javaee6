package org.agoncal.training.javaee.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
    private EntityManager em;

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
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", "1234-5678-5678", 247, false);

        // Persists the book
        em.persist(book);
        Long id = book.getId();

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getTitle(), "H2G2");

        // Updates the book
        book.setTitle("Hitchhiker's Guide");

        // Finds the book by primary key
        book = em.find(Book.class, id);
        assertEquals(book.getTitle(), "Hitchhiker's Guide");

        // Deletes the book
        em.remove(book);

        // Checks the book has been deleted
        assertNull("Book should has been deleted", em.find(Book.class, id));
    }
}