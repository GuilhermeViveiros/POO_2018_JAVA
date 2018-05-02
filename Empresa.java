
/**
 *  Classe empresa na qual esta submetida a uma entidade 
 */
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

import java.lang.String;//string

public class Empresa extends Entidade {
    // Tree associado a cada Fatura (ordenada por data)
    private TreeSet<Fatura> emissoes_data;
    // Tree  associado a cada Fatura (ordenada por valor)
    private TreeSet<Fatura> emissoes_valor;
    // Map associados a uma Pessoa e todas as suas Faturas
    private Map<String, Set<Fatura>> cliente;
    //Set com todos os produtos de uma empresa
    private Set<Produto> artigos;
    //Conjunto de areas que cada empresa pode ter
    private static Set<String> areas;
    //Conjunto de aeras que um empresa tem!
    private Set<String> setores;


    static {
        areas = new HashSet<String>();
        //this.area.add("Gestao");
        areas.add("Saúde");
        areas.add("Educação");
    }

    /**
    * Construtor por omissão de Empresa.
    */
    public Empresa() {
        super();
        //quando nao declaramos nenhuma função para o comparator ele assume a dele ou usa a nossa funcao se tiver o nome de ->"compare"
        //neste caso ja temos , em que a comparaçao é feita a partir da data
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.setores = new HashSet<String>();
    }

    /**
     * Construtor parametrizado da Empresa.
     * Aceita como parâmetros os valores para cada variável de instância da sua Entidade.
     */
    public Empresa(long nif, String nome, String mail, String morada, String telefone,Set<String> setor) {
        super(new Contacto(nif, nome, mail, morada, telefone));
        this.emissoes_data = new TreeSet<Fatura>();

        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.setores = new HashSet<String>();
        for (String x : setor) {
            //tem que pertencer as areas para conseguir entrar para os setores da empresa
            if (areas.contains(x)) {
                this.setores.add(x);
            }
        }
    }

    /**
     * Construtor de cópia da Empresa.
     * Aceita como parâmetro outra Empresa e utiliza os métodos
     * de acesso aos valores das variáveis de instância.
     * As faturas dos clientes ordenadas por data e valor não são copiadas. 
     */
    public Empresa(Empresa x) {
        super(x);
        this.cliente = x.getCliente();

        this.makeClienteData();
        this.makeClienteValue();

        this.artigos = x.getArtigos();
        this.setores = x.getSetores();
    }

