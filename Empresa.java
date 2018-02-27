import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data
import java.util.ArrayList;

public class Empresa extends Entidade 
{   
   
    private Entidade comum ;
    //lista de produtos da Empresa
    private ArrayList<String> produtos ;
    //setor da empresa
    private String setor ;
   
   
   
       
    public Empresa(){  
        //crio entidade
        this.comum = new Entidade();
        //crio os meus setores , String[] setores e quantos usados
        this.produtos = new ArrayList<String>();
        this.setor = null;
    }


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    public Empresa( long nif, String nome, String mail , String morada ,  ArrayList products , String pass , String setor){
        
        this.comum = new Entidade(nif,nome,mail,morada);
        this.comum.setPassword ( pass);
        this.setor = new String(setor);
        this.produtos = new ArrayList <String>(products);
    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
        
         //estou a copiar os meus setores referente ha empresa x  
         this.produtos = new  ArrayList<String>( x.getProdutos() ) ;
         // estou a copiar a entidade referente ha empresa x
         this.comum = new Entidade( x.getID() );
         this.setor = new String (x.getSetor());
            
         
    }
  


    //Setters!

    public void setSetores ( ArrayList x) {
            this.produtos = x;
    }
        
    //Getters!
    public String getSetor(){
        return this.setor;
    }
    // da me os setores de uma string
    public ArrayList getProdutos() {
        return ( new ArrayList (this.produtos));   
    }
      
        
    public int getLenSet(){
        return this.produtos.size();
        }

    public Entidade getID() {
        return (new Entidade (this.comum));
        }

      


    public void add_product ( String x ) {
      this.produtos.add(x);

    }

    public void rem_product( String x){
     this.produtos.remove(x);
    } 


    public String info () {
            String space;
            String texto_final;
            space = "         ";
            texto_final = space + '\n';
            // junta o nome
            texto_final += "Nome :" + this.comum.getNome() + '\n' ;
            // junta o mail
            texto_final += "Mail :" + this.comum.getMail() + '\n'; 
            // junta a morada
            texto_final += "Morada :" + this.comum.getMorada() + '\n';
            //juntos os setores de determinada empresa
            texto_final += "Setor economico :" + this.getSetor() +  '\n';

            texto_final += space;



          return texto_final;  

        }
         
     public void toString_extends( String texto_final) {

            texto_final = toString();
            String space;
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
            //junta o setor da empresa
            texto_final += "Setor economico :" + this.getSetor() +  '\n';
            //junta os produtos todos da empresa 
            texto_final += "Produtos :" + this.getProdutos() +  '\n';
            
            texto_final += space;



       

        }

  

    }
   
