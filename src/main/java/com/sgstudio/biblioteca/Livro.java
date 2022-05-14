/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sgstudio.biblioteca;

/**
 *
 * @author Kanashi
 */
public class Livro {
    //Oi Kanashi
    protected String titulo;
    protected String autor;
    private int idProprietario = -2;
    public static int livrosNovos;
    private int idLivro;
    private static int diasMaximosFora = 7;
    private int diasFora = 0;
    
    public Livro(String titulo,String autor){
        //Declarações iniciais
        this.idLivro = livrosNovos;
        livrosNovos++;
        this.titulo = titulo;
        this.autor = autor;
        
    }
          
    public void aumentarDiasFora(){
        this.diasFora+= 1;
        if(diasFora > diasMaximosFora){
            
            System.out.println("Este livro passou do prazo de renovação");
            
        }else if(diasFora == diasMaximosFora){
            
            System.out.println("Este livro deve ser renovado hoje!");
            
        }else {
            
            System.out.println("Este livro ainda no prazo de renovação");
            
        }
        
    }
    
    public void renovarLivro(){
        
        this.diasFora = 0;
        
    }
    
    public boolean setProprietario(int newPessoa, int pessoaAtual){
        
        //Cliente temporário
        Cliente cliente;
        //Procurando o cliente com o mesmo ID
        for (int i = 0; i < Biblioteca.getClientes().size(); i++) {
            //Cliente temporário
            cliente = Biblioteca.getClientes().get(i);
            
            if(cliente.getIdCliente() == newPessoa || newPessoa == -2){
                cliente.setLivrosArray();
                //Cliente com o mesmo id, ou tirando proprietário
                if(newPessoa == -2){
                    //Tirando proprietário
                    for (int j = 0; j < Biblioteca.getClientes().size(); j++) {
                        //Verificando se o livro pertencia a alguém
                        if (Biblioteca.getClientes().get(i).getIdCliente() == pessoaAtual) {
                            //Tirando o livro do cliente
                            Biblioteca.getClientes().get(i).removeLivros(this.getIdLivro());
                        }
                        
                    }
                    this.idProprietario = -2;
                    //Atualizando o vetor do cliente
                    cliente.setLivrosArray();
                    return true;
                }
                //Verificando se o cliente ainda pode receber livros
                if (cliente.isLiberado()) {
                    this.idProprietario = newPessoa;
                    cliente.setLivrosArray();
                    return true;
                }else{
                    System.out.println("Cliente não está liberado para pegar livros");
                    return false;
                }
                
                
            }
            
            
            
        }
        return false;
    }
    
    public int getDiasFora() {
        return diasFora;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getIdProprietario() {
        return idProprietario;
    }

    public int getIdLivro() {
        return idLivro;
    }
    @Override
    public String toString(){
        //Informações Livro
        String str;
        
        str="============================= \n"+"Id Livro:" + getIdLivro()+"\n"+"Titulo:" + getTitulo()+"\n"+"Autor:" + getAutor()+
        "\n"+"Proprietário:";
        //Verificando se tem proprietário
        if(this.idProprietario == -2){
            //Não tem
            str+="Sem Proprietário";
            
        }else {
            //Procurando nome do proprietário
            for (int i = 0; i < Biblioteca.getClientes().size(); i++) {
                
                if(Biblioteca.getClientes().get(i).getIdCliente() == this.idProprietario){
                    //Nome achado
                    str+=Biblioteca.getClientes().get(i).getNome();
                    
                }
                
            }
            
        }
        
        str+= "\n" + "Dias Fora: " + getDiasFora();
        
        return str;
    }
    
}
