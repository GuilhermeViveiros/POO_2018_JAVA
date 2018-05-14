package FacException;

public class InvalidActivityException extends Exception{
    
    public InvalidActivityException(){
        super();
    }
    
    public  InvalidActivityException(String s){
        super();
    }
    
    public  InvalidActivityException (  InvalidActivityException s){
        super(s);
    }
     
}
