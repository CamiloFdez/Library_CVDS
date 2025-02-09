# Integrantes
- Camilo Andres Fernandez Diaz
- Roger Alexander Rodriguez Abri

# Crearcion del proyecto

Creamos el proyecto siguiendo los siguientes requerimientos:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/requerimientos.png)

Construimos el proyecto utilizando maven:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/buildProyect.png)

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/proyecto_mvn.png)

Agregamos las dependencias JUNIT5:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/properties.png)

Ya en el proyecto tendriamos:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/creacion_proyecto.png)

compilamos para asegurarnos que todo esta funcionando luego de agregar las prpiedades:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/mvnCompile.png)

## Esqueleto del proyecto
Siguiendo las siguientes especificaciones :

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/treeRequerimientos.png)

ya implementadas dichas especificaciones tendriamos :

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/treeProyecto.png)

## Clases

Luego de haber creado el esqueleto del proyecto y teniendo los paquetes implementaremos las clases a usar en el mismo  (teniendo en cuenta que cada clase debe de tener su respectiva clase de pruebas en el paquete test):

### Book

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Book.png)

### Loan y LoanStatus

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Loan.png)

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/LoanStatus.png)

### User

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/User.png)

### Library

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Library.png)

Para esta clase tendriamos el codigo completo:

```java
package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        //TODO Implement the logic to add a new book into the map.
        return false;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                for (Book book : books.keySet()) {
                    if (book.getIsbn().equals(isbn) && books.get(book) > 0) {
                        Loan loan = new Loan(book, user, LocalDateTime.now());
                        loans.add(loan);
                        books.put(book, books.get(book) - 1);
                        return loan;
                    }
                }
            }
        }
        return null;
    }
}

/**
 * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
 * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
 * date should be the current date, validate that the loan exist.
 *
 * @param loan loan to return.
 *
 * @return the loan with the RETURNED status.
 */
public Loan returnLoan(Loan loan) {
    if (loans.contains(loan)) {
        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        books.put(loan.getBook(),books.get(loan.getBook())+1);
        return loan;
    }
    return null;
}

public boolean addUser(User user) {
    return users.add(user);
}

```
compilamos para verificar que todo este bien:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/mvnPakage.png)

## Pruebas Unitarias y TDD

Para realizar las pruebas debemos de contar con varios aspectos para que todo funcione correctamente.

### JUnit

Agragamos la version de JUnit a usar para automatizar la ejecucion de las  pruebas:

   ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Junit_version.png)

### JaCoCo

Agregamos la herramienta de JaCoCo para verificar que partes del codigo estan cubiertas por las pruebas ademas del valor de la cobertura que deben cumplir como minimo las pruebas para que el proyecto se pueda construir esto lo agregamos en el pom.xml:

```xml
      <build>
        <plugins>
          <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.12</version>
            <executions>
              <execution>
                <goals>
                  <goal>prepare-agent</goal>
                </goals>
              </execution>
              <execution>
                <id>report</id>
                <phase>test</phase>
                <goals>
                  <goal>report</goal>
                </goals>
                <configuration>
                  <excludes>
                    <exclude>/configurators/</exclude>
                  </excludes>
                </configuration>
              </execution>
              <execution>
                <id>jacoco-check</id>
                <goals>
                  <goal>check</goal>
                </goals>
                <configuration>
                  <rules>
                    <rule>
                      <element>PACKAGE</element>
                      <limits>
                        <limit>
                          <counter>CLASS</counter>
                          <value>COVEREDRATIO</value>
                          <minimum>0.85</minimum><!--Porcentaje mínimo de cubrimiento para construir el proyecto-->
                        </limit>
                      </limits>
                    </rule>
                  </rules>
                </configuration>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </build>
```

### Clases de pruebas 

Ahora para cada clase crearemos su respectiva clase de pruebas en el paquete de Test:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/treeTest.png)

#### BookTest

```java
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
```

### LoanTest

