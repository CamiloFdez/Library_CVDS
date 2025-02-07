package edu.eci.cvds.tdd.loan;

import edu.eci.cvds.tdd.Library.loan.Loan;
import edu.eci.cvds.tdd.Library.user.User;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTest {

    private Book libro = new Book( "El principito", "Antoine de Saint-Exupery", "9783140464079");
    private User usuario = new User("roger rodriguez","rogerrodriguez@gmail.com");

    @Test

    public void testSetAndGetBook(){
        Loan prestamo = new Loan();
        prestamo.getBook(libro);
        assertEquals(libro,prestamo.getBook(),"El libro debe de ser igual al establecido.");

    }
    @Test
    public void testSetAndGetUer(){
        Loan prestamo = new Loan();
        prestamo.getUser(usuario);
        assertEquals(usuario,prestamo.getUser(usuario),"El usuario debe ser igual al establecido.");
    }

    @Test
    public void testSetAndGetLocalDateTime(){
        LocalDateTime loanDate= LocalDateTime.now();
        Loan prestamo = new Loan();
        prestamo.getLoanDate(loanDate);
        assertEquals(loanDate,prestamo.getLoanDate(),"La fecha del prestamo debe de ser igual a la establecida.");

    }

}
