
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
