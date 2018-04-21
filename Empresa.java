
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
    // Tree Map associado a cada Fatura (ordenada por data)
    private TreeSet<Fatura> emissoes_data;
    // Tree Map associado a cada Fatura (ordenada por valor)
    private TreeSet<Fatura> emissoes_valor;
    // Faturas agrupadas por cliente que as 'recebeu'
    private Map<String, Set<Fatura>> cliente;
    //Map com id e respetivo Produto
    private Set<Produto> artigos;
    //Area da empresa
    //Tem que pertencer às áreas na variavel de classe.
    private String area;

    public Empresa() {
        super();
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.area = "empty";
    }

    public Empresa(long nif, String nome, String mail, String morada, String setor, String telefone) {
        super(new Contacto(nif, nome, mail, morada, telefone));
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
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
        // tem de ser mais restrito pois Têm de constar no conjunto de class que define as áreas.
        this.area = x;
    }

    //Getters!
    public Set<Produto> getArtigos() {
        return this.artigos.stream().map(Produto::clone).collect(Collectors.toSet());
    }

    public Set<Fatura> getEmissoes() {
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toSet());
    }

    public Map<String, Set<Fatura>> getCliente() {
        return this.cliente.entrySet().stream().collect(Collectors.toMap(l -> l.getKey(),
                l -> l.getValue().stream().map(Fatura::clone).collect(Collectors.toSet())));
    }

    //Privado -> apenas para os construtores pois nao queres clone da mesma informacao entre os mesmos
    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada na data
    private void makeClienteData() {

        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
                this.emissoes_data.add(k);
            }
        }
    }

    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada no valor
    private void makeClienteValue() {

        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
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
    public void AdicionarArtigo(Produto x) {

        if (!this.artigos.contains(x))
            this.artigos.add(x.clone());
    }

    //Adiciona e devolve a fatura emitida pela Empresa 
    public Fatura Fatura_emi(Entidade x, List<Produto> compras) {
        Fatura f = new Fatura(this.getContacto(), this.getArea(), compras);

        if (this.artigos.containsAll(compras)) { //estou a verificar se a lista de Produtos corresponde com a lista de produtos da minha empresa
            this.emissoes_data.add(f);
            this.emissoes_valor.add(f);

            if (!this.cliente.containsKey(x.getContacto().getNome())) {
                TreeSet<Fatura> l = new TreeSet<>(new Comparator<Fatura>() {
                    public int compare(Fatura x, Fatura y) {
                        //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                        return (-1) * x.comparePreco(y);
                    }
                });
                l.add(f);
                this.cliente.put(x.getContacto().getNome(), l);
            } else {
                this.cliente.get(x.getContacto().getNome()).add(f);
            }
            // private Map<String,Set<Fatura>> cliente;
        }
        return f.clone();
    }

    //Remove um Produto
    public boolean RemoveArtigo(Produto x) {
        if (this.artigos.contains(x)) {
            this.artigos.remove(x);
            return true;

        }
        return false;
    }

    public boolean RemoveRegisto(Entidade ent, Fatura x) {
        if (this.cliente.containsKey(ent.getContacto().getNome())) {
            Set<Fatura> fac_set = this.cliente.get(ent.getContacto().getNome());

            if (fac_set.contains(x)) {
                if (ent.removerFatura(x)) {
                    fac_set.remove(x);
                }
            }
        }
        return false;
    }

    //Metodo toString
    public String toString() {
        return super.toString() + "\nEmpresa\nSetor economico : " + this.getArea() + this.artigos.toString()
                + this.cliente.toString() + "\n";
    }

    //Metodo 6) Devolve as faturas emitidas pela Empresa , ordenadas 
    public List<Fatura> Faturas_data() { //ordenadas por data de emisssao
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> Faturas_data(LocalDate begin, LocalDate end) { //ordenadas por data de emisssao
        return this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> Faturas_valor() { //ordenadas por valor
        return this.emissoes_valor.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> Faturas_valor(LocalDate begin, LocalDate end) { //ordenadas por valor
        return this.emissoes_valor.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone).collect(Collectors.toList());
    }

    
    //Metodo 7) Lista das faturas por contribuinte num determinado intervalo de datas
    public List<Fatura> Faturas_tempo(LocalDate begin, LocalDate end, String nome_cliente) {
        if( this.cliente.containsKey(nome_cliente) ) {
            // existe;
            return this.cliente.get(nome_cliente).stream()
                                    .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone)
                                        .collect( Collectors.toList() );
        }
        return new ArrayList<Fatura>();

    }

    public List<Fatura> Faturas_tempo( String nome_cliente ) {
        
        if( this.cliente.containsKey(nome_cliente) ) {
            // existe;
            return this.cliente.get(nome_cliente).stream().map(Fatura::clone).collect( Collectors.toList() );
        }
        return new ArrayList<Fatura>();

    }
    
    //Metodo 8) Lista das faturas por contribuinte ordenadas por valor decrescente da despesa
    
    public List<Fatura> Faturas_despesa( String nome_cliente ) {
        
        if( this.cliente.containsKey(nome_cliente) ) {
            // existe;
            List<Fatura> l = this.Faturas_tempo( nome_cliente );
            l.sort( new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                    return (-1) * x.comparePreco(y);
                }
            } );
            return l;
        }
        return new ArrayList<Fatura>();

    }

    public List<Fatura> Faturas_despesa( LocalDate begin, LocalDate end, String nome_cliente ) {
        
        if( this.cliente.containsKey(nome_cliente) ) {
            // existe;
            List<Fatura> l = this.Faturas_tempo( begin , end ,nome_cliente );
            l.sort( new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                    return (-1) * x.comparePreco(y);
                }
            } );
            return l;
        }
        return new ArrayList<Fatura>();

    }
    
    //Metodo 9) Indicar o total faturado por uma Empresa num determinado periodo
    public double Total_faturado(LocalDate begin, LocalDate end) {
        return this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .mapToDouble(Fatura::getTotal).sum();
    }

    public double Total_faturado() {
        return this.emissoes_data.stream().mapToDouble(Fatura::getTotal).sum();
    }
}
