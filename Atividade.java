import java.time.LocalDate;

/**
 * Esta classe implementa uma Atividade.
 * Uma Atividade é uma interface que irá representar as areas em que uma Empresa pode ter.
 * 
 * @author (Gonçalo Faria);
 * @version (v1);
 * 
 * @author (Guilherme Viveiros);
 * @version (v1);
 * 
 * @author (Angelo Andre);
 * @version (v1);
 */

public interface Atividade
{

    public String getCodidigoActividade()throws InvalidFieldException;
    public String getNomeActividade()throws InvalidFieldException;
    public boolean areaDedusivel();
    public double regraCalculo( Empresa x  ,LocalDate begin , LocalDate end);
    public double regraCalculo( Pessoa x ,LocalDate begin , LocalDate end);
    public Atividade clone();
}
