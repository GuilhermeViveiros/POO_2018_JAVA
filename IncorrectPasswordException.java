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
