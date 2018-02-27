import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data


public class Empresa extends Entidade 
{   
   
    private Entidade comum ;
    //lista de produtos da Empresa
    private StringA produtos ;
    //setor da empresa
    private String setor ;
   
   
   
       
    public Empresa(){  
        //crio entidade
        this.comum = new Entidade();
        //crio os meus setores , String[] setores e quantos usados
        this.produtos = new StringA();
        this.setor = null;
    }


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
       public Empresa( long nif, String nome, String mail , String morada ,  StringA x , String pass){
        
        this.comum = new Entidade(nif,nome,mail,morada);
        this.comum.setPassword ( pass);

        this.produtos = new StringA ( x);
    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
        
         
          //estou a copiar os meus setores referente ha empresa x  
         this.produtos = new  StringA ( x.getSetores() ) ;
          // estou a copiar a entidade referente ha empresa x

         // identidade é privade ver melhor
         this.comum = new Entidade( x.getID() );
            
         
    }

    public void add_set ( String x ) {
      this.produtos.append(x);

    }

    public void rem_set ( String x){
     this.produtos.rmv(x);
    } 
         
        public void setSetores ( StringA x) {
            this.produtos = x;
        }
        
        
        // da me os setores de uma string
        public StringA getProdutos() {
            return this.produtos.clone();   
        }
      
        
        public int getLenSet(){
            return this.produtos.getU();
        }
//??????
        public Entidade getID () {
            return this.comum.clone();
        }
//??????
        // coloca uma "entidade" x na minha entidade comum
        public void setID ( Empresa x) {
            this.comum = new Entidade( x.comum ) ;
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
            texto_final += "Produtos :" + this.getProdutos() +  '\n';
            
            texto_final += space;



          return texto_final;  

        }

  

    }
   
