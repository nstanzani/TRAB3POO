package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public class Loan {
    private User user;
    private Book book;

    Loan(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public String getName() {
        return user.getName();
    }
    @Override
    public String toString(){
        return "Nome: " + user.getName() + "\nLivro: " + book.getTitle() + "\n";
    }

    public String toFile() {
        return user.getName() + "," + book.getTitle() + "\n";
    }
}
