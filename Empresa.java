import java.lang.String;

public class Empresa extends Entidade 
{
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private String[] setores;
    // da me a classe da empresa para saber em que "setor" está agrupada
    private String[] classe;

    public Empresa(){  
       this.comum = new Entidade(); //Entidade
       
    }
    
    // cria me a minha empresa a partir de uma entidade dada (copia dados)
       public Empresa( long nif, String nome, String mail , String morada ){
        this.nif    = nif  ;
        this.nome   = nome ;
        this.mail   = mail ;
        this.morada = morada ;
        

    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    public Empresa ( Empresa x) {
         int comp = x.getsetores();
         int pmoc = x.getclasse();
         this.comun = x.getentid;
         System.arraycopy ( this.setores , 0 , x.getsetores() ,0 , comp.length); 
         System.arraycopy ( this.classe  , 0 , x.getsetores() ,0 , pmoc.length);     
         
    }

    
    // da me os setores de uma string
    public String[] getsetores() {
     return this.setores;   
    }

    public String[] getclasse() {
     return this.clasee;
    }
     
   // da me a entidade de uma "entidade" x.
    private comum getentid() {
     return this.comum;

    } 

    }
   
