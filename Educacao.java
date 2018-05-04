import java.time.LocalDate;

public class Educacao implements Atividade
{
   
    private String nome; //nome da atividade
    private String codigo; //codigo da atividade
    static int check = 1; //estou a assumir que irá contar para as despesas
   
    
    public Educacao(){
        this.nome = " ";
        this.codigo = " ";  
    }

    public Educacao(String nome , String codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public Educacao(Educacao x){
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
        return valor* 0.5;
    }
    //(Algoritmo improvisado)
    public double regraCalculo(Pessoa x){
        return (double)(x.getAgregado().size()) * 0.6;
    }

    public Educacao clone(){
        return new Educacao(this);
    }
        
    public boolean equals(Object x){
        if(x == this) return true;
        if (x.getClass() != this.getClass() || x == null) return false;
        Educacao y = (Educacao) x;
        if ( (y.getNome().equals(this.codigo)) && (y.getCodigo().equals(this.codigo))) return true;
        return false;
    }
    
}
