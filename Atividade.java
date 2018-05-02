public interface Atividade
{

    public String getCodidigoActividade();
    public String getNomeActividade();
    public boolean areaDedusivel();
    public double regraCalculo( Empresa x );
    public double regraCalculo( Pessoa x );
    public Atividade clone();
    public Atividade equals();
  
 
}
