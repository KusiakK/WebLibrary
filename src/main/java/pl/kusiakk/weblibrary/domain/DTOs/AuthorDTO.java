package pl.kusiakk.weblibrary.domain.DTOs;

import pl.kusiakk.weblibrary.domain.models.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthorDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String birthPlace;

    private List<BookDTO> books;

    public AuthorDTO(Author author) {
        this.id = author.getAuthorID();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.birthPlace = author.getBirthPlace();
        books = new ArrayList<>();
        author.getBooks().forEach(
                book -> this.books.add(new BookDTO(book)
                ));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
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
