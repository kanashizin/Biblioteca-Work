/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sgstudio.biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Kanashi
 */
public class Estoque{
    
    private String autor;
    private String titulo;
    private ArrayList<Livro> books = new ArrayList();
    
    public Estoque(String titulo, String autor){
        //Declarações iniciais
        adicionarLivro(titulo, autor);
        this.titulo = titulo;
        this.autor = autor;
    }
    
    public void aumentarEstoqueLivro(Livro newBook){
        //Verificando se existe livro igual
        if(verifyDuplicateBooks(newBook.getTitulo(), newBook.getAutor())){
            //Se existir adiciona ao estoque também
            books.add(newBook);
            
        }else
            System.out.println("Este livro não é igual ao do estoque");
            //Não tem igual no estoque
    }
    
    public void adicionarLivro(String nomeLivro, String autorLivro){
        //Adicionando o primeiro livro ao estoque
        Livro tempBook = new Livro(nomeLivro,autorLivro);
        books.add(tempBook);

    }
    
    public boolean verifyDuplicateBooks(String bookName, String autor){
        //Procurando livros iguais
        for (int i = 0; i < books.size(); i++) {
            
            if(books.get(i).getAutor().equalsIgnoreCase(autor) && books.get(i).getTitulo().equalsIgnoreCase(bookName)){
                //Existe igual
                return true;
            }
            
        }
        return false;
    }

    public int getEstoqueSize() {
        return books.size();
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Livro> getBooksList() {
        return books;
    }
    
    
    
    
}
