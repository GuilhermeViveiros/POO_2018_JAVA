/**
 *
 *  Guilherme Viveiros -> nao mexer
 *  Classe empresa na qual esta submetida a uma entidade 
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.lang.String;//string
import java.util.HashMap;
import java.util.Map;

public class Empresa extends Entidade {
    //private String aglomerado;
    //private Entidade comum ;

    // Array List , tipo predefenido em java que me cria ( x,x,x)...com produtos , um identificador e um price
    private Map<Integer,Produto> artigos;
    //setor da empresa
    private String setor;

    public Empresa() {
        //crio entidade
        super();
        //crio os meus setores , String[] setores e quantos usados
        this.artigos = new HashMap<Integer, Produto>();
        //setor da empresa
        this.setor = "";
    }

    // cria me a minha empresa a partir de uma entidade dada 
    // neste caso so copia os valores da entidade
    public Empresa(long nif, String nome, String mail, String morada, String setor, String telefone) {

        super(nif, nome, mail, morada, telefone);
        this.artigos = new HashMap<Integer, Produto>();
        this.setor = setor;

    }

    // cria me a minha empresa a partir de uma empresa dada (copia dados)
    // copia os setores , as classes e a entidade
    public Empresa(Empresa x) {
        super(x.getNif(), x.getNome(), x.getMail(),x.getMorada(), x.getTelefone());
        //estou a copiar os meus setores referente ha empresa x  
        this.artigos = x.getArtigos();
        this.setor = x.getSetor();
    }

    //Setters!

    public void setSetor(String x) {
        this.setor = x;
    }

    //Getters!

    public Map<Integer,Produto> getArtigos() {
        return this.artigos.values().stream().collect(Collectors.toMap( p -> p.getId(), p -> p.clone() ));

    }

    // devolve me o setor 
    public String getSetor() {
        return this.setor;
    }

    // retorna o tamanho do array em questao
    public int quantidadeArtigos() {
        return this.artigos.size();
    }

    // adiciona um novo artigo(Produto) ao (ArrayList)    
    public void AdicionarArtigo ( Produto x ) {  
        
        if( !this.artigos.containsKey( x.getId() ) )
            this.artigos.put( x.getId() , x );
    }

    // boolean que me remove um determinado Produto
    public boolean RemoverArtigo(Produto x) {
        if (this.artigos.containsKey(x.getId())) {
            this.artigos.remove(x.getId());
            return true;
        }
        return false;
    }
    
    /* Fazer iterador pelo Map e transformar em String os produtos
    //Set of Entry
    Set< Entry <Integer, Produto>> setMap = artigos.entrySet();
    // Um iterador para o map
    Iterator< Entry <Integer, Produto >> iteratorMap = setMap.iterator();
    //funcao dedicada para testes individuais
    public void info(){
        System.out.println("\n HashMap com Multiple Values");
        
        // display all the elements
        while(iteratorMap.hasNext()) {

            Map.Entry<Integer,Produto> entry = (Map.Entry<Integer, Produto>) iteratorMap.next();

            Integer key = entry.getKey();

            Produto produto = entry.getValue();

            System.out.println("Key = '" + key + "' has values: " + produto);

        }
    }
    */
    public String toString() {
        return super.toString() + "\nEmpresa\nSetor economico : " + this.getSetor() + "\n";
    }
}
