/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sgstudio.biblioteca;

/**
 *
 * @author Kanashi
 */
public class Cliente {
    
    
    private String nome;
    private String cpf;
    private static int id = 0;
    private int idCliente;
    private Livro[] livros;
    private boolean liberado;
    
    public Cliente(String nome, String cpf){
        //Declarações Iniciais
        liberado = true;
        livros = new Livro[2];
        this.cpf = cpf;
        this.nome = nome;
        idCliente = id;
        id++;
    }
    
    public static boolean validCPF(String cpf){
        
        for (int i = 0; i < Biblioteca.getClientes().size(); i++) {
            
            if (Biblioteca.getClientes().get(i).getCpf().equalsIgnoreCase(cpf)) {
                
                return false;
                
            }
            
        }
        
        if (cpf.length() == 11) {
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public void removeLivros(int idLivro){
        //Procurando livro no seu vetor
        for (int i = 0; i < livros.length; i++) {
            
            if (livros[i].getIdLivro() == idLivro) {
                //Livro encontrado
                if(i == 0 && livros[i+1]!=null){
                    //Removendo o primeiro livro
                    livros[i] = livros[i+1];
                    livros[i+1] = null;
                    return;
                    
                }else {
                    //Removendo o segundo livro
                    livros[i] = null;
                    return;
                }
                
            }
            
        }
        
    }
    
    public void setLivrosArray(){
        int countLivros = 0;
        //Procurando livros
        for (int i = 0; i < Biblioteca.getEstoques().size(); i++) {
            for (int j = 0; j < Biblioteca.getEstoques().get(i).getEstoqueSize(); j++) {
                
                if(Biblioteca.getEstoques().get(i).getBooksList().get(j).getIdProprietario() == this.getIdCliente()){
                    //Livros que pertencem a esse cliente
                    
                    //Salvando Livro no vetor
                    livros[countLivros] = Biblioteca.getEstoques().get(i).getBooksList().get(j);
                    
                    if(countLivros == 1) {
                        //Verificando se está no limite de livros
                        this.liberado = false;
                        return;
                    }
                    
                    countLivros++;
                    
                }
                
            }
        }
        
    }

    public boolean isLiberado() {
        return liberado;
    }
    
    
    
    @Override
    public String toString(){
        //Informações do cliente
        String str = "";
        str+=Biblioteca.barra+"\n"+"ID: "+this.getIdCliente()+"\n"+"Nome: "+this.getNome()+"\n"+"CPF: "+this.getCpf();
        if(livros!=null){
            //Verificando se o cliente tem livros
            for (int i = 0; i < livros.length; i++) {

                if(livros[i] != null){
                    //Mostrando os livros
                    str+="\n"+"Livro "+(i+1)+": " + livros[i].getTitulo();
                }

            }
        }else {
            //Sem livros
            str+="\n"+"Sem livros";
        }
        return str;
        
    }
    
}
