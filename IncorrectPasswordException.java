/**
 * Esta classe implementa uma IncorrectPasswordException.
 * Uma IncorrectPasswordException é uma classe que faz parte das Atividades.
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

public class IncorrectPasswordException extends Exception
{   
   public IncorrectPasswordException(){
       super();
   }
   
   public IncorrectPasswordException(String x){
       super(x);
   }
   
   public IncorrectPasswordException(IncorrectPasswordException x){
       super(x);
   }
}
