/**
 * Esta classe implementa uma Entidade.
 * Uma Entidade será a unidade basica à qual será aplicada tributação fiscal.
 * 
 * @author (Gonçalo Faria); 
 * @version (v1);
 */

public class Entidade
{
    // variáveis de instância 
    private long nif;
    private String nome;
    private String mail;
    private String morada;
    private String telefone;
    private Password pw;
    /**
     * Construtores de classe Entidade.
     * Declaração dos construtores por omissão(vazio),
     * parametrizado e de cópia.
     */

     /**
     * Construtor por omissão de Entidade.
     */
    public Entidade(){
        this.nif    = -1;
        this.nome   = " O Seu nome ";
        this.mail   = " utilizario@example.com";
        this.morada = " A sua morada ";
        this.pw     = new Password();
        this.telefone = "+351 000 000 000";
    }
    /**
     * Construtor parametrizado de Entidade.
     * Aceita como parâmetros os valores para cada variável de instância.
     */
    public Entidade( long ni_p, String nom_p, String mai_p , String morad_p , String telefone ){
        this.nif    = ni_p  ;
        this.nome   = nom_p ;
        this.mail   = mai_p ;
        this.morada = morad_p ;
        this.pw     = new Password();
        this.telefone = telefone;

    }
    /**
     * Construtor de cópia de Entidade.
     * Aceita como parâmetro outra Entidade e utiliza os métodos
     * de acesso aos valores das variáveis de instância.
     * A password não é copiada. É iniciada por omissão.
     */

    public Entidade( Entidade inc ){
        this.nif    = inc.getNif();
        this.nome   = inc.getNome();
        this.mail   = inc.getMail();
        this.morada = inc.getMorada();
        this.pw     = new Password();
        this.telefone = inc.getTelefone();
        // a password está vazia.
    }

    /**
     * Métodos de instância
     */

    /**
     * Devolve o valor do nif (numero de identifcação fiscal).
     * @return valor do nif.
     */
    // getters !! 
    public long getNif(){
        return this.nif;
    } 
    /**
     * Devolve uma string contendo o numero de telfone.
     * @return numero de telfone.
     */

    public String getTelefone(){
        return this.telefone;
    }
    /**
     * Devolve uma string contendo o nome da Entidade.
     * @return string com o nome.
     */
    public String getNome(){
        return this.nome;
    } 
    /**
     * Devolve uma string contendo o e-mail da Entidade.
     * @return string com o e-mail.
     */
    public String getMail(){
        return this.mail;
    } 

    /**
     * Devolve uma string contendo a morada da Entidade.
     * @return string com a morada.
     */
    public String getMorada(){
        return this.morada;
    }
    /**
     * Método que devolve a representação em String de toda a Entidade.
     * @return String com tudas as variáveis de instâncias(exceto password).
     */
    public String toStringg(){
        String text,space;
        space = "________________________________________\n";

        text  = space;
        text += "nome: " + this.nome + "\n\n";
        text += "nif: " + this.nif + "\n";
        text += space;

        text += "morada: " + this.morada + "\n";
        text += space;

        text += "mail: " + this.mail + "\n";
        text += "telefone: " + this.telefone + "\n";

        return ( text );
    }

    // setters!!
    /**
     * Atualiza o valor da variável de instância telfone.
     * @param telefone novo numero de telfone.
     */
    public void setTelefone(String telefone){
        this.telefone = telefone ;
    }
    /**
     * Atualiza o valor da variável de instância nome.
     * @param n novo nome da Entidade.
     */
    public void setNome( String n ){
        this.nome = n;
    }
    /**
     * Atualiza o valor da variável de instância mail.
     * @param n novo e-mail da Entidade.
     */
    public void setMail( String n ){
        this.mail = n;
    }
     /**
     * Atualiza o valor da variável de instância morada.
     * @param n nova morada da Entidade.
     */
    public void setMorada( String n ){
        this.morada = n;
    }

     /**
     * Atualiza o valor da palavra pass da entidade.
     * Convém confirmar a palavra pass antes de usar este método.
     * @param n nova palavra pass da Entidade.
     */
    public boolean setPassword( String n ){
        
        return this.pw.setPassword( n );
    }
    
     /**
     * Método que verifica se uma data String é a palavra pass da Entidade.
     * @param n possível palavra pass.
     */
    public boolean checkPassword( String n){
        
        return this.pw.check(n);
    }
    /**
     * Método que altera a palavra pass da Entidade.
     * Para isso terá que fornecer a palavra pass atual da Entidade.
     * Convém confirmar a nova palavra pass antes de usar este método.
     * @param next nova palavra ass. 
     * @param current proxima palavra pass.
     */
    public boolean AlterPassword( String next ,String current ){
        Password nPW = new Password();
        
        if ( this.pw.check( current ) ){
            nPW.setPassword( next );
            this.pw = nPW;
            
            return true;
        }
        return false;
    }

    /**
     * Método que determina se 2 Entidades são iguais.
     * Apenas é necessário o mesmo nif.
     * Esta função é deterministica, reflexiva, transitiva e simétrica.
     * @return booleano que é verdadeiro caso as Entidades sejam iguais e falso caso contrário. 
     */
    public boolean equals ( Object o ){

        if ( this == o ) return true;

        if (o==null || getClass() != o.getClass()) return false;

        Entidade inc = (Entidade) o;

        if ( (this.nif == inc.getNif()) )
            return true;

            // não é neces 

        return false;

    }
    /**
     * Método que faz o clone do objeto receptor da mensagem.
     * Para tal invoca o construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Entidade clone() {
        
        return (new Entidade(this));  
    }
    
}
