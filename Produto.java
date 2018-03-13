import java.util.ArrayList; 

public class Produto
{

    private String products; //= new ArrayList<String>(); 
    private int identificador;
    private double price; 

public Produto () {
    products = null;
    identificador = 0;
    price = 0.0;
}

public Produto ( String x , int y , double c) {
    this.products = x;
    this.identificador = y;
    this.price = c;
}


public Produto ( Produto x ) {
    this.setProduct(x.products);
    this.setIdf(x.identificador);
    this.setPrice (x.price);

}

//Getters!

public double getPrice () {
    return this.price;
}
public int getIdf () {
    return this.identificador;
}

public String getProduct () {
     return this.products;
}

//setters!

public void setPrice ( double x) {
    this.price = x;
}

public void setProduct(String x ) {
    this.products = x;
}

public void setIdf(int x) {
    this.identificador = x;
}



}
