package Exception;

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
