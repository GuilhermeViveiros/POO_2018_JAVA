/**
 *
 *  Guilherme Viveiros -> nao mexer
 *  Classe empresa na qual esta submetida a uma entidade 
 */


public class Produto {

    private String nome;
    private int id;
    private double preco;

    public Produto() {
        this.nome = "invalido";
        this.id = 0;
        this.preco = 0.0;
    }

    public Produto(String x, int y, double c) {
        this.nome = x;
        this.id = y;
        this.preco = c;
    }

    public Produto(Produto x) {
        this.setNome(x.getNome());
        this.setId(x.getId());
        this.setPreco(x.getPreco());

    }

    //Getters!

    public double getPreco() {
        return this.preco;
    }

    public int getId() {
        return this.id;
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

    public void setId(int x) {
        this.id = x;
    }

    //so comparamos o ID e os Nomes , nao os precos
    public boolean equals(Produto obj) {
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=this.getClass()) return false;
        Produto p = /*(Produto)*/ obj;
        return (this.id == p.getPreco() && this.nome.equals( p.getNome()) );
    }

    public Produto clone() {
        return new Produto(this);
    }

    public String toString(){
        return this.nome + " " + this.preco;
    }
}
