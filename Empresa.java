/**
 *
 *  Guilherme Viveiros -> nao mexer
 *  Classe empresa na qual esta submetida a uma entidade 
 */
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.String;//string

public class Empresa extends Entidade {
    //tree Map associado a cada Fatura (ordenada pelo compareTo da Fatura)
    private TreeSet<Fatura> emissoes_data;
    //tree Map associado a cada Fatura (ordenada pelo compareTo da Fatura)
    private TreeSet<Fatura> emissoes_valor;
    //Map com a key sendo o nome de uma Pessoa , values é um conjunto de Faturas
    private Map<String,Set<Fatura>> cliente;
    //Map com id e respetivo Produto
    private Set<Produto> artigos;
    //Area da empresa
    private String area;

    
    public Empresa() {
        super();
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor =  new TreeSet<>(
                                new Comparator<Fatura>(){
                                    public int compare(Fatura x , Fatura y){
                                        return x.comparePreco(y);
                                    }
                                    });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.area = "";
    }

    public Empresa(long nif, String nome, String mail, String morada, String setor, String telefone) {
        super(new Contacto(nif, nome, mail, morada, telefone));
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor =  new TreeSet<>(
                                new Comparator<Fatura>(){
                                    public int compare(Fatura x , Fatura y){
                                        return x.comparePreco(y);
                                    }
                                    });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.area = setor;

    }

    public Empresa(Empresa x) {
        super(x);
        this.cliente = x.getCliente();
        this.makeClienteData();
        this.makeClienteValue();
        this.artigos = x.getArtigos();
        this.area = x.getArea();
    }
    
    
    //Setters!
    public void setArea(String x) {
        this.area = x;
    }
    
    
    //Getters!
    public Set<Produto> getArtigos() {
        return this.artigos.stream().map(Produto :: clone).collect(Collectors.toSet());
    }
    
    public TreeSet<Fatura> getEmissoes(){
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
    }
    
    public Map<String,Set<Fatura>> getCliente(){
        return this.cliente.entrySet().stream().collect(
                    Collectors.toMap( l -> l.getKey() 
                    , l -> l.getValue().stream().map(Fatura::clone).collect( Collectors.toSet() )));
    }
    
    //Privado -> apenas para os construtores pois nao queres clone da mesma informacao entre os mesmos
    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada na data
    private void makeClienteData(){
        
        for(Set<Fatura> l : this.cliente.values() ){
            
            for(Fatura k : l){
                this.emissoes_data.add(k);
            }
        }
    } 
    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada no valor
    private void makeClienteValue (){
                                    
        for(Set<Fatura> l : this.cliente.values() ){
            
            for(Fatura k : l){
                this.emissoes_valor.add(k);
            }
        }                          
    }
    
    public String getArea() {
        return this.area;
    }
       
    
    //Metodos de classe
    
    //Add -----------------------------------
    //Adiciona um novo Produto ao Map   
    public void AdicionarArtigo (Produto x) {  
        
        if( !this.artigos.contains(x) )
            this.artigos.add(x.clone());
    }
    
    //Adiciona e devolve a fatura emitida pela Empresa 
    public Fatura Fatura_emi(Entidade x ,List <Produto> compras ) {
        Fatura f = new Fatura(x.getContacto() , this.getArea() , compras );
        
        if ( this.artigos.containsAll(compras)){ //estou a verificar se a lista de Produtos corresponde com a lista de produtos da minha empresa
            this.emissoes_data.add(f);
            this.emissoes_valor.add(f);
            
            if (!this.cliente.containsKey(x.getContacto().getNome())){
                TreeSet<Fatura> l = new TreeSet<>( new Comparator<Fatura>(){
                                    public int compare(Fatura x , Fatura y){
                                        //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                                        return (-1) * x.comparePreco(y);
                                        }
                                    });
                l.add(f);
                this.cliente.put(x.getContacto().getNome() , l);
            }
            else {
                this.cliente.get(x.getContacto().getNome()).add(f);
            }
        // private Map<String,Set<Fatura>> cliente;
        }
        return f.clone();
    }
    
    //Remove um Produto
    public boolean RemoveArtigo(Produto x){
        if (this.artigos.contains(x)) {
            this.artigos.remove(x);
            return true;
  
        }
        return false;
    }
    
    public boolean RemoveRegisto(Entidade ent,Fatura x){
        if (this.cliente.containsKey(ent.getContacto().getNome())){
            if (this.cliente.values().contains(x)){
                if(ent.removerFatura(x)){
                    this.cliente.values().remove(x);
                }
            }
        }  
        return false;
    }
    
    //Metodo toString
    public String toString() {
        return super.toString() + "\nEmpresa\nSetor economico : " + this.getArea() + "\n";
    }
    
    //Metodo 6) Devolve as faturas emitidas pela Empresa , ordenadas 
    public List Faturas_data(){ //ordenadas por data de emisssao
        return this.emissoes_data.stream().collect(Collectors.toList());
    }
    public List Faturas_valor(){ //ordenadas por valor
        return this.emissoes_valor.stream().collect(Collectors.toList());
    }
    
    //Metodo 7) Lista das faturas por contribuinte num determinado intervalo de datas
    public List Faturas_tempo(LocalDate begin , LocalDate end){
            List res = this.emissoes_data.stream().filter(l->l.getDate().isAfter(begin)).filter(l->l.getDate().isBefore(end)).
            map(Fatura::clone).collect(Collectors.toList());
            return res;
    }
   
    //Metodo 8) Lista das faturas por contribuinte ordenadas por valor decrescente da despesa
    public List Faturas_despesa(){
          return this.cliente.values().stream().collect(Collectors.toList());
    }
    
    //Metodo 9) Indicar o total faturado por uma Empresa num determinado periodo
    public double Total_faturado(LocalDate begin , LocalDate end){
        return this.emissoes_valor.stream().mapToDouble(Fatura :: getTotal).sum();
    }
}

