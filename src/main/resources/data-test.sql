use librarytest;

insert into authors (id_author, first_name, last_name)
VALUES (1, 'J.R.R.', 'Tolkien');

insert into authors (id_author ,first_name, last_name)
VALUES (2, 'William', 'Gibson');

insert into books (id_books, is_borrowed, isbn, title, author_id_author)
values (1, false, 9780007488315, 'The Fellowship of the Ring', 1);

insert into books (id_books, is_borrowed, isbn, title, author_id_author)
values (2, false, 9780261102385, 'The Lord of the Rings', 1);