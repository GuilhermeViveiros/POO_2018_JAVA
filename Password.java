
/**
 * Escreva a descrição da classe Password aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Random;

public class Password
{
    private int key;
    private long pw;

    public Password(){
        this.key = java.util.Random.nextInt();
        this.pw  = -1;
    }

    public Password( Password x ,String pass ){
        if ( x.check( pass ) ){
            this.key = x.getKey();
            this.pw  = x.getPw(); 

        }
    }

    // get -> privados.
    private Password getKey(){
        return this.key ;
    }

    private Password getPw(){
        return this.pw ;
    }

    // set ->

    public void setPassword( String x){
        this.pw = this.encode( x );
    }
    // métodos publicos.
    public boolean check(String entry ){

        return (this.encode( entry ) == this.pw);

    }

    private long encode ( String pw ){
        
        long tmp=0;

        for(int i=0; i<pw.length() ; i++ ){
            tmp+=  (int)pw.charAt(i) * pow( this.key , (i+1) );
        }
        this.pw = tmp%MAX_VALUE;
    }
    
    
}
