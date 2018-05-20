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

public class InvalidFieldException extends Exception
{
    public InvalidFieldException(){
        super();
    }
    
    public InvalidFieldException(String x){
        super(x);
    }
    
    public InvalidFieldException(InvalidFieldException x){
        super(x);
    }
}
