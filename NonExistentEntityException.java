public class NonExistentEntityException extends Exception
{
  
    public NonExistentEntityException(){
        super();
    }

    public NonExistentEntityException(String x){
        super(x);
    }
    
    public NonExistentEntityException(NonExistentEntityException x){
        super(x);
    }
}
