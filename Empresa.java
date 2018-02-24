import java.lang.String;

public class Empresa extends Entidade 
{   

    //modulo para aceder a todas as funcoes da Entidade
    Entidade Entidad3 = new Entidade();
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private String[] setores;
    // da me a classe da empresa para saber em que "setor" está agrupada
    private String[] classe;
    // verifica se algo está correto ou nao em termos de "Passwords"

    // _____-------------_______________-
    private bollean check;
    // check = checkPass();  -> a empresa recebe a pass????
    

    public Empresa(){  
       this.comum = new Entidade(); //Entidade
       
    }
    


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
       public Empresa( long nif, String nome, String mail , String morada ){
        this.comum.nif    = nif  ;
        this.comum.nome   = nome ;
        this.comum.mail   = mail ;
        this.comum.morada = morada ;
       
        

    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
         String[] comp = x.getsetores();
         String[] pmoc = x.getclasse();
         
         this.comum = x;
         
         System.arraycopy ( this.setores , 0 , x.getsetores() ,0 , comp.length); 
         System.arraycopy ( this.classe  , 0 , x.getsetores() ,0 , pmoc.length);     
         
    }

        
          // retorna me o nif da
        public long nif = Entidad3.getNif();
        // retorna me o nome
        public String nome = Entidad3.getNome();
        // retorna me o mail 
        public String mail = Entidad3.getMail();
        // retorna me a morada
        public String morada = Entidad3.getMorada();
        // -> verifica se a pass está correta ou nao
        public boolean checkPass = Entidad3.checkPassword();

   
        // da me os setores de uma string
        public String[] getsetores() {
            return this.setores;   
        }
        // da me as classes das empresas
        public String[] getclasse() {
            return this.classe;
        }

        // coloca uma "entidade" x na minha entidade comum
        //public Entidade getID() {
        //    return this.comum;
        //} 


  

    }
   
