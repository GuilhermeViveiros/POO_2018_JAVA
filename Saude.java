import java.time.LocalDate;

public class Saude implements Atividade
{
    private String nome; //nome da atividade
    private String codigo; //codigo da atividade
    static int check = 1; //estou a assumir que irá contar para as despesas
   
    
    public Saude(){
        this.nome = "nenhum";
        this.codigo = "nenhum";  
    }

    public Saude(String nome , String codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public Saude(Saude x){
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
        if (check == 1) return true;
        return false;
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
    //(Algoritmo improvisado)
    public int hashCode(){
        return this.nome.length() % 2;
    }
    
    public Saude clone(){
        return new Saude(this);
    }
        
    public boolean equals(Object x){
        if(x == this) return true;
        if (x.getClass() != this.getClass() || x == null) return false;
        Saude y = (Saude) x;
        if ( (y.getNome().equals(this.codigo)) && (y.getCodigo().equals(this.codigo))) return true;
        return false;
    }
    
}

