
/**
 * Escreva a descrição da classe Entidade aqui.
 * 
 * @author (Gonçalo Faria); 
 * @version (v1);
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
        // a password está vazia.
    }
    // getters !! 

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
        String text,space;
        space = "________________________________________\n";

        text  = space;
        text += "nome: " + this.nome + "\n\n";
        text += "nif: " + this.nif + "\n";
        text += space;

        text += "morada: " + this.morada + "\n";
        text += space;

        text += "mail: " + this.mail + "\n";

        return ( text );
    }

    // setters!!

    public void setNif( long n){
        this.nif = n;
    }

    public void setNome( String n ){
        this.nome = n;
    }

    public void setMail( String n ){
        this.mail = n;
    }

    public void setMorada( String n ){
        this.morada = n;
    }

    public boolean setPassword( String n ){
        
        return this.pw.setPassword( n );
    }
    
    public boolean checkPassword( String n){
        return this.pw.check(n);
    }
    
    public boolean AlterPassword( String next ,String current ){
        Password nPW = new Password();
        
        if ( this.pw.check( current ) ){
            nPW.setPassword( next );
            this.pw = nPW;
            
            return true;
        }
        return false;
    }

    public boolean equals ( Object o ){

        if ( this == o ) return true;

        if (o==null || getClass() != o.getClass()) return false;

        Entidade inc = (Entidade) o;

        if ( (this.nif == inc.getNif()) && (this.morada == inc.getMorada()) && (this.nome == inc.getNome()) && (this.mail == inc.getMail() ) )
            return true;

        return false;

    }

    public Entidade clone() {

        return (new Entidade(this));  
    }
}
