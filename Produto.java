import java.io.Serializable;

public class Produto implements Serializable{

    private String nome;
    private double preco;
    private Atividade area;

    public Produto() {
        this.nome = "invalido";
        this.preco = 0.0;
        this.area = null;
    }

    public Produto(String x, Atividade y, double c) {
        this.nome = x;
        this.preco = c;
        this.area = y.clone();
    }

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

    //Getters!
    public double getPreco() {
        return this.preco;
    }

    public String getNome() throws InvalidFieldException {
        if( this.nome.equals("invalido") )
            throw new InvalidFieldException("Ainda não foi indicado nenhum nome");
        else
            return this.nome;

    }

    public Atividade getArea() throws InvalidActivityException {
        if(this.area == null)
            throw new InvalidActivityException(" Não foi indicada nenhuma atividade");
        else
            return this.area.clone();
    }
    //setters!

    public void setPreco(double x) {
        this.preco = x;
    }

    public void setNome(String x) {
        this.nome = x;
    }

    public void setArea(Atividade x){
        this.area = x.clone();
    }

    //so comparamos o ID e os Nomes , nao os precos
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

    public Produto clone() {
        return new Produto(this);
    }

    public String toString(){
        return this.nome + " " + this.preco;
    }

    public int hashCode(){
        return (int)this.nome.hashCode();
    }
}
