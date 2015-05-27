package br.usp.icmc.ssc0103;

import java.time.LocalDate;
import java.util.Optional;

public class Book {
    private String title;
    private String author;
    private Optional<LocalDate> rentDate;
    private Optional<LocalDate> devDate;
    private Optional<String> lender;
    private boolean textBook;

    Book(String title, String author, boolean textBook){
        this.title = title;
        this.author = author;
        this.textBook = textBook;
        this.lender = Optional.empty();
        this.rentDate = Optional.empty();
        this.devDate = Optional.empty();
    }

    @Override
    public String toString(){
        if (lender.isPresent())
            return "Titulo: " + this.title + "\nAutor: " + this.author + "\nEmprestado: Sim" + "\n";
        else
            return "Titulo: " + this.title + "\nAutor: " + this.author + "\nEmprestado: Nao" + "\n";
    }

    public String toFile() {
        if (lender.isPresent())
            return this.title + "," + this.author + "," + this.textBook + "," + lender.get() + ","
                    + rentDate.get().getDayOfMonth() + "," + rentDate.get().getMonth() + "," + rentDate.get().getYear()
                    + "," + devDate.get().getDayOfMonth() + "," + devDate.get().getMonth() + "," + devDate.get().getYear() + "\n";
        else
            return this.title + "," + this.author + "," + this.textBook + ",null,null,null,null,null,null,null\n";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = Optional.of(rentDate);
    }

    public void setDevDate(LocalDate devDate) {
        this.devDate = Optional.of(devDate);
    }

    public void setLender(String lender) {
        this.lender = Optional.of(lender);
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

    public LocalDate getRentDate() {
        return rentDate.get();
    }

    public LocalDate getDevDate() {
        return devDate.get();
    }

    public String getLender() {
        if (this.lender.isPresent())
            return this.lender.get();
        return "null";
    }

    public boolean getTextBook(){
        return textBook;
    }
}

