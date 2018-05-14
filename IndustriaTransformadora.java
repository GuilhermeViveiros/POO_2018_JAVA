 
import java.time.LocalDate;

public class IndustriaTransformadora implements Atividade
{
   
    private String nome; //nome da atividade
    private String codigo; //codigo da atividade
    static int check = 0; //estou a assumir que nao irá contar para as despesas
   
    
    public IndustriaTransformadora(){
        this.nome = "nenhum";
        this.codigo = "nenhum";  
    }

    public IndustriaTransformadora(String nome , String codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public IndustriaTransformadora(IndustriaTransformadora x){
        this.nome = x.getNome();
        this.codigo = x.getCodigo();
    }

    //Getters!
    public String getNome(){return this.nome;}
    public String getCodigo(){return this.codigo;}

    //Metodos
    public String getCodidigoActividade(){
        return getCodigo();
    }
    public String getNomeActividade(){
        return getNome();
    }

    public boolean areaDedusivel(){
        return check == 1;
    }
    
    //(Algoritmo improvisado)
    public double regraCalculo( Empresa x , LocalDate begin , LocalDate end ){
        double valor = x.Total_faturado(begin,end);
        return valor* 0.4;
    }
    //(Algoritmo improvisado)
    public double regraCalculo(Pessoa x){
        return (double)(x.getAgregado().size()) * 0.3;
    }

    public IndustriaTransformadora clone(){
        return new IndustriaTransformadora(this);
    }
        
    public boolean equals(Object x){
        if(x == this) return true;
        if (x.getClass() != this.getClass() || x == null) return false;
        IndustriaTransformadora y = (IndustriaTransformadora) x;
        return (y.getNome().equals(this.codigo)) && (y.getCodigo().equals(this.codigo));
    }
    
}
