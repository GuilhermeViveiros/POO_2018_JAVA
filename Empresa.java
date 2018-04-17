/**
 *
 *  Guilherme Viveiros -> nao mexer
 *  Classe empresa na qual esta submetida a uma entidade 
 */

import java.util.*;
import java.util.stream.Collectors;
import java.lang.String;//string

public class Empresa extends Entidade {
    //tree Map com id associado a cada Fatura
    private TreeMap<Integer,Fatura> emissoes;
    //Map com a key sendo o nome de uma Pessoa , values é um conjunto de Faturas
    private Map<String,Set<Fatura>> cliente;
    //Map com id e respetivo Produto
    private Map<Integer,Produto> artigos;
    //setor da empresa
    private String area;

    public Empresa() {
        super();
        this.emissoes = new TreeMap<Integer , Fatura>();
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashMap<Integer, Produto>();
        this.area = "";
    }

    public Empresa(long nif, String nome, String mail, String morada, String setor, String telefone) {
        super(nif, nome, mail, morada, telefone);
        this.emissoes = new TreeMap<Integer , Fatura>();
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashMap<Integer, Produto>();
        this.area = setor;

    }

    public Empresa(Empresa x) {
        super(x.getContacto().getNif(), x.getContacto().getNome(), x.getContacto().getMail(),x.getContacto().getMorada(), x.getContacto().getTelefone());
        //estou a copiar os meus setores referentes ha empresa x  
        this.artigos = x.getArtigos();
        this.area = x.getArea();
    }
    
    

    
    //Setters!
    public void setArea(String x) {
        this.area = x;
    }
    
    
    //Getters!
    public Map<Integer,Produto> getArtigos() {
        return this.artigos.values().stream().map(Produto :: clone).collect(Collectors.toMap( p -> p.getId(), p -> p.clone() ));
    }
    
    public TreeMap<Integer , Fatura> getEmissoes(){
        return this.emissoes.values().stream().map(Fatura::clone).collect(Collectors.toMap( p->p.getId() , p -> p));
    }
    
    
    private TreeMap<Integer , Fatura> makeClienteBase(   Map<String,Set<Fatura>> x  ){
        
        TreeMap<Integer, Fatura > g = new TreeMap();
        
        for( Set<Fatura > l : x.values() ){
            
            for( Fatura k : l){
                g.put(k.getId() , k );
            }
        }
        return g;
    } 
           

    public String getArea() {
        return this.area;
    }
       
    
    //Metodos
    //Adiciona um novo Produto ao Map   
    public void AdicionarArtigo ( Produto x ) {  
        
        if( !this.artigos.containsKey( x.getId() ) )
            this.artigos.put( x.getId() , x );
    }

    //Remove um Produto
    public boolean RemoverArtigo(Produto x) {
        if (this.artigos.containsKey(x.getId())) {
            this.artigos.remove(x.getId());
            return true;
        }
        return false;
    }
    
    //Devolve a fatura emitida pela Empresa 
    public Fatura Fatura_emi(Empresa x ,List <Integer> compras ) {
         //= new Fatura(x.getContacto() , x.getArea() , compras);
        List produtos = this.artigos.values().stream().filter( l -> compras.contains(l.getId())).map(Produto :: clone).collect(Collectors.toList());
        Fatura f = new Fatura(x.getContacto() , x.getArea() , produtos);
        return f;
    }
    
    //toString
    public String toString() {
        return super.toString() + "\nEmpresa\nSetor economico : " + this.getArea() + "\n";
    }
}
