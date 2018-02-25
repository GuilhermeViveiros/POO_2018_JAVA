import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data


public class Empresa extends Entidade 
{   
    Scanner s = new Scanner(System.in);
    //modulo para aceder a todas as funcoes da Entidade
    Entidade aux ;
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private StringA setores = new StringA();
    // verifica se algo está correto ou nao em termos de "Passwords"
   
   
       
    public Empresa(){  
        //crio entidade
        aux = new Entidade();
        //crio os meus setores , String[] setores e quantos usados
        setores = new StringA();
    }
    
    public void add_set ( String x ) {
        if ( this.setores.full() == true ) this.setores.tabledoubling();
         else this.setores.append(x);

    }

   //falta acabar 
    public void rem_set ( String x){
     this.setores.rmv(x);
    } 

    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
       public Empresa( long nif, String nome, String mail , String morada ,  StringA x){
        System.out.println ("Digite uma password para a sua empresa");
        String pass =  s.nextLine();
        setPassword ( pass);
        
        this.comum = new Entidade(nif,nome,mail,morada);
        this.setores = new StringA ( x );
           
    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
        
         System.out.println ("Digite uma password para a sua empresa");
         String pass =  s.nextLine();
         setPassword ( pass);

         
          //estou a copiar os meus setores referente ha empresa x  
         this.setores = new  StringA ( getSetores () ) ;
          // estou a copiar a entidade referente ha empresa x
         this.comum = new Entidade( x.getID() );
            
         
    }
         
        public void setSetores ( StringA x) {
            this.setores = x;
        }
        
        
        // da me os setores de uma string
        public StringA getSetores() {
            return this.setores;   
        }
      
        
        public int getUsed_setores(){
            return this.setores.getU();
        }

        public Entidade getID () {
            return this.comum;
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
            texto_final += "Setores :" + this.getSetores() +  '\n';
            
            texto_final += space;



          return texto_final;  

        }

  

    }
   
