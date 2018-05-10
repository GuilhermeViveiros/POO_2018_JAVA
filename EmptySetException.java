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
