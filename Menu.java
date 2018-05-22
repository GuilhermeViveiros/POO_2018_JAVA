
import java.io.*;
import java.util.*;

public class Menu
{   
    private ArrayList<String> opcoes;
    private String titulo;
    
    public Menu(String titulo)
    {
        this.titulo = titulo;
        this.opcoes = new ArrayList<String>();
    }
    
    public void add(String op){
        this.opcoes.add(op);
    }
    
    public int showMenu(){
        
        int value;
        Scanner s = new Scanner(System.in);
        System.out.println(this.titulo);
        do{
            int count = 1 ;
            for(String a :this.opcoes){
                System.out.println( count + " - " + a );
                count++;
            }
            System.out.println(" 0 - Voltar para o menu anterior ");
            value = s.nextInt();
        }while( value < 0 && value > this.opcoes.size() );
        
        return value;
    }
}
