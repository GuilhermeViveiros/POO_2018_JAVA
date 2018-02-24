import java.lang.String;

public class Empresa extends Entidade 
{   

    //modulo para aceder a todas as funcoes da Entidade
    Entidade aux = new Entidade();
    //esta Entidade ainda é nula 
    private Entidade comum ;
    //esta e a lista de setores que eu tenho , posso os remover adicionar .. StringA faz isso
    private String[] setores;
    // da me a classe da empresa para saber em que "setor" está agrupada
    private String[] classe;
    // verifica se algo está correto ou nao em termos de "Passwords"
    public boolean check;
 
       
    public Empresa(){  
       this.comum = new Entidade(); //Entidade
       
    }
    


    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    // colocei pass mas nao sei se preciso qualquer coisa tiro ::::::????????
       public Empresa( long nif, String nome, String mail , String morada , String pass){
       this.comum.setNif(nif) ;
       this.comum.setNome (nome);   
       this.comum.setMail (mail); 
       this.comum.setMorada (morada);
       // verifica se a pass está certa , ainda estou a decidir se é preciso
       check = aux.checkPassword(pass);
        

    }
    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa ( Empresa x) {
         String[] comp = x.getsetores();
         String[] pmoc = x.getclasse();
         
         this.comum = x;
         // estou a meter o array de setores e classes na minha empresa
         System.arraycopy ( this.setores , 0 , x.getsetores() ,0 , comp.length); 
         System.arraycopy ( this.classe  , 0 , x.getsetores() ,0 , pmoc.length);     
         
    }
          
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
            
            texto_final += space;



          return texto_final;  

        }

  

    }
   
