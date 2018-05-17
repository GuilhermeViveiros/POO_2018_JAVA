import java.time.LocalDate;

public interface Atividade
{

    public String getCodidigoActividade()throws InvalidFieldException;
    public String getNomeActividade()throws InvalidFieldException;
    public boolean areaDedusivel();
    public double regraCalculo( Empresa x  ,LocalDate begin , LocalDate end);
    public double regraCalculo( Pessoa x ,LocalDate begin , LocalDate end);
    public Atividade clone();
}
