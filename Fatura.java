import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Escreva a descrição da classe Fatura aqui.
 * 
 * @author (Gonçalo Faria) 
 * @version (v1)
 */
public class Fatura {
    //
    private Contacto servidor;
    private String area;
    private String desc; 
    private LocalDate date;
    private List<Produto> compras;
    private double total;

    public Fatura() {
        this.servidor  = new Contacto();
        this.area = "campo vazio";
        this.desc = "campo vazio";
        this.total = 0.0;
        this.date =  LocalDate.now();
        this.compras = new ArrayList<Produto>();
    }

    public Fatura(Contacto x, String area , List<Produto> compras) {
        this.servidor = x.clone();
        this.area = area;
        this.desc = "campo vazio";
        this.date = LocalDate.now();
        this.compras = compras.stream().map(Produto::clone).collect( Collectors.toList() );
        this.total = compras.stream().mapToDouble( Produto::getPreco).sum();
    }

    public Fatura( Fatura x){
        this.servidor = x.getServidor();
        this.area = x.getArea();
        this.desc = x.getDescricao();
        this.date = x.getDate();
        this.compras = x.getCompras();
        this.total = x.getTotal();
    }
    // se o programa continuar assim vai andar num loop interno -> new fatura -> 2 new entidade -> new fatura -> ....
    // condensar pls.

    public int compareTo(Fatura b) {
        return (this.getDate().compareTo( b.getDate() ));
    }

    public int comparePreco(Fatura b){
        return (int)(b.getTotal() - this.total);
    }

    public boolean equals ( Object o ){

        if ( this == o ) return true;

        if (o==null || getClass() != o.getClass()) return false;

        Fatura inc = (Fatura) o;

        if ( (this.date == inc.getDate() ) &&
                this.servidor.equals( inc.getServidor() ) &&
                    this.compras.equals( inc.getCompras() ) )
            return true;

        return false;
    }

    public Fatura clone(){
        return new Fatura(this);
    }

    public LocalDate getDate(){
        return this.date;
    }

    public double getTotal(){
        return this.total;
    }

    public Contacto getServidor(){
        return this.servidor.clone();
    }

    public String getDescricao(){
        return this.desc;
    }

    public String getArea(){
        return this.area;
    }

    public List<Produto> getCompras(){
        return this.compras.stream().map(Produto::clone).collect( Collectors.toList() );
    }

    //------- Setters ---------------------------------

    public void setDate(LocalDate newd ){
        this.date = newd;
    }

    public void setServidor( Contacto serv){
        this.servidor = serv.clone();
    }

    public void setDesricao( String com ){
        this.desc = com;
    }

    public void setArea( String Area ){
        this.area = Area;
    }
    
    public void addCompra( Produto x ){
        this.compras.add( x.clone());
        this.total += x.getPreco();
    }
}
