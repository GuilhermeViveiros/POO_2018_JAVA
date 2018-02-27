import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data
import java.util.ArrayList; 

public class Empresa extends Entidade 
{   
    private String aglomerado;
   
    private Entidade comum ;
    
    private ArrayList<Produto> artigo ;
    //setor da empresa
    private String setor ;
   
   
   
       
    public Empresa(){  
        //crio entidade
        this.comum = new Entidade();
        //crio os meus setores , String[] setores e quantos usados
        this.artigo = new ArrayList();
        this.setor = null;
    }


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    public Empresa( long nif, String nome, String mail , String morada ,  String pass , String setor){
        
        this.comum = new Entidade(nif,nome,mail,morada);
        this.comum.setPassword (pass);
        this.setor = new String(setor);
        
 
        
    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
        
         //estou a copiar os meus setores referente ha empresa x  
         this.artigo =  x.getArtigo();
         // estou a copiar a entidade referente ha empresa x
         this.comum = new Entidade( x.getID() );
         
         this.setor = new String (x.getSetor());
            
         
    }
  


    //Setters!
    
    // super classe , os "filhos" 
    public void setAglomerado ( String x ) {
        this.aglomerado = x;
    }
    
    public void setSetores( ArrayList x) {
            this.artigo = x;
    }
        
    //Getters!
    
    public ArrayList getArtigo() {
        return this.artigo;
        
    }
    
    public String getSetor(){
        return this.setor;
    }
    // da me os setores de uma string
    // cast atrás para dar return do meu ArrayList em tipo Array de Produto
    public Produto[] getProduto() {
        return  (Produto []) this.artigo.toArray() ;   
    }
      
        
    public int getLenSet(){
        return this.artigo.size();
        }

    public Entidade getID() {
        return (new Entidade (this.comum));
        }
        

    public void add_product ( Produto x ) {
        // adiciona um artigo x ao meu ArrayList e numeros
      this.artigo.add(x);

    }
    
    public boolean rem_product( Produto x){
    // indexof é bollean que 
    if ( this.artigo.indexOf(x) == -1 ) return false;
    else {
        this.artigo.remove(this.artigo.indexOf(x)) ;
        return true;
     } 
    }
    
     public void Clone_ArrytoList ( ArrayList <Produto> aux){
         this.artigo = new ArrayList();
         
         for ( int i=1; i < aux.size() ; i++ ) {
             this.artigo.add ( aux.get(i) ) ;
            }
	
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
            texto_final += "Produtos :" + this.getProduto() +  '\n';
            
            texto_final += space;



       

        }

  

    }
   
