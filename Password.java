
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
    private long password;

    public long code ( String pw ){
        this.key      = java.util.Random.nextInt();
        this.password = -1;
    }
    

    public Password(){
        this.key      = java.util.Random.nextInt();
        this.password = -1;
    }

    public boolean check(String entry ){
        
        return (this.code( entry ) == this.password) ;
    }
    
    
}
