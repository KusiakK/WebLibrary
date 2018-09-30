package pl.kusiakk.weblibrary.domain.exceptions;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException() {
        super();
    }

    public AuthorNotFoundException(String s) {
        super(s);
    }
}
