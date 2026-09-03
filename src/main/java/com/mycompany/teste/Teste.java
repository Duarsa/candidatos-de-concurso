/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
class Candidatos{
    int numInscricao;
    String nome;
    int idade;
    int numDepen;
    double pontos;
}



public class Teste {
    
    // fazer a leitura do arquivo e salvar no programa
    public static void Leitura(ArrayList<Candidatos> lista){
        
        

        try{
            File ler = new File("candidatos.txt");
            Scanner sc_leia = new Scanner(ler);
            
            while(sc_leia.hasNextLine()){
                
                String linha = sc_leia.nextLine();

                String[] dados = linha.split(",");

                Candidatos cand = new Candidatos();

                cand.numInscricao = Integer.parseInt(dados[0]);
                cand.nome = dados[1];
                cand.numDepen = Integer.parseInt(dados[2]);
                cand.idade = Integer.parseInt(dados[3]);
                cand.pontos = Double.parseDouble(dados[4]);

                lista.add(cand);
            }
            
        }catch(FileNotFoundException e){
            System.out.println("Erro ao encontar o arquivo");
        }

    }


    public static void Busca(ArrayList<Candidatos> lista, int pos, int inscricao){
        if(pos >= lista.size()){
            System.out.println("Candidato inexistente");
            return;
        }

        if(lista.get(pos).numInscricao == inscricao){
            System.out.println("Nome: " + lista.get(pos).nome);
            return;
        }

        Busca(lista, pos+1, inscricao);
    }

    public static void Classificado(ArrayList<Candidatos> lista){

        for(int i=0; i<lista.size()-1; i++){
            for(int j=0; j<lista.size()-1-i; j++){
                if(lista.get(j).pontos < lista.get(j+1).pontos){
                    Candidatos aux = lista.get(j);
                    lista.set(j, lista.get(j+1));
                    lista.set(j+1, aux);
                }

            }
        }

        for(Candidatos c: lista){
            System.out.println("Nome: " + c.nome +
                " | Pontos: " + c.pontos +
                " | Idade: " + c.idade +
                " | Dependentes: " + c.numDepen);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {

    Scanner sc = new Scanner(System.in);

    ArrayList<Candidatos> lista = new ArrayList<>();

    int op;

    do{

        System.out.println("\n===== MENU =====");
        System.out.println("1 - Ler arquivo");
        System.out.println("2 - Buscar candidato");
        System.out.println("3 - Mostrar classificados");
        System.out.println("9 - Sair");

        op = sc.nextInt();

        switch(op){

            case 1:

                Leitura(lista);

                System.out.println("Arquivo carregado com sucesso!");

                break;

            case 2:

                System.out.println("Digite o numero da inscricao:");

                int inscricao = sc.nextInt();

                Busca(lista, 0, inscricao);

                break;

            case 3:

                Classificado(lista);

                break;

            case 9:

                System.out.println("Encerrando...");

                break;

            default:

                System.out.println("Opcao invalida!");

        }

    }while(op != 9);

}
    
    
    
}
