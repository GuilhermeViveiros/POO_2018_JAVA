/**
 * Esta classe implementa uma EmptySetException.
 * Uma EmptySetException é uma classe que faz parte das Atividades.
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

public class EmptySetException extends Exception
{
    public EmptySetException(){
        super();
    }
    
    public EmptySetException(String s){
        super(s);
    }
    
    public EmptySetException(EmptySetException x){
        super(x);
    }
  
}
