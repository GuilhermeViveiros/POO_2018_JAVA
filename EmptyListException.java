public class EmptyListException extends Exception
{

    public EmptyListException(){
        super();
    }
    
    public EmptyListException(String x){
        super(x);
    }
    
    public EmptyListException(EmptyListException x){
        super(x);
    }
}
