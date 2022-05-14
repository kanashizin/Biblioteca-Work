/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sgstudio.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kanashi
 */
public class Biblioteca {
    
    private static int livrosFora;
    private static int estoqueTotal;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Estoque> estoques;
    private static boolean running = true;
    private static Livro livroSelecionado;
    private static Cliente clienteSelecionado;
    public static String barra = "=============================";
    
    public static void main(String[] args) {
        //Declaração iniciais
        clientes = new ArrayList();
        estoques = new ArrayList();
        estoqueTotal = Livro.livrosNovos;
        
        
        Scanner scan = new Scanner(System.in);
        
        while(running){
            //Menu Inicial
            System.out.println(barra);
            System.out.println("Bem vindo ao Programa da Biblioteca");
            System.out.println("Qual sua próxima ação?");
            System.out.println("1 - Adicionar Livro, 2 - Listar de Livros,3 - Selecionar Livro");
            System.out.println("4 - Adicionar Cliente, 5 - Listar Clientes, 6 - Selecionar Cliente");
            System.out.println("7 - Sair");
            //Entrada de dados
            int menu;
            String menuOption = scan.nextLine();
            //Verificando erros
            try{
                menu = Integer.parseInt(menuOption);
                menu(menu, scan);
            }catch(Exception e){
                //Código de erro
                System.out.println(barra);
                System.out.println("Entrada incorreta");
                System.out.println("Verifique sua escrita/entrada");
                
            }
        }
        
    }
    