    //Privado -> apenas para os construtores pois nao queres clone da mesma informacao entre os mesmos
    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada na data
    private void makeClienteData() {
        this.emissoes_data = new TreeSet<>();
        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
                this.emissoes_data.add(k);
            }
        }
    }

    //atraves do Map criamos um set de Faturas iguais com ordenacao baseada no valor
    private void makeClienteValue() {
        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
                this.emissoes_valor.add(k);
            }
        }
    }

    /**
    * Métodos de instância-------------------------------------------------------------------------------------------------------
    */

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

    public Set<String> getSetores() {
        return this.setores.stream().collect(Collectors.toSet());
    }


    //Método que adiciona e devolve a fatura emitida pela Empresa de um determinado Setor
    public Fatura Fatura_emi(Entidade x, List<Produto> compras, String setor) {

        if (areas.contains(setor)) {
            Fatura f = new Fatura(this.getContacto(), setor, x.getContacto().getNif(),
                    compras.stream().map(Produto::clone).collect(Collectors.toList()));

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
        return null;
    }

    //Método que remove um Setor
    public boolean RemoveSetor(String x) {
        if (this.setores.contains(x)) {
            this.setores.remove(x);
            return true;
        }
        return false;
    }

    //Método que adiciona um Setor
    public boolean AdicionaSetor(String x) {
        if (this.setores.contains(x)) {
            this.setores.add(x);
            return true;
        }
        return false;
    }

    //Método que troca um conjunto de setores
    public void SetSetores(Set<String> x) {
        this.setores = x.stream().filter(l -> areas.contains(l)).collect(Collectors.toSet());
    }

    //Método que remove uma area
    public boolean RemoveArea(String x) {
        if (areas.contains(x)) {
            areas.remove(x);
            return true;
        }
        return false;
    }

    //Método que adiciona um Setor
    public boolean AdicionaArea(String x) {
        if (areas.contains(x)) {
            areas.add(x);
            return true;
        }
        return false;
    }

    //Método estatico que troca um conjunto de areas
    public static void SetAreas(Set<String> x) {
        areas = x.stream().filter(l -> areas.contains(l)).collect(Collectors.toSet());
    }

    //Adiciona um novo Produto ao Map   
    public boolean AdicionarArtigo(Produto x) {
        if (!this.artigos.contains(x)){
            this.artigos.add(x.clone());
            return true;
        }
        return false;
    }

    //Método que remove um Produto
    public boolean RemoveArtigo(Produto x) {
        if (this.artigos.contains(x)) {
            this.artigos.remove(x);
            return true;

        }
        return false;
    }

    //Método que remove uma Fatura de uma determinada Pessoa
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
        return super.toString() + "\nEmpresa\nSetores economico : " + this.setores.toString() + this.artigos.toString()
                + this.cliente.toString() + "\n";
    }

    //Método que devolve o total fatura pela empresa
    public double Total_faturado() {
        return this.emissoes_data.stream().mapToDouble(Fatura::getTotal).sum();
    }

    //Metodo que devolve as faturas que a empresa emitiu entre 2 datas
    public List<Fatura> Faturas_data(LocalDate begin, LocalDate end) { //ordenadas por data de emisssao
        return this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .map(Fatura::clone).collect(Collectors.toList());
    }

    //Metodo que devolve as faturas que a empresa emitiu
    public List<Fatura> Faturas_valor() { //ordenadas por valor
        return this.emissoes_valor.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> Faturas_valor(LocalDate begin, LocalDate end) { //ordenadas por valor
        return this.emissoes_valor.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .map(Fatura::clone).collect(Collectors.toList());
    }

    //Metodo clone
    public Empresa clone(){
        return new Empresa(this);
    }
    
    //Método iguais para as variaveis de instancia
    

    //Metodo equals
    public Empresa equals(Empresa x){
        if (x == this) return true;
        if (x.getClass() != this.getClass() || x == null ) return false;
        if (iguais(x)) return true;
    }

    //Metodo 6) Devolve as faturas emitidas pela Empresa , ordenadas 
    public List<Fatura> Faturas_data() { //ordenadas por data de emisssao
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    //Metodo 7) Lista das faturas por contribuinte num determinado intervalo de datas
    public List<Fatura> Faturas_tempo(LocalDate begin, LocalDate end, String nome_cliente) {
        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            return this.cliente.get(nome_cliente).stream()
                    .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone)
                    .collect(Collectors.toList());
        }
        return new ArrayList<Fatura>();

    }

    //Devolve todas as Faturas de um cliente ordenadas pela data de emissao
    public List<Fatura> Faturas_tempo(String nome_cliente) {

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            return this.cliente.get(nome_cliente).stream().map(Fatura::clone).collect(Collectors.toList());
        }
        return new ArrayList<Fatura>();

    }

    //Metodo 8) Lista das faturas por contribuinte ordenadas por valor decrescente da despesa
    public List<Fatura> Faturas_despesa(String nome_cliente) {

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.Faturas_tempo(nome_cliente);
            l.sort(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                    return (-1) * x.comparePreco(y);
                }
            });
            return l;
        }
        return new ArrayList<Fatura>();

    }

    //Devolve o total de faturas  entre uma respetiva data de um cliente 
    public List<Fatura> Faturas_despesa(LocalDate begin, LocalDate end, String nome_cliente) {

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.Faturas_tempo(begin, end, nome_cliente);
            l.sort(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    //estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente 
                    return (-1) * x.comparePreco(y);
                }
            });
            return l;
        }
        return new ArrayList<Fatura>();

    }

    //Metodo 9) Indicar o total faturado por uma Empresa num determinado periodo
    public double Total_faturado(LocalDate begin, LocalDate end) {
        return this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .mapToDouble(Fatura::getTotal).sum();
    }

}
