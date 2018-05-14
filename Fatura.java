import java.time.LocalDate;
import java.util.*;
import java.io.Serializable;

/**
 * Escreva a descrição da classe Fatura aqui.
 * 
 * @author (Gonçalo Faria)
 * @version (v1)
 */

public class Fatura implements Serializable{
    //
    private Contacto servidor;
    private Atividade area;
    private long nifcliente;
    private String desc;
    private LocalDate date;
    private List<Produto> compras;
    private double total;

    public Fatura() {
        this.servidor = new Contacto();
        this.area = null;
        this.desc = "campo vazio";
        this.total = 0.0;
        this.nifcliente = -1;
        this.date = LocalDate.now();
        this.compras = new ArrayList<Produto>();
    }

    public Fatura(Contacto x, Atividade area, long nifCliente, List<Produto> compras) {
        this.servidor = x.clone();
        this.area = area.clone();
        this.desc = "campo vazio";
        this.date = LocalDate.now();
        this.nifcliente = nifCliente;
        this.compras = compras.stream().map(Produto::clone).collect(Collectors.toList());
        this.total = compras.stream().mapToDouble(Produto::getPreco).sum();
    }

    public Fatura(Fatura x) {
        this.servidor = x.getServidor();
        this.area = x.getArea();
        this.desc = x.getDescricao();
        this.date = x.getDate();
        this.nifcliente = x.getCnif();
        this.compras = x.getCompras();
        this.total = x.getTotal();
    }
    // se o programa continuar assim vai andar num loop interno -> new fatura -> 2
    // new entidade -> new fatura -> ....
    // condensar pls.

    public int compareTo(Fatura b) {
        return (this.getDate().compareTo(b.getDate()));
    }

    public int comparePreco(Fatura b) {
        return (int) (b.getTotal() - this.total);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Fatura inc = (Fatura) o;

        if (this.area.equals(inc.getArea()) && (this.date == inc.getDate()) && this.servidor.equals(inc.getServidor())
                && this.compras.equals(inc.getCompras()) && this.nifcliente == inc.getCnif())
            return true;

        return false;
    }

    public Fatura clone() {
        return new Fatura(this);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getTotal() {
        return this.total;
    }

    public Contacto getServidor() {
        return this.servidor.clone();
    }

    public String getDescricao() throws InvalidFieldException{
        if( this.desc.equals("campo vazio") )
            throw new InvalidFieldException();
        else
            return this.desc;
    }

    public Atividade getArea() throws InvalidActivityException {
        if( this.area == null)
            throw new InvalidActivityException("area nao pode ser nula");
        else
            return this.area.clone();
    }

    public List<Produto> getCompras() throws EmptyListException{
        if( this.compras.size() == 0 )
            throw new EmptyListException("Lista de compras nao pode ser vazia");
        else
            return this.compras.stream().map(Produto::clone).collect(Collectors.toList());
    }

    public long getCnif() throws InvalidFieldException{
        if( this.nifcliente == -1)
            throw new InvalidFieldException("Nif inapropriado");
        else
            return this.nifcliente;
    }

    // ------- Setters ---------------------------------
    public void setCnif(long nif) {
        this.nifcliente = nif;
    }

    public void setDate(LocalDate newd) {
        this.date = newd;
    }

    public void setServidor(Contacto serv) {
        this.servidor = serv.clone();
    }

    public void setDesricao(String com) {
        this.desc = com;
    }

    public void setArea(Atividade area) {
        this.area = area.clone();
    }

    public void addCompra(Produto x) {
        this.compras.add(x.clone());
        this.total += x.getPreco();
    }
}