    private static void menu(int acao, Scanner scan){
        //Looping de insistencia
        boolean valido = true;
        while(valido){
            
            switch (acao){
                //Adicionando Livro no Estoque
                case 1:
                    System.out.println("Título do Livro e autor do Livro:");
                    System.out.println("Separe os dois por ;");
                    String str;
                    str = scan.nextLine();
                    //Separando Titulo e Autor
                    String[] strArray = str.split(";");
                    //Adicionando Livro no Estoque
                    criarEstoque(strArray[0], strArray[1]);
                    valido = false;
                    System.out.println("Livro Adicionado");
                    break;
                case 2:
                    if(estoques.size() == 0){
                        //Estoque Vazio
                        System.out.println(barra+"Seu estoque está vazio");
                    }else{
                        //Tem livro no estoque
                        for (int i = 0; i < estoques.size(); i++) {
                            for (int j = 0; j < estoques.get(i).getBooksList().size(); j++) {
                                //Mostrando os livros
                                System.out.println(estoques.get(i).getBooksList().get(j).toString());

                            }
                        }
                    }
                    valido = false;
                    break;
                    
                case 3:
                    //Seleção de livro
                    System.out.println(barra);
                    System.out.println("Digite o id do livro que você quer selecionar");
                    int id = -1;
                    String strID;
                    boolean validarID = true;
                    //Looping de insistencia
                    while(validarID){
                        System.out.print("ID: ");
                        strID = scan.nextLine();
                        //Código anti-erro
                        try{

                            id = Integer.parseInt(strID);

                        }catch(Exception e){

                            System.out.println("ID inválido");
                            
                            
                        }
                        //Verificando se tem livro com esse ID
                        if(!selecionarLivro(id)){
                            //Não tem livro com esse ID
                            System.out.println("Este ID não foi cadastrado");
                            return;
                        }else {
                            //Existe livro com esse ID
                            validarID = false;
                            boolean modificandoLivro = true;
                            int strMenu;
                            //Loopting de insistencia
                            while(modificandoLivro){
                                //Ações com o livro
                                System.out.println(barra);
                                System.out.println("Livro Selecionado");
                                System.out.println(livroSelecionado.toString());
                                System.out.println(barra);
                                System.out.println("Digite sua proxima ação:");
                                System.out.print("1 - Remover Livro do estoque, 2 - Emprestar Livro, ");
                                if (livroSelecionado.getIdProprietario() != -2) {
                                    //Se o livro tiver proprietário
                                    System.out.println("3 - Aumentar Dias Fora");
                                    System.out.print("4 - Renovar Livro,");
                                    
                                }
                                System.out.println("5 - Sair");
                                
                                //Recebendo entrada
                                strMenu = scan.nextInt();
                                switch(strMenu){
                                    
                                    case 1:
                                        //Removendo livro do estoque
                                        System.out.println("Tem certeza que deseja apagar?(Y/N)");
                                        String strDelete;
                                        boolean debug = true;
                                        strDelete = scan.nextLine();
                                        
                                        if(strDelete.equalsIgnoreCase("Y")){
                                            for (int i = 0; i < estoques.size(); i++) {

                                                for (int j = 0; j < estoques.get(i).getEstoqueSize(); j++) {

                                                    if(estoques.get(i).getBooksList().get(j).getIdLivro() == livroSelecionado.getIdLivro()){
                                                        //Se for o unico livro no estoque
                                                        //Deletar este estoque
                                                        if (estoques.get(i).getEstoqueSize() == 1) {
                                                            estoques.remove(i);
                                                            modificandoLivro = false;
                                                            System.out.println(barra);
                                                            System.out.println("Removendo Estoque");
                                                            System.out.println("Retornando para o Menu:");
                                                            return;
                                                        }else {
                                                            //Se não deletar apenas o livro
                                                            modificandoLivro = false;
                                                            estoques.get(i).getBooksList().remove(j);
                                                            System.out.println(barra);
                                                            System.out.println("Removendo Livro do Estoque");
                                                            System.out.println("Retornando para o Menu:");
                                                            return;
                                                        }

                                                    }

                                                }
                                            }
                                        }else {
                                            //Remover Cancelado
                                            modificandoLivro = false;
                                            System.out.println("Remoção cancelada");
                                            return;
                                        }
                                    break;
                                    case 2:
                                        //Trocar Proprietário
                                        boolean setProper = true;
                                        //Loopting de insistencia
                                        while(setProper){
                                            System.out.println("Digite o ID do proprietário:");
                                            System.out.println("Use -1 para sair e -2 para tirar o proprietário");
                                            int proprietario;
                                            proprietario = scan.nextInt();
                                            
                                            if (proprietario == -1) {
                                                //Sair
                                                setProper = false;
                                                return;
                                            }
                                            
                                            if(!livroSelecionado.setProprietario(proprietario,livroSelecionado.getIdProprietario())){
                                                //Livro não existe
                                                System.out.println("Proprietário Inválido");
                                            }else{
                                                // Livro existe
                                                System.out.println("Proprietário setado com sucesso!");
                                                setProper = false;
                                            }
                                            
                                        }
                                        modificandoLivro = false;
                                        valido = false;
                                        break;
                                    case 3:
                                        //Aumentando dias foras
                                        if(livroSelecionado.getIdProprietario() == -2){
                                            //Verificando se o livro tem proprietário
                                            System.out.println("Número Inválido");
                                            
                                        }else {
                                            //Se tiver aumenta dias fora
                                            livroSelecionado.aumentarDiasFora();
                                            System.out.println("Aumentando Dias foras para " + livroSelecionado.getDiasFora());
                                            return;
                                        }
                                        
                                        break;
                                    case 4:
                                        //Renovar livro
                                        if(livroSelecionado.getIdProprietario() == -2){
                                            //Verificando se tem proprietário
                                            System.out.println("Número Inválido");
                                            
                                        }else {
                                            //Renovando
                                            livroSelecionado.renovarLivro();
                                            System.out.println("Renovando Livro");
                                            return;
                                        }
                                        
                                        
                                        break;
                                    default:
                                        //Sair
                                        System.out.println("Saindo");
                                    return;
                                    
                                }
                                
                            }
                            
                        }
                    }
                    
                    break;
                    
                case 4:
                    //Cadastrar cliente
                    System.out.println(barra);
                    System.out.println("Digite seu nome:");
                    String name = scan.nextLine();
                    System.out.println("Digite seu cpf:");
                    String cpf = scan.nextLine();
                    //Validando CPF
                    if (Cliente.validCPF(cpf)) {
                        //CPF válido
                        Biblioteca.clientes.add(new Cliente(name,cpf));
                        valido = false;
                        System.out.println("Cliente Adicionado");
                    }else {
                        //CPF inválido
                        System.out.println("CPF inválido");
                    }
                    valido = false;
                    break;
                case 5:
                    //Listar clientes
                    if(clientes.size() == 0){
                        //Sem clientes registrados
                        System.out.println(barra);
                        System.out.println("Você não tem clientes registrados");
                    }else {
                        for (int i = 0; i < clientes.size(); i++) {
                            //Mostrando Clientes
                            System.out.println(clientes.get(i).toString());
                        }
                    }
                    valido = false;
                    break;
                case 6:
                    //Selecionar Cliente
                    boolean selectingUser = true;
                    int idUser;
                    String strIdUser;
                    //Loopting de Insistencia
                    while(selectingUser){
                        System.out.println(barra);
                        System.out.println("Digite o id do usuário: (-1)");
                        strIdUser = scan.nextLine();
                        //Código anti-erro
                        try{
                            
                            idUser = Integer.parseInt(strIdUser);
                            
                        }catch(Exception e){
                            
                            System.out.println("Erro com o id");
                            return;
                        }
                        //Procurando o cliente
                        for (int i = 0; i < clientes.size(); i++) {
                            
                            if(clientes.get(i).getIdCliente() == idUser){
                                //Modificar cliente
                                clienteSelecionado = clientes.get(i);
                                System.out.println(barra);
                                System.out.println(clienteSelecionado.toString());
                                System.out.println(barra);
                                System.out.println("Digite sua ação");
                                System.out.println("1 - Listar Livros, 2 - Remover Cliente");
                                return;
                            }
                            
                        }
                        System.out.println("Cliente não encontrado");
                        return;
                    }
                    break;
                case 7:
                    //Sair
                    System.out.println("Obrigado por Trabalhar Conosco");
                    valido = false;
                    running = false;
                    
                    break;
                default:
                    valido=false;
                    break;
            }
        }
        
    }
    
