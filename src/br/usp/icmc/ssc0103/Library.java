package br.usp.icmc.ssc0103;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Library {
    int nextCode = 0;
    static List<User> userList = new LinkedList<User>();
    static List<Book> bookList = new LinkedList<Book>();
    static List<Loan> loanList = new LinkedList<Loan>();

    public void loadLists() {
        try {
            BufferedReader inUser = new BufferedReader(new FileReader("users.csv"));
            BufferedReader inBook = new BufferedReader(new FileReader("books.csv"));
            String line;
            int penalty, quota, time, code;
            while ((line = inUser.readLine()) != null) {
                String[] splitLine = line.split(",");
                quota = Integer.parseInt(splitLine[1]);
                time = Integer.parseInt(splitLine[2]);
                penalty = Integer.parseInt(splitLine[3]);
                code = Integer.parseInt(splitLine[5]);
                if (splitLine[4].toLowerCase().equals("estudante")) {
                    Student user = new Student(splitLine[0], quota, time, code);
                    user.setPenalty(penalty);
                    userList.add(user);
                    nextCode++;
                }
                if (splitLine[4].toLowerCase().equals("professor")) {
                    Professor user = new Professor(splitLine[0], quota, time, code);
                    user.setPenalty(penalty);
                    userList.add(user);
                    nextCode++;
                } else if (splitLine[4].toLowerCase().equals("comunidade")) {
                    Community user = new Community(splitLine[0], quota, time, code);
                    user.setPenalty(penalty);
                    userList.add(user);
                    nextCode++;
                }
            }
            while ((line = inBook.readLine()) != null) {
                String[] splitLine = line.split(",");
                Book book;
                if (splitLine[2].toLowerCase().equals("false")) {
                    book = new Book(splitLine[0], splitLine[1], false);
                    bookList.add(book);
                } else if (splitLine[2].toLowerCase().equals("true")) {
                    book = new Book(splitLine[0], splitLine[1], true);
                    bookList.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
        }
    }

    public void menu() {
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
                    break;
                case 3:
                    registerNewLoan();
                    break;
                case 4:
                    //registerNewDev();
                    break;
                case 5:
                    saveChanges();
                    break;
                case 6:
                    User u = null;
                    printAll(u);
                    break;
                case 7:
                    Book b = null;
                    printAll(b);
                    break;
                case 8:
                    Loan l = null;
                    printAll(l);
                    break;
                case 0:
                    System.exit(0);
            }

        } while (op != 0);

    }

    private void registerNewLoan() {
        Optional<Book> book;
        Optional<User> user;
        Scanner scanner = new Scanner(System.in);
        String bookTitle;
        int userCode;

        System.out.println("Digite o nome do livro que o usuario deseja pegar emprestado: ");
        bookTitle = scanner.nextLine();
        book = bookList
                .stream()
                .filter(b -> b.getTitle().toLowerCase().equals(bookTitle.toLowerCase()))
                .findFirst();

        System.out.println("Digite o codigo do usuario que deseja fazer o emprestimo: ");
        userCode = scanner.nextInt();

        user = userList
                .stream()
                .filter(u -> u.getCode() == userCode)
                .findFirst();

        if (user.isPresent() && book.isPresent()) {
            Loan loan = new Loan(user.get().getName(), book.get().getTitle());
            loanList.add(loan);
        }
    }

    private void registerNewUser() {

        Scanner scanner = new Scanner(System.in);
        String name;
        String userType;

        System.out.println("Digite o nome do usuário: ");
        name = scanner.nextLine();
        System.out.println("Digite o tipo de conta do usuário (estudante, professor ou comunidade): ");
        userType = scanner.nextLine();

        if (userType.toLowerCase().equals("estudante")) {
            Student user = new Student(name, 4, 15, nextCode);
            userList.add(user);
            nextCode++;
        } else if (userType.toLowerCase().equals("professor")) {
            Professor user = new Professor(name, 6, 60, nextCode);
            userList.add(user);
            nextCode++;
        } else if (userType.toLowerCase().equals("comunidade")) {
            Community user = new Community(name, 2, 15, nextCode);
            userList.add(user);
            nextCode++;
        } else {
            System.out.println("Tipo de usuário inválido!");
        }
    }

    private void printAll(User user) {
        userList
                .stream()
                .sorted(Comparator.comparing(User::getCode))
                .forEach(System.out::println);
    }

    private void printAll(Book book) {
        bookList
                .stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    private void printAll(Loan book) {
        loanList
                .stream()
                .sorted(Comparator.comparing(Loan::getName))
                .forEach(System.out::println);
    }

    private void saveChanges() {
        Thread t = new Thread(() ->{
            try {
                ListIterator userItr = userList.listIterator();
                ListIterator bookItr = bookList.listIterator();
                FileWriter fwUser = new FileWriter("users.csv");
                FileWriter fwBook = new FileWriter("books.csv");

                while (userItr.hasNext()) {
                    User user = (User) userItr.next();
                    fwUser.write(user.toFile());
                }
                while (bookItr.hasNext()) {
                    Book book = (Book) bookItr.next();
                    fwBook.write(book.toFile());
                }
                fwUser.close();
                fwBook.close();
                menu();
            }
            catch (IOException e){
                System.out.println("Erro ao abrir o arquivo");
            }});
        t.start();
    }

    private void registerNewBook() {
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
        System.out.println("É um livro texto? (Sim/Nao)");
        auxString = scanner.nextLine().toLowerCase();

        textBook = auxString.toLowerCase().equals("sim");

        book = new Book(title, author, textBook);
        bookList.add(book);
    }
}