/**
 * Esta classe implementa uma InvalidActivityException.
 * Uma InvalidActivityException é uma classe que faz parte das Atividades.
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

public class InvalidIntervalException extends Exception
{
    public InvalidIntervalException(){
        super();
    }
    
    public InvalidIntervalException(String x){
        super(x);
    }
    
    public InvalidIntervalException(InvalidIntervalException x){
        super(x);
    }
}