    private static void criarEstoque(String titulo, String autor){
        
        for (int i = 0; i < estoques.size(); i++) {
            
            if(estoques.get(i).getAutor().equalsIgnoreCase(autor) && estoques.get(i).getTitulo().equalsIgnoreCase(titulo)){
                //Este livro já existe
                Scanner scan = new Scanner(System.in);
                System.out.println("Cara... Já existe um livro igual no estoque.");
                System.out.println("Deseja adicionar o livro ao estoque atual?(Y/N)");
                
                String str = scan.next();
                boolean valido = true;
                
                if(str.equalsIgnoreCase("Y")){
                    //Adicionando ao estoque
                    estoques.get(i).aumentarEstoqueLivro(new Livro(titulo, autor));
                    valido = false;
                    
                }else if(str.equalsIgnoreCase("N")){
                    //Ignorando ele
                    valido = false;
                    return;
                    
                }
                //Looping de insistencia
                while(valido){
                        
                    System.out.println("Resposta Incorreta");
                    System.out.println("Deseja adicionar o livro ao estoque atual?(Y/N)");
                    
                    if(str.equalsIgnoreCase("Y")){
                        //Aumentando estoque
                        estoques.get(i).aumentarEstoqueLivro(new Livro(titulo, autor));
                        valido = false;
                    }else if(str.equalsIgnoreCase("N")){
                        //Ignorar livro
                        valido = false;
                        return;
                    }
                }
                return;
            }
            
        }
        
        estoques.add(new Estoque(titulo, autor));
        
    }
    
    public static boolean selecionarLivro(int idLivro){
        //Procurando livro no estoque
        for (int i = 0; i < estoques.size(); i++) {
            Estoque temp = estoques.get(i);
            Livro livroTemp;
            for (int j = 0; j < temp.getBooksList().size(); j++) {
                
                livroTemp = temp.getBooksList().get(j);
                if (idLivro == livroTemp.getIdLivro()) {
                    //Selecionando livro
                    livroSelecionado = livroTemp;
                    return true;
                }
                
            }
        }
        
        return false;
    }
    
    public static ArrayList<Estoque> getEstoques(){
        
        return estoques;
        
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    
    
    public Livro getLivroSelecionado(){
        return livroSelecionado;
    }

    public static int getLivrosFora() {
        return livrosFora;
    }

    public static int getEstoqueTotal() {
        return estoqueTotal;
    }
    
    
    
}
