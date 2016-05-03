package com.example.niva.exceptionclass;

/**
 * Created by Niva on 03/05/2016.
 */
public class BookStore {
    private Book [] books;
    private int howMany;
    private final int MAX_BOOKS = 10;

    public BookStore(){
        books=new Book[MAX_BOOKS];
        howMany = 0;
    }
    //------------------------------------------------------------------------------------------
    public void add(Book b) {
        if(howMany == MAX_BOOKS)
            return;
        books[howMany] = b;
        howMany++;
    }
    //------------------------------------------------------------------------------------------
    public void buy(String name) throws BookNotFoundException{
        for (int i = 0; i < howMany; i++) {
            if(books[i].getBookName().equals(name)){
                books[i] = books[howMany - 1];
                books[howMany - 1] = null;
                howMany--;
                return;
            }
        }

        //when will we get here?
        throw new BookNotFoundException();
    }
    //------------------------------------------------------------------------------------------
    public Book[] booksByAuthor(String name) throws AuthorNotFoundException{
        Book[] bookBy=new Book[MAX_BOOKS];
        for (int i = 0, j = 0; i < howMany; i++) {
            if(books[i] != null && books[i].getBookAuthor().equals(name)){
                bookBy[j] = books[i];
                j++;
            }
        }
        if(bookBy[0] == null)
            throw new AuthorNotFoundException();
        return bookBy;
    }
    //------------------------------------------------------------------------------------------
    public String toString() {
        if(howMany == 0)
            return "No Books. \n";
        String str="Books Available: \n";
        for (int i = 0; i < howMany; i++) {
            str+=books[i].toString()+" \n";
        }

        return str;
    }
}
