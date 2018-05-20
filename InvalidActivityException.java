/**
 * Esta classe implementa uma InvalidActivityException.
 * Uma InvalidActivityException é uma classe que faz parte das Atividades.
 * 
 * @author (Gonçalo Faria);
 * @version (v1);
 * 
 * @author (Guilherme Viveiros);
 * @version (v1);
 * 
 * @author (Angelo Andre);
 * @version (v1);
 */

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
