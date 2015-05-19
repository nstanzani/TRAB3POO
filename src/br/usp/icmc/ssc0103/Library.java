package br.usp.icmc.ssc0103;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by naldost on 19/05/15.
 */
public class Library {

    static LinkedList<User> userList = new LinkedList<User>();

    public static void menu() throws Exception{

        int op;
        Scanner scanner = new Scanner(System.in);

        do {
            op = scanner.nextInt();
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

            switch (op) {
                case 1:
                    registerNewUser("Guilherme", "Student");
                    registerNewUser("Arnaldo", "Student");
                    printList(userList);
                    break;
                case 2:
                    //registerNewBook();
                    break;
                case 3:
                    //registerNewLoan();
                    break;
                case 4:
                    //registerNewDev();
                    break;
                case 5:
                    //saveChanges();
                    break;
                case 6:
                    //printAllUsers();
                    break;
                case 7:
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

    public static void registerNewUser(String name, String userType) {
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

    public static void printList(LinkedList list) {
        System.out.println(list);
    }


    public static void main(String[] args) {
        try {
            menu();
        }
        catch(Exception e){}
    }
}