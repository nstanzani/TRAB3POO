package br.usp.icmc.ssc0103;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private String name;
    private String title;
    private int code;
    private LocalDate rentDate;
    private LocalDate devDate;

    Loan(String name, String title, LocalDate rentDate, LocalDate devDate, int code) {
        this.name = name;
        this.title = title;
        this.rentDate = rentDate;
        this.devDate = devDate;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Nome: " + this.name + "\nLivro: " + this.title + "\nData de devolucao: " + this.devDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
    }

    /**
     * Format that will be used to write to file
     */
    public String toFile() {
        return this.name + "," + this.title + "," + this.rentDate.getYear() + "," + this.rentDate.getMonthValue() + ","
                + this.rentDate.getDayOfMonth() + "," + this.devDate.getYear() + "," + this.devDate.getMonthValue() + ","
                + this.devDate.getDayOfMonth() + "," + this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public void setDevDate(LocalDate devDate) {
        this.devDate = devDate;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCode() {
        return this.code;
    }

    public LocalDate getRentDate() {
        return this.rentDate;
    }

    public LocalDate getDevDate() {
        return this.devDate;
    }

}
