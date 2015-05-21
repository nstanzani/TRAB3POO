package br.usp.icmc.ssc0103;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

/**
 * Created by naldost on 19/05/15.
 */
public class Library {

    static List<User> userList = new LinkedList<User>();
    static List<Book> bookList = new LinkedList<Book>();

    public static void menu() throws Exception{

        int op;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\t\tMenu\n");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Cadastrar novo livro");
            System.out.println("3 - Registrar empréstimo");
            System.out.println("4 - Registrar devolução");
            System.out.println("5 - Salvar alterações");
            System.out.println("6 - Imprimir todos os usuários");
            System.out.println("7 - Imprimir todos os livros");
            System.out.println("8 - Imprimir todos os empréstimos");
            System.out.println("0 - Sair");

            op = scanner.nextInt();

            switch (op) {
                case 1:
                    registerNewUser();
                    break;
                case 2:
                    registerNewBook();
                    //System.out.println(bookList);
                    break;
                case 3:
                    //registerNewLoan();
                    break;
                case 4:
                    //registerNewDev();
                    break;
                case 5:
                    saveChanges(userList);
                    break;
                case 6:
                    printUserList(userList);
                    //printAllUsers();
                    break;
                case 7:
                    printBookList(bookList);
                    //printAllBooks();
                    break;
                case 8:
                    //printAllLoans();
                    break;
                case 0:
                    //saveChanges();
                    //System.out.print("\033[H\033[2J");
                    System.exit(0);
            }

        } while (op != 0);
    }

    public static void registerNewUser() {

        Scanner scanner = new Scanner(System.in);
        String name;
        String userType;

        System.out.println("Digite o nome do usuário: ");
        name = scanner.nextLine();
        System.out.println("Digite o tipo de conta do usuário: ");
        userType = scanner.nextLine();

        if (userType.equals("Student")) {
            Student user = new Student(name, 4, 15);
            userList.add(user);
        } else if (userType.equals("Professor")) {
            Professor user = new Professor(name, 6, 60);
            userList.add(user);
        } else if (userType.equals("Community")) {
            Community user = new Community(name, 2, 15);
            userList.add(user);
        } else {
            System.out.println("Tipo de usuário inválido!");
            return;
        }
    }

    public static void printUserList(List list) {
        //System.out.println(list);

        list
                .stream()
                .sorted(Comparator.comparing(User::getName))
                .forEach(System.out::println);
    }

    public static void saveChanges(List list){
        Thread t = new Thread(() ->{
            try {
                ListIterator itr = list.listIterator();
                File f = new File("users.txt");
                FileWriter fw = new FileWriter(f, true);

                while (itr.hasNext()) {
                    User user = (User) itr.next();
                    System.out.println(user);
                    fw.write(user.toString() + "\n");
                }
                fw.close();
            }
            catch (IOException e){
                System.out.println("sjfshfhs");
            }});
        t.start();
    }

    public static void registerNewBook() {
        Scanner scanner = new Scanner(System.in);
        String title;
        String author;
        String auxString;
        boolean textBook;
        Book book;

        System.out.println("Digite o título do livro: ");
        title = scanner.nextLine();
        System.out.println("Digite o nome do autor: ");
        author = scanner.nextLine();
        System.out.println("É um livro texto? (Sim/Não)");
        auxString = scanner.nextLine().toLowerCase();

        //System.out.println("Passou das entradas");

        if(auxString.equals("sim"))
            textBook = true;
        else
            textBook = false;

        //System.out.println("Saiu dos ifs");

        book = new Book(title, author, textBook);
        System.out.println(book);
        bookList.add(book);
    }

    public static void printBookList (List list) {

        list
                .stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    public static void loadPreviousData () {
        try {
            CSVReader reader = new CSVReader(new FileReader("users.txt"));
            String [] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        }
        catch (IOException e) {
            System.out.println("Error to open file!");
        }

    }

    public static void main(String[] args) {
        loadPreviousData();
        try {
            menu();
        }
        catch(Exception e){}
    }
}