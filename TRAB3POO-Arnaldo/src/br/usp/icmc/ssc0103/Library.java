package br.usp.icmc.ssc0103;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Library {
    int nextCode = 0;
    LocalDate today;
    static List<User> userList = new LinkedList<User>();
    static List<Book> bookList = new LinkedList<Book>();
    static List<Loan> loanList = new LinkedList<Loan>();

    /**
     * Alows to set a specific date to simulation
     */
    public void setSimulatedDate() {
        Scanner scanner = new Scanner(System.in);
        int dia, mes, ano;
        System.out.println("Digite o dia que a simulação deve ser executada (formato numerico): ");
        dia = scanner.nextInt();
        System.out.println("Digite o mes que a simulação deve ser executada (formato numerico): ");
        mes = scanner.nextInt();
        System.out.println("Digite o ano que a simulação deve ser executada (formato numerico): ");
        ano = scanner.nextInt();
        today = LocalDate.of(ano, mes, dia);
    }

    /**
     * Read all data from files and stores it in linked lists
     * to manipulate in RAM
     */
    public void loadLists() {
        try {
            BufferedReader inUser = new BufferedReader(new FileReader("users.csv"));
            BufferedReader inBook = new BufferedReader(new FileReader("books.csv"));
            BufferedReader inLoan = new BufferedReader(new FileReader("loans.csv"));
            String[] splitLine;
            String line;
            Optional<LocalDate> aux;
            while ((line = inUser.readLine()) != null) {
                splitLine = line.split(",");
                if (splitLine[3].equals("null")) {
                    aux = Optional.empty();
                } else if (today.isAfter(LocalDate.of(Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5])))) {
                    aux = Optional.empty();
                } else {
                    aux = Optional.of(LocalDate.of(Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5])));
                }
                if (splitLine[6].toLowerCase().equals("estudante")) {
                    Student user = new Student(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[7]));
                    user.setPenalty(aux);
                    userList.add(user);
                    nextCode++;
                }
                if (splitLine[6].toLowerCase().equals("professor")) {
                    Professor user = new Professor(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[7]));
                    user.setPenalty(aux);
                    userList.add(user);
                    nextCode++;
                } else if (splitLine[6].toLowerCase().equals("comunidade")) {
                    Community user = new Community(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[7]));
                    user.setPenalty(aux);
                    userList.add(user);
                    nextCode++;
                }
            }
            while ((line = inBook.readLine()) != null) {
                splitLine = line.split(",");
                Book book;
                book = new Book(splitLine[0], splitLine[1], splitLine[2].toLowerCase().equals("true"));
                if (splitLine[3].toLowerCase().equals("null") != true) {
                    book.setLender(Optional.of(splitLine[3]));
                    book.setRentDate(Optional.of(LocalDate.of(Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5]), Integer.parseInt(splitLine[6]))));
                    book.setDevDate(Optional.of(LocalDate.of(Integer.parseInt(splitLine[7]), Integer.parseInt(splitLine[8]), Integer.parseInt(splitLine[9]))));
                }
                bookList.add(book);
            }
            while ((line = inLoan.readLine()) != null) {
                splitLine = line.split(",");
                LocalDate rentDate = LocalDate.of(Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]));
                LocalDate devDate = LocalDate.of(Integer.parseInt(splitLine[5]), Integer.parseInt(splitLine[6]), Integer.parseInt(splitLine[7]));
                Loan loan;
                loan = new Loan(splitLine[0], splitLine[1], rentDate, devDate, Integer.parseInt(splitLine[8]));
                loanList.add(loan);
            }
        } catch (IOException e) {
            System.out.println("Arquivo ainda não inicializado!");
        }
    }

    /**
     * Prints the menu and calls the right method for each choice
     */
    public void menu() {
        int op;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Data do sistema: " + today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("\t\tMenu\n");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Cadastrar novo livro");
            System.out.println("3 - Registrar empréstimo");
            System.out.println("4 - Registrar devolução");
            System.out.println("5 - Salvar alterações");
            System.out.println("6 - Imprimir todos os usuários");
            System.out.println("7 - Imprimir todos os livros");
            System.out.println("8 - Imprimir todos os empréstimos");
            System.out.println("9 - Imprimir os empréstimos em atraso");
            System.out.println("10 - Alterar data do sistema");
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
                    registerNewDev();
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
                case 9:
                    checkLate();
                    break;
                case 10:
                    setSimulatedDate();
                    break;
                case 0:
                    System.out.println("Sair pode significar perda de dados. Antes de sair, certifique-se de salvar as modificações. Digite 0 se realmente quiser sair");
                    if (scanner.nextInt() == 0)
                        System.exit(0);
                    break;
            }

        } while (op != 0);

    }

    /**
     * Register a new loan for a specified user
     */
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
                .filter(b -> b.getLender().toLowerCase().equals("null"))
                .findFirst();

        System.out.println("Digite o codigo do usuario que deseja fazer o emprestimo: ");
        userCode = scanner.nextInt();

        user = userList
                .stream()
                .filter(u -> u.getCode() == userCode)
                .filter(u -> u.getPenalty().isAfter(today) != true)
                .filter(u -> u.getRemainingQuota() != 0)
                .findFirst();

        if (user.isPresent() && book.isPresent()) {

            if (user.get().getType().toLowerCase().equals("comunidade") && book.get().getTextBook()) {
                System.out.println("O usuario nao tem permissao suficiente para pegar esse livro emprestado");
            } else {
                Loan loan = new Loan(user.get().getName(), book.get().getTitle(), today, today.plusDays(user.get().getTime()), user.get().getCode());
                book.get().setLender(Optional.of(user.get().getName()));
                book.get().setRentDate(Optional.of(today));
                book.get().setDevDate(Optional.of(today.plusDays(user.get().getTime())));
                user.get().decRemainingQuota();
                loanList.add(loan);
                System.out.println("Empréstimo realizado com sucesso em nome de: " + user.get().getName());
            }
        } else if (user.isPresent() != true) {
            System.out.println("O usuario nao foi encontrado, ou nao tem cota disponivel ou tem uma penalidade pendente");
        } else if (book.isPresent() != true) {
            System.out.println("Nao foi encontrado nenhum livro com tal titulo disponivel para emprestimo");
        }
    }

    /**
     * Register a book devolution
     */
    private void registerNewDev() {
        try {
            FileWriter fw = new FileWriter("closedloans.csv", true);
            Scanner scanner = new Scanner(System.in);
            int code;
            String title, name;
            Optional<Book> book = Optional.empty();
            Optional<Loan> loan = Optional.empty();
            Optional<User> user = Optional.empty();

            System.out.println("Escreva o codigo do usuario que ira devolver o livro");
            code = Integer.parseInt(scanner.nextLine());
            System.out.println("Escreva o titulo do livro que o usuario ira devolver");
            title = scanner.nextLine();

            loan = loanList
                    .stream()
                    .filter(l -> l.getCode() == code)
                    .filter(l -> l.getTitle().toLowerCase().equals(title.toLowerCase()))
                    .findFirst();

            user = userList
                    .stream()
                    .filter(u -> u.getCode() == code)
                    .findFirst();

            if (user.isPresent()) {
                name = user.get().getName();
            } else {
                System.out.println("Nao foi possivel encontrar nenhum usuario com esse codigo");
                fw.close();
                return;
            }

            book = bookList
                    .stream()
                    .filter(b -> b.getTitle().toLowerCase().equals(title.toLowerCase()))
                    .filter(b -> b.getLender().toLowerCase().equals(name.toLowerCase()))
                    .findFirst();

            if (loan.isPresent() && book.isPresent()) {
                if (today.isAfter(loan.get().getDevDate())) {
                    long dif = ChronoUnit.DAYS.between(loan.get().getDevDate(), today);
                    user.get().calculatePenalty(today, dif);
                    System.out.println("O livro estava atrasado, por isso foi adicionado uma penalidade ao usuario");
                }
                fw.write(loan.get().toFile() + "\n");
                loanList.remove(loan.get());
                book.get().setLender(Optional.empty());
                book.get().setDevDate(Optional.empty());
                book.get().setRentDate(Optional.empty());
                user.get().incRemainingQuota();
                System.out.println("Devolução do livro: " + book.get().getTitle() + " realizada com sucesso");
            } else if (loan.isPresent() != true) {
                System.out.println("Nao foi encontrado nenhum emprestimo com esse codigo e esse livro");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Arquivo ainda não inicializado!");
        }
    }

    /**
     * Register a new user with the specified account type
     */
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
            System.out.println("Usuário cadastrado com sucesso, código de cadastro: " + nextCode);
            nextCode++;
        } else if (userType.toLowerCase().equals("professor")) {
            Professor user = new Professor(name, 6, 60, nextCode);
            userList.add(user);
            System.out.println("Usuário cadastrado com sucesso, código de cadastro: " + nextCode);
            nextCode++;
        } else if (userType.toLowerCase().equals("comunidade")) {
            Community user = new Community(name, 2, 15, nextCode);
            userList.add(user);
            System.out.println("Usuário cadastrado com sucesso, código de cadastro: " + nextCode);
            nextCode++;
        } else {
            System.out.println("Tipo de usuário inválido!");
        }
    }

    /**
     * Overload: Prints all users
     */
    private void printAll(User user) {
        userList
                .stream()
                .sorted(Comparator.comparing(User::getCode))
                .forEach(System.out::println);
    }

    /**
     * Overload: Prints all books
     */
    private void printAll(Book book) {
        bookList
                .stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    /**
     * Overload: Prints all loans
     */
    private void printAll(Loan loan) {
        loanList
                .stream()
                .sorted(Comparator.comparing(Loan::getDevDate).reversed())
                .forEach(System.out::println);
    }

    /**
     * Write any changes made to the lists in disk
     */
    private void saveChanges() {
        Thread t = new Thread(() -> {
            try {
                ListIterator userItr = userList.listIterator();
                ListIterator bookItr = bookList.listIterator();
                ListIterator loanItr = loanList.listIterator();
                FileWriter fwUser = new FileWriter("users.csv");
                FileWriter fwBook = new FileWriter("books.csv");
                FileWriter fwLoan = new FileWriter("loans.csv");

                while (userItr.hasNext()) {
                    User user = (User) userItr.next();
                    fwUser.write(user.toFile() + "\n");
                }
                while (bookItr.hasNext()) {
                    Book book = (Book) bookItr.next();
                    fwBook.write(book.toFile() + "\n");
                }
                while (loanItr.hasNext()) {
                    Loan loan = (Loan) loanItr.next();
                    fwLoan.write(loan.toFile() + "\n");
                }
                fwUser.close();
                fwBook.close();
                fwLoan.close();
            } catch (IOException e) {
                System.out.println("Erro ao abrir o arquivo");
            }
        });
        t.start();
    }

    /**
     * Registers new book
     */
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
        System.out.println("Livro: " + title + " cadastado com sucesso!");
    }

    /**
     * Checks if a loan is overdue
     */
    private void checkLate() {
        loanList
                .stream()
                .filter(l -> today.isAfter(l.getDevDate()) == true)
                .forEach(l -> System.out.println("Livro atrasado:\n\n" + l.toString()));
    }
}