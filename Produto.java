import java.io.Serializable;

/**
 * Esta classe implementa um Produto.
 * Uma Produto é uma classe que faz parte de uma Fatura e de uma Empresa(produtos vendidos)
 * 
 * @author (Gonçalo Faria);
 * @version (v1);
 * 
 * @author (Guilherme Viveiros);
 * @version (v1);
 * 
 * @author (Angelo Andre);
 * @version (v1);
 */

public class Produto implements Serializable{
    /** O nome do Produto */
    private String nome;
    /** O preço do Produto */
    private double preco;
    /** A area do Porduto */
    private Atividade area;

    /**
    * Construtor por omissão de Produto.
    */
    public Produto() {
        this.nome = "invalido";
        this.preco = 0.0;
        this.area = null;
    }

    /**
     * Construtor parametrizado de Produto.
     * @param String
     * @param Area
     * @param double
     */
    public Produto(String x, Atividade y, double c) {
        this.nome = x;
        this.preco = c;
        this.area = y.clone();
    }

    /**
     * Construtor de cópia de Produtos. Aceita como parâmetro outro Produto e
     * utiliza os métodos de acesso aos valores das variáveis de instância. 
     * @param Produto
     */
    public Produto(Produto x) {
        this.preco = x.getPreco();

        try{
            this.nome  = x.getNome();
        } catch (InvalidFieldException e ){
            this.nome = "invalido";
        }

        try{
            this.area = x.getArea();
        } catch( InvalidActivityException e){
            this.area = null;
        }

    }

    /**
     * Obtem o preco do Produto
     */
    public double getPreco() {
        return this.preco;
    }

    /**
     * Obtem o nome do Produto
     */
    public String getNome() throws InvalidFieldException {
        if( this.nome.equals("invalido") )
            throw new InvalidFieldException("Ainda não foi indicado nenhum nome");
        else
            return this.nome;

    }

    /**
     * Obtem a area do Produto
     */
    public Atividade getArea() throws InvalidActivityException {
        if(this.area == null)
            throw new InvalidActivityException(" Não foi indicada nenhuma atividade");
        else
            return this.area.clone();
    }
   
    /**
     * Redefine o Preco do Produto
     */
    public void setPreco(double x) {
        this.preco = x;
    }

    /**
     * Redefine o nome do Produto
     */
    public void setNome(String x) {
        this.nome = x;
    }

    /**
     * Redefine a area do Produto
     */
    public void setArea(Atividade x){
        this.area = x.clone();
    }

    /**
     * Método que determina se 2 Produtos são iguais.
     * Esta função é deterministica, reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso as Produtos sejam iguais e falso caso
     *         contrário.
     */
    public boolean equals(Produto obj) {
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=this.getClass()) return false;
        Produto p = /*(Produto)*/ obj;

        boolean r = false;
        boolean l;

        try{
            l = this.nome.equals( p.getNome());
        }catch (InvalidFieldException e ){
            l = (this.nome == "invalido");
            r = true;
        }

        if( r )
            l = l & (p.getPreco() == this.preco);
        
        return l;
    }

     /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Produto clone() {
        return new Produto(this);
    }

    /**
     * Método que devolve a representação em String de toda o Produto. 
     */
    public String toString(){
        return this.nome + " " + this.preco;
    }

    /**
     * Obtem um inteiros que representa um hascode através do nome do Produto
     */
    public int hashCode(){
        return (int)this.nome.hashCode();
    }
}

