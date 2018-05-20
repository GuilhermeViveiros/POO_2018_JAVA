/**
 * Esta classe implementa uma EmptyMapException.
 * Uma EmptyMapException é uma classe que faz parte das Atividades.
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

public class EmptyMapException extends Exception
{
    public EmptyMapException(){
        super();
    }
    
    public EmptyMapException(String x){
        super(x);
    }
    
    public EmptyMapException(EmptyMapException x){
        super(x);
    }
}
