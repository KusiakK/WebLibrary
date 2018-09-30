package pl.kusiakk.weblibrary.domain.exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String s) {
        super(s);
    }
}
