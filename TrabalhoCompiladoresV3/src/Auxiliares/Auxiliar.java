/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Leandro
 */
public class Auxiliar {
    public String aux;
    public int cont;
    ArrayList<String> naoTerminais = new ArrayList<String>(); //Será usado para saber se o elemento é não terminal
    ArrayList<Integer> tamanhos = new ArrayList<Integer>();
    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<String> terminais = new ArrayList<String>();
    ArrayList<String> ignorar = new ArrayList<String>();
    
    public Auxiliar(){
        cont=1;
        naoTerminais.add("chunk");
        naoTerminais.add("fieldsep");
        naoTerminais.add("stat");
        naoTerminais.add("laststat");
        naoTerminais.add("block");
        naoTerminais.add("varlist");
        naoTerminais.add("explist");
        naoTerminais.add("functioncall");
        naoTerminais.add("exp");
        naoTerminais.add("namelist");
        naoTerminais.add("auxstat");
        naoTerminais.add("auxlaststat");
        naoTerminais.add("auxfieldlist");
        naoTerminais.add("functAux1");
        naoTerminais.add("functAux2");
        naoTerminais.add("funcname");
        naoTerminais.add("unop");
        naoTerminais.add("binop");
        naoTerminais.add("funcbody");
        naoTerminais.add("var");
        naoTerminais.add("func");
        naoTerminais.add("prefixexp");
        naoTerminais.add("auxfuncname");
        naoTerminais.add("tableconstructor");
        naoTerminais.add("args");
        naoTerminais.add("parlist");
        naoTerminais.add("fieldlist");
        naoTerminais.add("auxelseif");
        naoTerminais.add("field");
        terminais.add("IF");
        terminais.add("WHILE");
        terminais.add("THEN");
        terminais.add("ELSE");
        terminais.add("ELSEIF");
        terminais.add("END");
        terminais.add("DO");
        terminais.add("UNTIL");
        terminais.add("REPEAT");
        terminais.add("FOR");
        
        
        ignorar.add(",");
        ignorar.add("(");
        ignorar.add(")");
        ignorar.add("[");
        ignorar.add("]");
        ignorar.add("{");
        ignorar.add("}");
        ignorar.add(";");
         ignorar.add("IF");
         ignorar.add("WHILE");
         ignorar.add("THEN");
         ignorar.add("ELSE");
         ignorar.add("ELSEIF");
         ignorar.add("END");
         ignorar.add("DO");
         ignorar.add("UNTIL");
         ignorar.add("REPEAT");
         ignorar.add("FOR");
     
        
        
        
    }
    
    
    
    public Auxiliar(String pai , String filho){
         try {
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auxiliar.txt", true)));
            String auxi = pai + "---->" + filho;
            out.println(auxi.replace(" ", "---->")); //para facilitar no split do criarArvore()
            out.close();
                       
            
        } catch (IOException ex) {
            
        } 
    }
    
    
    
