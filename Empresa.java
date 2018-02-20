import java.lang.String;

public class Empresa extends Entidade 
{
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private String[] setores;

    public Empresa(){  
       this.comum = new Entidade(); //Entidade
     
       
    }
    
    public Empresa(Entidade x , int s ) {
        this.comum = new Entidade(x);
       
        
    }
    
    public Empresa ( Empresa x) {
         System.arraycopy ( this.setores , 0 , x. ,0 ,      
    }
    
  
    public String[] getsetores() {
     return this.setores;   
    }
        
    }
   
