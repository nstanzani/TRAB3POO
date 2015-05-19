package br.usp.icmc.ssc0103;

/**
 * Created by naldost on 19/05/15.
 */
public class Library {

    public static void menu(){
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
    }

    public static void main(String[] args) {
        menu();
    }
}