import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data


public class Empresa extends Entidade 
{   
    Scanner s = new Scanner(System.in);
   // StringA ex = new StringA();
    //modulo para aceder a todas as funcoes da Entidade
    Entidade aux = new Entidade();
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private String[] setores;
    // verifica se algo está correto ou nao em termos de "Passwords"
    public boolean check;
    //
   
       
    public Empresa(){  
       this.comum = new Entidade(); //Entidade 
    }
    
   //falta acabar 
    public void rem_set ( String [] x){
     
    } 

    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    // colocei pass mas nao sei se preciso qualquer coisa tiro ::::::????????
       public Empresa( long nif, String nome, String mail , String morada ,  String[] x){
        System.out.println ("Digite uma password para a sua empresa");
        String pass =  s.nextLine();
        setPassword ( pass);

        this.comum = new Entidade(nif,nome,mail,morada);
        this.setSetores (x);
           
    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
        
         System.out.println ("Digite uma password para a sua empresa");
         String pass =  s.nextLine();
         setPassword ( pass);

         
         comum = new Entidade();   

         String[] set = x.getSetores();
      
         
         this.comum = new Entidade();
         setID ( x );

         // estou a meter o array de setores e classes na minha empresa
         System.arraycopy ( this.setores , 0 , x.getSetores() ,0 , set.length); 
            
         
    }
         
        public void setSetores ( String[] x) {
            this.setores = x;
        }
        
        
        // da me os setores de uma string
        public String[] getSetores() {
            return this.setores;   
        }

        // coloca uma "entidade" x na minha entidade comum
        public void setID ( Empresa x) {
            this.comum = x.comum;
        } 
        
        
        public String toString() {
            String space;
            String texto_final;
            space = "         ";
            texto_final = space + '\n';
            // junta o nome
            texto_final += "Nome :" + this.comum.getNome() + '\n' ;
            // junta o mail
            texto_final += "Mail :" + this.comum.getMail() + '\n'; 
            //junta o nif
            texto_final += "Nif :" + this.comum.getNif() + '\n';
            // junta a morada
            texto_final += "Morada :" + this.comum.getMorada() + '\n';
            //juntos os setores de determinada empresa
            texto_final += "Setores :" + this.getSetores() + '\n';
            
            texto_final += space;



          return texto_final;  

        }

  

    }
   
