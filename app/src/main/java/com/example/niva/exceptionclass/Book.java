package com.example.niva.exceptionclass;

/**
 * Created by Niva on 03/05/2016.
 */
public class Book {
    private String bookName, bookAuthor;
    private int price;
    private final static int MAX_PRICE = 200, INIT_PRIVE = 100;

    public Book(String name, String author){
        bookName=name;
        bookAuthor=author;
        price=INIT_PRIVE;
    }
    //------------------------------------------------------------------------------------------
    public String getBookName() {return bookName;}
    //------------------------------------------------------------------------------------------
    public String getBookAuthor() {return bookAuthor;}
    //------------------------------------------------------------------------------------------
    public int getPrice() {return price;}
    //------------------------------------------------------------------------------------------
    public void setPrice(int price) throws IllegalPriceException {
        if(price<=0 || price>MAX_PRICE)
            throw new IllegalPriceException();
        this.price = price;
    }
    //------------------------------------------------------------------------------------------
    public String toString() {
        return "Name Of Book: ["+getBookName()+"], Name Of Author: ["+getBookAuthor()+"], Price: ["+getPrice()+"]";
    }
}
