package edu.eci.cvds.tdd.user;

import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {

    private User usuario = new User("Sergio Andres Camacho", "sergio.camacho@gmail.com");

    @Test
    public void testGetUsername(){
        assertEquals("Sergio Andres Camacho", usuario.getName(), "El nombre del usuario deberia ser Sergio Andres Camacho.");
    }

    @Test
    public void testGetId() {
        assertEquals("sergio.camacho@gmail.com", usuario.getId(), "El id del usuario deberia ser sergio.camacho@gmail.com");
    }

    @Test
    public void testSetUsername(){
        usuario.setName("Camilo Fernandez");
        assertEquals("Camilo Fernandez", usuario.getName(), "El nombre del usuario deberia ser Camilio Fernandez.");
    }

    @Test
    public void testSetId() {
        usuario.setId("camilo.fernandez@gmail.com");
        assertEquals("camilo.fernandez@gmail.com", usuario.getId(), "El id del usuario deberia ser camilo.fernandez@gmail.com");
    }
}
