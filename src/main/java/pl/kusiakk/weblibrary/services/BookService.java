package pl.kusiakk.weblibrary.services;


import pl.kusiakk.weblibrary.domain.models.Book;

import java.util.List;

public class BookService {

    private static BookService instance;
    private BookRepository bookRepository;

    private BookService() {
        bookRepository = new BookRepository();
    }

    public static BookService getInstance() {
        if (null == instance){
            instance = new BookService();
        }
        return instance;
    }

    public Boolean add(Book book) {
        return null != bookRepository.create(book);
    }

    public Boolean edit(Book book) {
        return null != bookRepository.update(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book get(int id) {
        return bookRepository.read(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
