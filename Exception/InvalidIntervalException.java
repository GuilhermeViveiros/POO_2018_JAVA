package Exception;

public class InvalidIntervalException extends Exception
{
    public InvalidIntervalException(){
        super();
    }
    
    public InvalidIntervalException(String x){
        super(x);
    }
    
    public InvalidIntervalException(InvalidIntervalException x){
        super(x);
    }
}
