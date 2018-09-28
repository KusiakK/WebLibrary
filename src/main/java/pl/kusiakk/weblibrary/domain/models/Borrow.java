package pl.kusiakk.weblibrary.domain.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @Column(name = "id_borrow")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowID;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userID;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    public Borrow() {
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public Book getBookID() {
        return bookID;
    }

    public void setBookID(Book book) {
        this.bookID = book;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User borrower) {
        this.userID = borrower;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(borrowID, borrow.borrowID) &&
                Objects.equals(bookID, borrow.bookID) &&
                Objects.equals(userID, borrow.userID) &&
                Objects.equals(rentalDate, borrow.rentalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowID, bookID, userID, rentalDate);
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrowID=" + borrowID +
                ", book=" + bookID +
                ", borrower=" + userID +
                ", rentalDate=" + rentalDate +
                '}';
    }
}
