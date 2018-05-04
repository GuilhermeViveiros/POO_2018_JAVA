import java.time.LocalDate;

public interface Atividade
{

    public String getCodidigoActividade();
    public String getNomeActividade();
    public boolean areaDedusivel();
    public double regraCalculo( Empresa x  ,LocalDate begin , LocalDate end);
    public double regraCalculo( Pessoa x);
    public Atividade clone();
    public boolean equals(Object o);
  
 
}
