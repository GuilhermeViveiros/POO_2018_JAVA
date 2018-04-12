
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

    public boolean equals(Produto x) {
        return ((this.id == x.getId()) && (this.nome == x.getNome()));
    }

    public Produto clone() {
        return new Produto(this.nome, this.id, this.preco);
    }

}
