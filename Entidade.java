
/**
 * Escreva a descrição da classe Entidade aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

public class Entidade
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private long nif;
    private String nome;
    private String mail;
    private String morada;
    private Password pw;

    public Entidade(){
        this.nif    = -1;
        this.nome   = " O Seu nome ";
        this.mail   = " utilizario@example.com";
        this.morada = " A sua morada ";
        this.pw     = new Password();
    }

    public Entidade( long ni_p, String nom_p, String mai_p , String morad_p ){
        this.nif    = ni_p  ;
        this.nome   = nom_p ;
        this.mail   = mai_p ;
        this.morada = morad_p ;
        this.pw     = new Password();

    }

    public Entidade( Entidade inc ){
        this.nif    = inc.getNif();
        this.nome   = inc.getNome();
        this.mail   = inc.getMail();
        this.morada = inc.getMorada();
        this.pw     = new Password();

        
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
