package br.usp.icmc.ssc0103;

public class Book {
    private String title;
    private String author;
    private Date rentDate;
    private Date devDate;
    private String lender;
    private boolean textBook;

    Book(String title, String author, boolean textBook){
        this.title = title;
        this.author = author;
        this.textBook = textBook;
    }

    @Override
    public String toString(){
        return "Titulo: " + this.title + "\nAutor: " + this.author;
    }

    public String toFile() {
        return this.title + "," + this.author + "," + this.textBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setDevDate(Date devDate) {
        this.devDate = devDate;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public void setTextBook(boolean textBook) {
        this.textBook = textBook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getDevDate() {
        return devDate;
    }

    public String getLender() {
        return lender;
    }

    public boolean getTextBook(){
        return textBook;
    }
}

