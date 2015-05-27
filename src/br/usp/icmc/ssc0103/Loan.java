package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public class Loan {
    private String name;
    private String book;

    Loan(String name, String book) {
        this.name = name;
        this.book = book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public String getBook() {
        return book;
    }
    @Override
    public String toString(){
        return "Nome: " + name + "\nLivro: " + book + "\n";
    }

    public String toFile() {
        return name + "," + book + "\n";
    }
}
