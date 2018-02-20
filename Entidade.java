
/**
 * Escreva a descrição da classe Entidade aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Random;

public class Entidade
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private long nif;
    private String nome;
    private String mail;
    private String morada;
    private long pw;
    private int key;

    public Entidade(){
        this.nif    = -1;
        this.nome   = " O Seu nome ";
        this.mail   = " utilizario@example.com";
        this.morada = " A sua morada ";
        this.key    = java.util.Random.nextInt();
        this.pw     = -1;
    }

    public Entidade( long ni_p, String nom_p, String mai_p , String morad_p ){
        this.nif    = ni_p  ;
        this.nome   = nom_p ;
        this.mail   = mai_p ;
        this.morada = morad_p ;
        this.key    = java.util.Random.nextInt();
        this.pw     = -1;
    }

    public Entidade( Entidade inc ){
        this.nif    = inc.getNif();
        this.nome   = inc.getNome();
        this.mail   = inc.getMail();
        this.morada = inc.getMorada();
        this.key    = java.util.Random.nextInt();
        this.pw     = -1;
    }
    // getters!

    public long getNif(){
        return this.nif;
    } 

    public String getNome(){
        return this.nome;
    } 

    public String getMail(){
        return this.mail;
    } 

    public String getMorada(){
        return this.morada;
    }

    public String toString(){
        
        return ( )
    }

    
    public boolean verifymail( String x){
    
    }
    
    public boolean verifymorada( String x){
    
    }
    
    public boolean verifynif( long x, int key ){
        
    }
    
    
    
}
