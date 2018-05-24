public class InvalidConselhoException extends Exception
{
   public InvalidConselhoException(){
       super();
   }
   
   public InvalidConselhoException(String x){
       super(x);
   }
   
   public InvalidConselhoException(InvalidConselhoException x){
       super(x);
   }
}
