package edu.eci.cvds.tdd.loan;

import edu.eci.cvds.tdd.library.book.Book;

import edu.eci.cvds.tdd.library.loan.*;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTest {

    private  Book libro = new Book("El principito", "Antoine de Saint-Exupery", "9783140464079");
    private  User usuario = new User("roger rodriguez","rogerrodriguez@gmail.com");

    @Test
    public void testSetAndGetBook(){
        Loan prestamo = new Loan();
        prestamo.setBook(libro);
        assertEquals(libro,prestamo.getBook(),"El libro debe de ser igual al establecido.");

    }

    @Test
    public void testSetAndGetUer(){
        Loan prestamo = new Loan();
        prestamo.setUser(usuario);
        assertEquals(usuario,prestamo.getUser(usuario),"El usuario debe ser igual al establecido.");
    }

    @Test
    public void testSetAndGetLocalDateTime() {
        LocalDateTime loanDate = LocalDateTime.now();
        Loan prestamo = new Loan();
        prestamo.setLoanDate(loanDate);
        assertEquals(loanDate, prestamo.getLoanDate(), "La fecha del prestamo debe de ser igual a la establecida.");
    }

    @Test
    public void testSetAndGetLoanStatus(){
        Loan prestamo = new Loan();
        LoanStatus status = LoanStatus.ACTIVE;
        prestamo.setStatus(status);
        assertEquals(status,prestamo.getStatus(),"El estado del prestamo deberia ser igual a ACTIVE.");
    }

    @Test
    public void testSetAndGetReturnDate(){
        LocalDateTime returnDate = LocalDateTime.now();
        Loan prestamo = new Loan();
        prestamo.setReturnDate(returnDate);
        assertEquals(returnDate,prestamo.getReturnDate(),"La fecha de retorno deberia ser igual a cuando se establecio.");
    }
}
