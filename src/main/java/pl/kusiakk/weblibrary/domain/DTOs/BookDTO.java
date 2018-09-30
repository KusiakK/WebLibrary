package pl.kusiakk.weblibrary.domain.DTOs;

import pl.kusiakk.weblibrary.domain.models.Book;

import java.time.LocalDate;
import java.util.Objects;

public class BookDTO {

    private Integer id;

    private String title;

    private String category;

    private String isbn;

    private Integer pages;

    private LocalDate releaseDate;

    private String summary;

    private boolean borrowed;

    public BookDTO(Book book) {
        this.id = book.getBookID();
        this.title = book.getTitle();
        this.category = book.getCategory();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.releaseDate = book.getReleaseDate();
        this.summary = book.getSummary();
        this.borrowed = book.isBorrowed();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return borrowed == bookDTO.borrowed &&
                Objects.equals(id, bookDTO.id) &&
                Objects.equals(title, bookDTO.title) &&
                Objects.equals(category, bookDTO.category) &&
                Objects.equals(isbn, bookDTO.isbn) &&
                Objects.equals(pages, bookDTO.pages) &&
                Objects.equals(releaseDate, bookDTO.releaseDate) &&
                Objects.equals(summary, bookDTO.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, isbn, pages, releaseDate, summary, borrowed);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", isbn='" + isbn + '\'' +
                ", pages=" + pages +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", borrowed=" + borrowed +
                '}';
    }
}
