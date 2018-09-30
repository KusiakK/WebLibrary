package pl.kusiakk.weblibrary.domain.DTOs;

import org.springframework.data.util.Pair;
import pl.kusiakk.weblibrary.domain.models.Book;

import java.util.List;
import java.util.Objects;

public class AuthorDTO {

    private String firstName;

    private String lastName;

    private String birthPlace;

    private List<Pair<String, Integer>> books;

    public AuthorDTO(String firstName, String lastName, String birthPlace, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthPlace = birthPlace;
        books.forEach(
                book -> this.books.add(
                        Pair.of(book.getTitle(), book.getBookID())
                ));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<Pair<String, Integer>> getBooks() {
        return books;
    }

    public void setBooks(List<Pair<String, Integer>> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(firstName, authorDTO.firstName) &&
                Objects.equals(lastName, authorDTO.lastName) &&
                Objects.equals(birthPlace, authorDTO.birthPlace) &&
                Objects.equals(books, authorDTO.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthPlace, books);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", books=" + books +
                '}';
    }
}
