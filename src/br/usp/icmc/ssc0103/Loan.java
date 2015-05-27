package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public class Loan {
    private String name;
    private String book;
    private Date rentDate;
    private Date devDate;

    @Override
    public String toString(){
        return "Nome: " + name + "Livro: " + book + "Data de empréstimo" +
                rentDate.toString() + "Data de devolução" + devDate.toString();
    }
}
