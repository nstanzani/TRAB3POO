package br.usp.icmc.ssc0103;

public class Main {

    /**
     * Main method that will create a new Library System
     * and load previous file data
     */
    public static void main(String[] args) {
        Library lib = new Library();
        lib.setSimulatedDate();
        lib.loadLists();
        lib.menu();
    }
}
