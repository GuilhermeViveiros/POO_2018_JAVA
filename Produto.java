/**
 *
 */

public class Produto {

    private String nome;
    private double preco;

    public Produto() {
        this.nome = "invalido";
        this.preco = 0.0;
    }

    public Produto(String x, int y, double c) {
        this.nome = x;
        this.preco = c;
    }

    public Produto(Produto x) {
        this.setNome(x.getNome());
        this.setPreco(x.getPreco());

    }

    //Getters!

    public double getPreco() {
        return this.preco;
    }

    public String getNome() {
        return this.nome;
    }

    //setters!

    public void setPreco(double x) {
        this.preco = x;
    }

    public void setNome(String x) {
        this.nome = x;
    }

    //so comparamos o ID e os Nomes , nao os precos
    public boolean equals(Produto obj) {
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=this.getClass()) return false;
        Produto p = /*(Produto)*/ obj;
        return (this.nome.equals( p.getNome()) );
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
