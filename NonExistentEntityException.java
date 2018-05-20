/**
 * Esta classe implementa uma NonExistentEntityException.
 * Uma NonExistentEntityException é uma classe que faz parte das Atividades.
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

public class NonExistentEntityException extends Exception
{
  
    public NonExistentEntityException(){
        super();
    }

    public NonExistentEntityException(String x){
        super(x);
    }
    
    public NonExistentEntityException(NonExistentEntityException x){
        super(x);
    }
}
