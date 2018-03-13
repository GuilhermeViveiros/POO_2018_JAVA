import java.util.Scanner;//scanner
import java.lang.String;//string
import java.time.LocalDate;// é bom para comparar datas e isso
import java.sql.Date; //data presente
import java.text.SimpleDateFormat; //formato da data
import java.util.ArrayList; 
import java.lang.StringBuilder; // mete -me o meu ArrayList para String

public class Empresa 
{   
    private String aglomerado;
   
    private Entidade comum ;
    
    // Array List , tipo predefenido em java que me cria ( x,x,x)...com produtos , um identificador e um price
    private ArrayList<Produto> artigo = null ;
    //setor da empresa
    private String setor ;
   
   
    public Empresa(){  
        //crio entidade
        this.comum = new Entidade();
        //crio os meus setores , String[] setores e quantos usados
        this.artigo = new ArrayList();
        //setor da empresa
        this.setor = null;
    }


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    public Empresa( long nif, String nome, String mail , String morada ,  String pass , String setor ,String number ){
        
        this.comum = new Entidade(nif,nome,mail,morada, number);
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
    // devolve me o setor 
    public String getSetor(){
        return this.setor;
    }
   
    // cast atrás para dar return do meu ArrayList em tipo Array de Produto
    public Produto[] getProduto() {
        if (this.artigo == null) {
            return new Produto[1];
        }
        
        //this.artigo.t;
        return   this.artigo.toArray(new Produto[0]) ;   
    }
    
    
    // devolve me a string com todos os meus produtos
    public String ArrayList_for_String  ( Produto [] x) {
        
        String res = new String(); 
        res = "";
         // vou buscar a minha lista de produtos "artigos";
         for (int i= 0 ; i < x.length ; i++) {
             // estou a transformar tudo em String
             res += "    " + x[i].getPrice() + "--" + x[i].getIdf() + "--" + x[i].getProduct() + "||||";
          
            }   
      return res;
    }
   
      
    // retorna o tamanho do array em questao
    public int getLenSet(){
        return this.artigo.size();
        }
    // retorna me a entidade
    public Entidade getID() {
        return (new Entidade (this.comum));
        }
        
        
        // adiciona um novo artigo(Produto) ao (ArrayList)    
    public boolean add_product ( Produto x ) {  
     if ( this.artigo == null ) this.artigo = new ArrayList();
     return  this.artigo.add(x);
    }
    // boolean que me remove um determinado Produto
    public boolean rem_product( Produto x){
    // indexof é bollean que devolve o index da posicao x no array , se houver
    if ( this.artigo.indexOf(x) == -1 ) return false;
    else {
        // remove me o Produto x do array
        this.artigo.remove(this.artigo.indexOf(x)) ;
        return true;
     } 
    }
    
    // minha funcao clone para ArrayList de produtos
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
            
            texto_final += "Produtos :" + ArrayList_for_String(this.getProduto()) +  '\n';
             
            texto_final += space;



          return texto_final;  

        }
         
     public String toString() {
             String space = " ";
         
            return this.comum.toString() +
            //junta o setor da empresa
            "Setor economico :" + this.getSetor() +  '\n' ;
              
        }
    }
   