     private void criarArvore() throws IOException, Exception{
         int totalEspacos = 0;
         boolean term = false;
         BufferedReader br = new BufferedReader(new BufferedReader(new FileReader("auxiliar.txt")));
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("arvore.txt")));
         ArrayList<String> linhas = new ArrayList<String>();
         ArrayList<String> file = new ArrayList<String>();
         for (int i = 0; i < cont-1; i++) {
             linhas.add(br.readLine()); //Linhas do arquivo
         }
         String[] elementos = null;
         String linha = null;
         String auxi = "";
         
         for (int i = 0; i < linhas.size(); i++) {
             auxi = "";
             linha = linhas.get(i); //Cada linha do arquivo salva no array
             elementos = linha.split("---->"); //separando elementos de cada linha
             if(elementos.length > 2){
                 for (int j = 1; j < elementos.length; j++) {
                     auxi = auxi + " " + elementos[j];
                 }
                 linha = elementos[0] + "---->" + auxi;
             }
             
             if(term){ //Se term = true, vai chamar contador para subtrair a quantidade de espaços do total, ou seja, retroceder (é onde está o problema)
                 String teste = linha.split("---->")[0];
                 totalEspacos -= contador(file, teste);
             }
             //Adiciona a linha no arraylist que, no final, vai ser escrito no arquivo
             file.add(contaEspacos(totalEspacos) + linha); //Ate aqui, ok!
             
                 for (int j = 1; j < linha.split("---->").length; j++) {
                     String expressao = linha.split("---->")[j];
                     if(expressao.split(" ").length > 1){
                         totalEspacos += linha.length();//Atualizando o total de espaços
                         file.add(contaEspacos(totalEspacos) + "|"); //Espaços e arestas
                     } //Ate aqui, ok!
                     else if(naoTerminais.contains(linha.split("---->")[j])){ //Testando se é não terminal
                            totalEspacos += linha.length();
                            file.add(contaEspacos(totalEspacos) + "|"); //Espaços e arestas
                            
                       }
                     if(linha.split("---->")[1].contains(" ")){
                         String teste = linhas.get(i).split("---->")[1];
                         
                         if(!(naoTerminais.contains(teste))){ //casos de palavras reservadas
                             if(!(terminais.contains(teste)))
                                //totalEspacos -= contador(file, linha.split("---->")[1]);
                                 term = false;
                            }
                     }else{
                         String teste = linha.split("---->")[1];
                         if(!naoTerminais.contains(teste) && !terminais.contains(teste)){//Se chegar numa folha (terminal), term = true
                                term = true;
                            }
                     }
             }
                 
             
         }
        
         for (int i = 0; i < file.size(); i++) {
             out.println(file.get(i));
         }
         out.close();
                      
         
         
     }
     
     private void tresEnderecos() throws IOException, Exception{
         BufferedReader entrada = new BufferedReader(new BufferedReader(new FileReader("auxiliarInv.txt")));
         PrintWriter saida = new PrintWriter(new BufferedWriter(new FileWriter("tresEnd.txt")));
         ArrayList<String> linhas = new ArrayList<String>();
         ArrayList<String> terms = new ArrayList<String>();
        // contaLinhas();
         for (int i = 0; i < cont-1; i++) {
             linhas.add(entrada.readLine()); //Linhas do arquivo
         }
         String[] elementos = null;
         String linha = null;
         int contador=0;
         int t=1;
         for (int i = 0; i < linhas.size(); i++) {
             linha = linhas.get(i); //Cada linha do arquivo salva no array
             elementos = linha.split("---->"); //separando elementos de cada linha
             for (int j=0; j < elementos.length; j++ ){
                 if(!naoTerminais.contains(elementos[j]) && !ignorar.contains(elementos[j])){
                     terms.add(elementos[j]);
                 }
             }
        }
        while(!terms.isEmpty()){
            if(contador%3==0){
                saida.println("");
                saida.print("T" + t++ + ": ");
            }
            
            saida.print(terms.get(0) + " ");
            terms.remove(0);
            contador++;
        }
        entrada.close();
        saida.close();
        
     }
     
     private int contador(ArrayList<String> file, String var){
         int cont = 0;
         String teste = "";
         for (int i = file.size()-1; i > 0; i--) {
             if(file.get(i).contains("---->")){
                 teste = file.get(i).split("---->")[1];
             } else{
                 teste = file.get(i);
             }
             if(!teste.equals("|")){
                if(!teste.contains(var)){
                    cont += file.get(i).trim().length();
                }else{
                    cont += file.get(i).trim().length() - i;
                    return cont;
                }
             }
         }
         
         return 0;
     }
     private String contaEspacos(int totalEspacos){
         String s = "";
         for (int i = 0; i < totalEspacos; i++) {
             s = s + " ";
         }
         return s;
     }
    public void contaLinhas(){
        try {
            Scanner arq =  new Scanner ( new File ("auxiliar.txt"));
                    while(arq.hasNextLine()){
                        cont = cont + 1;
                        arq.nextLine();
                    }      
            arq.close();
        } catch (IOException ex) {
            
        }
        
        
   
        
    }

    
  public void inverteArq() throws Exception{
        contaLinhas();
       
       String[] inverte =  new String[this.cont];
       int  i=0;
        try {
            Scanner arq =  new Scanner ( new File ("auxiliar.txt"));
                    while(i!=(cont-1)){
                        inverte[i]=arq.nextLine();
                        i=i+1;
                    }      
            arq.close();
            
            
        } catch (IOException ex) {
            
        }
        
        
        
         try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auxiliar.txt")));
            PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("auxiliarInv.txt")));
            i=this.cont - 2;
                 while(i>=0){
                       out.println(inverte[i]);
                       i=i-1;
                    } 
               out.close();
             for (int j = 0; j <this.cont-1 ; j++) {
              out2.println(inverte[j]);
                }
               out2.close();
                 criarArvore();
                 tresEnderecos();
        } catch (IOException ex) {
            
        } 
        
        
        
   }     
         

  
    
}