```java
    package edu.eci.cvds.tdd.loan;

import edu.eci.cvds.tdd.library.book.Book;

import edu.eci.cvds.tdd.library.loan.*;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTest {

    private Book libro = new Book("El principito", "Antoine de Saint-Exupery", "9783140464079");
    private User usuario = new User("roger rodriguez", "rogerrodriguez@gmail.com");

    @Test
    public void testSetAndGetBook() {
        Loan prestamo = new Loan();
        prestamo.setBook(libro);
        assertEquals(libro, prestamo.getBook(), "El libro debe de ser igual al establecido.");

    }

    @Test
    public void testSetAndGetUer() {
        Loan prestamo = new Loan();
        prestamo.setUser(usuario);
        assertEquals(usuario, prestamo.getUser(), "El usuario debe ser igual al establecido.");
    }

    @Test
    public void testSetAndGetLocalDateTime() {
        LocalDateTime loanDate = LocalDateTime.now();
        Loan prestamo = new Loan();
        prestamo.setLoanDate(loanDate);
        assertEquals(loanDate, prestamo.getLoanDate(), "La fecha del prestamo debe de ser igual a la establecida.");
    }

    @Test
    public void testSetAndGetLoanStatus() {
        Loan prestamo = new Loan();
        LoanStatus status = LoanStatus.ACTIVE;
        prestamo.setStatus(status);
        assertEquals(status, prestamo.getStatus(), "El estado del prestamo deberia ser igual a ACTIVE.");
    }

    @Test
    public void testSetAndGetReturnDate() {
        LocalDateTime returnDate = LocalDateTime.now();
        Loan prestamo = new Loan();
        prestamo.setReturnDate(returnDate);
        assertEquals(returnDate, prestamo.getReturnDate(), "La fecha de retorno deberia ser igual a cuando se establecio.");
    }
}

```

### UserTest

```java
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

```

### LibraryTest

```java
    package edu.eci.cvds.tdd;

import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
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
    public void testLoanABookSuccess() {
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertEquals(book, loan.getBook());
        assertEquals(user, loan.getUser());
    }
    @Test
    public void testReturnLoanSuccess() {
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan);
        Loan returnedLoan = library.returnLoan(loan);
        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
    }

    @Test
    public void testLoanABookUserNotFound() {
        library.addBook(book);
        Loan loan = library.loanABook("nonexistent-user", book.getIsbn());
        assertNull(loan);
    }

    @Test
    public void testLoanABookBookNotFound() {
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(), "nonexistent-isbn");
        assertNull(loan);
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

```
## SONARQUBE

Utilizaremos SONARQUBE para hacer un analisis estatico del codigo , para esto necesitamos tener Doker por lo cual a continuacion descargamos la imagen de doker:

- Para intaler y ejecutar SONARQUBE instalaremos Ubuntu:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/installUbuntu.png)

- Instalamos doker para poder ejecutar y gestionar la aplicacion en un contenedor :

    Buscamos en el navegador el installador de doker :

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerGoogle.png)

    En la pagina oficial descargamos el paquete de instalacion de doker:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/installDokerPage.png)

    Una vez descargado Inicializamos la instalacion en el equipo:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerInitializingInstall.png)

    Se desempacan los archivos necesarios para la intalacion y ejecucion de doker:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerUnpakingFiles.png)
    
    Se completara la instalacion:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerInstallationSucceded.png)

    con esto ya podemos ejecutar el comando para descargar la imagen de doker:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/imagenDoker.png)

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/imagenDokerFirewall.png)

- Arrancamos el servicio de SONARQUBE con el comando ```docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest```:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerArranque.png)

- Validamos el funcionamiento utilizando el comando ```doker ps -a```:

    ![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/dokerValidacion.png)

- Iniciamos sesion en sonar ```localhost:9000``` , cambiamos la contraseña la cual por defecto es usuario y el usuario es admin:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarLog.png)

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarUpdatePasword.png)

- Entramos a las opciones de la cuenta para generar un token:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarToken.png)

- Instalamos sonarLint en el IDE que en nuestro caso es IntelliJ :

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarLintIDE.png)

- Ahora añadimos el plugin de Sonar al pom del proyecto esto usando :

```xml
<plugin>
    <groupId>org.sonarsource.scanner.maven</groupId>
     <artifactId>sonar-maven-plugin</artifactId>
    <version>4.0.0.4121</version>
</plugin>
```
Implementado :

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarPlugin.png)

- Ahora implementamos las propiedades de Sonar en el pom del proyecto usando:

```xml
    <sonar.projectKey>library</sonar.projectKey>
    <sonar.projectName>library</sonar.projectName>
    <sonar.host.url>http://localhost:9000</sonar.host.url>
    <sonar.coverage.jacoco.xmlReportPaths>target/site/jacoco/jacoco.xml</sonar.coverage.jacoco.xmlReportPaths>
    <sonar.coverage.exclusions>src//configurators/*</sonar.coverage.exclusions>
```
Implementado:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarProperties.png)

- Generamos el reporte con JaCoCo con el cubrimento adecuado corregido:

- Generamos la integracion con sonar usando el comando ```mvn verify sonar:sonar -D sonar.token=[TOKEN_GENERADO```:

![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarIntegrate1.png)
![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/sonarIntegrate2.png)

- Una vez generado el reporte lo visualizamos en la pagina de sonar:
![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Sonarproject.png)

- Ahora para finalizar esperamos un poco y el reporte desde la pagina de Sonar se nos vera de essta manera la cual nos dara el estado del proyecto:
![imagen](https://github.com/CamiloFdez/Library_CVDS/blob/master/assets/Sonarfinal.png)



    













