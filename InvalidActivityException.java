public class InvalidActivityException extends Exception{
    
    public InvalidActivityException(){
        super();
    }
    
    public  InvalidActivityException(String s){
        super(s);
    }
    
    public  InvalidActivityException ( InvalidActivityException s){
        super(s);
    }
     
}
