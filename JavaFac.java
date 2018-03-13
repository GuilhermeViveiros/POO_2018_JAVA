import java.util.Scanner;
import java.util.ArrayList;
public class JavaFac
{
    
//MODULO DESIGNADO PARA TESTES , TESTE AQUI AS SUAS FUNCOES MAS NAO MEXO NO QUE ESTA ESCRITO SEM AVISAR PRIMEIRO SFF
//MODULO DESIGNADO PARA TESTES , TESTE AQUI AS SUAS FUNCOES MAS NAO MEXO NO QUE ESTA ESCRITO SEM AVISAR PRIMEIRO SFF
//MODULO DESIGNADO PARA TESTES , TESTE AQUI AS SUAS FUNCOES MAS NAO MEXO NO QUE ESTA ESCRITO SEM AVISAR PRIMEIRO SFF
//MODULO DESIGNADO PARA TESTES , TESTE AQUI AS SUAS FUNCOES MAS NAO MEXO NO QUE ESTA ESCRITO SEM AVISAR PRIMEIRO SFF

public static void main (String[] args) {
Scanner s = new Scanner (System.in);

//Pessoa(long ni_P, String nom_P, String mai_P, String morad_P, int nrDAF_P, ArrayList<Long> nrFAF_P, float cF_P, String cAE_P, String number
       String teste;
       Produto[] product ;
       ArrayList<Long> n = new ArrayList();
       n.add((long)8);
       n.add((long)7);
       long x = 312312;
       Pessoa pessoa = new Pessoa ( 29120312 , "pessoas" , "angelo@gmail.com" , "rua das pessoas" , 982 , n , 212212 , "Pilas" ,"+351 pessoas");  
       Entidade aux = new Entidade( 263744361 , "entidades" , "goncas@gmail.entidades" , "rua das entidades" ,"+351 entidades");
       Empresa empresa = new Empresa ( 263744361 , "empresa" , "guilherme@gmail.empresa" , "rua das empresas" ,"+351 empresas" , "Saude" , "empresaa:987217" );
      
       //Entidade nova = new Entidade ( aux ) ESTA A FUNCIONAR -> FAZ O QUE SE PEDE
       //System.out.println ( aux.toString())  ESTA A FUNCIONAR -> FAZ O QUE SE PEDE
      
       //System.out.println ( x.info())  ESTA A FUNCIONAR -> FAZ O QUE SE PEDE
       
       System.out.println ( empresa.toString() ) ; 
       System.out.println ( pessoa.toString() ) ;
         
         
    /*
       aux.setPassword ( "coisas" );
       System.out.println ( "Digite uma pass ");
       teste = s.nextLine();
       if ( aux.checkPassword(teste)) System.out.println ("Passe certa meu caro");
       else System.out.println ("Passe errada ");
       
       aux.AlterPassword ( "nova" , "coisas");
       System.out.println ( "Digite uma pass ");
       teste = s.nextLine();
       if ( aux.checkPassword(teste) ) System.out.println ("Passe certa meu caro");
       else System.out.println ("Passe errada meu caro");
       
       
      */ //ESTA PARTE NAO DA 
      
      /*
      Produto novo = new Produto("peras" , 0 ,12.0);
      Produto novo1 = new Produto("goncas" , 1 ,11.0);
      Produto novo2 = new Produto("angelo" , 2 ,5.0);
      Produto novo3 = new Produto("pilas de angelo" , 3 ,3.50);
      Produto novo4 = new Produto("Melissa" , 4 ,500);
      
    
      empresa.add_product (novo);
      empresa.add_product (novo1);
      empresa.add_product (novo3);
      empresa.add_product (novo4);
      System.out.println ( empresa.info()); 
      
      */ // ESTA PARTE FUNCIONA , ADICIONAR PRODUTOS
     
      
     
    }
  
  

  
}
    
    
    

