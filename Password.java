
/**
 * Escreva a descrição da classe Password aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class Password 
{
    private int key;
    private long pw;

    public Password(){
        Random randomno = new Random();
        
        this.key = randomno.nextInt( Integer.MAX_VALUE - 1 ) + 1;
        this.pw  = -1;
    }

    public boolean empty(){
        return ( this.pw == -1 );
    }
    
    // set ->

    public boolean setPassword( String x){
        if ( this.empty() ){
            this.pw = this.encode( x );
            return true;
        }  
        
        return false;
    }
    
    // métodos publicos.
    public boolean check(String entry ){

        return (this.encode( entry ) == this.pw);

    }

    private long encode ( String pp ){
        
        long tmp=0;

        for(int i=0; i<pp.length() ; i++ ){
            tmp+=  (int)pp.charAt(i) * Math.pow( this.key , (i+1) );
        }
        return tmp%Integer.MAX_VALUE;
        
    }
    
    
}
