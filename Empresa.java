
/**
 *  Classe empresa na qual esta submetida a uma entidade 
 */
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
import java.lang.String;//string
import java.io.Serializable;

public class Empresa extends Entidade implements Serializable{
    // Tree associado a cada Fatura (ordenada por data)
    private TreeSet<Fatura> emissoes_data;
    // Tree associado a cada Fatura (ordenada por valor)
    private TreeSet<Fatura> emissoes_valor;
    // Map associados a uma Pessoa e todas as suas Faturas
    private Map<String, Set<Fatura>> cliente;
    // Set com todos os produtos de uma empresa
    private Set<Produto> artigos;
    // Conjunto de areas que uma empresa tem!
    private Set<Atividade> areas;

    /**
     * Construtor por omissão de Empresa.
     */
    public Empresa(){
        super();
        // quando nao declaramos nenhuma função para o comparator ele assume a dele ou
        // usa a nossa funcao se tiver o nome de ->"compare"
        // neste caso ja temos , em que a comparaçao é feita a partir da data
        this.emissoes_data = new TreeSet<Fatura>();
        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.areas = new HashSet<Atividade>();
    }

    /**
     * Construtor parametrizado da Empresa. Aceita como parâmetros os valores para
     * cada variável de instância da sua Entidade.
     */
    public Empresa(long nif, String nome, String mail, String morada, String telefone, Set<Atividade> areas){
        super(new Contacto(nif, nome, mail, morada, telefone));
        this.emissoes_data = new TreeSet<Fatura>();

        this.emissoes_valor = new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.areas = areas.stream().map(Atividade::clone).collect(Collectors.toSet());
    }

    /**
     * Construtor de cópia da Empresa. Aceita como parâmetro outra Empresa e utiliza
     * os métodos de acesso aos valores das variáveis de instância. As faturas dos
     * clientes ordenadas por data e valor não são copiadas.
     */
    public Empresa(Empresa x) {
        super(x);
        this.cliente = this.cliente.entrySet().stream().collect(Collectors.toMap(l -> l.getKey(),
        l -> l.getValue().stream().map(Fatura::clone).collect(Collectors.toSet())));

        this.makeClienteData();
        this.makeClienteValue();

        this.artigos = this.artigos.stream().map(Produto::clone).collect(Collectors.toSet());
        this.areas =  this.areas.stream().map(Atividade::clone).collect(Collectors.toSet());
    }

    // Privado -> apenas para os construtores pois nao queres clone da mesma
    // informacao entre os mesmos
    // atraves do Map criamos um set de Faturas iguais com ordenacao baseada na data
    private void makeClienteData() {
        
        this.emissoes_data = new TreeSet<>();
        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
                this.emissoes_data.add(k);
            }
        }
    }

    // atraves do Map criamos um set de Faturas iguais com ordenacao baseada no
    // valor
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
     * Métodos de
     * instância-------------------------------------------------------------------------------------------------------
     */
    // Getters!
    /*
    new TreeSet<>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        }); */
    public Set<Produto> getArtigos() throws EmptySetException{
        if (this.artigos.size()==0)throw new EmptySetException("Set de artigos ainda nao foi preenchido");
        return this.artigos.stream().map(Produto::clone).collect(Collectors.toSet());
    }

    public Set<Fatura> getEmissoesD() throws EmptySetException{
        if (this.artigos.size()==0) throw new EmptySetException("Set de Faturas ainda nao preenchido");
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toSet());
    }

    public Set<Fatura> getEmissoesV() throws EmptySetException {
        if (this.emissoes_valor.size() ==0) throw new EmptySetException("Set de Faturas ainda nao preenchido");
        return this.emissoes_valor.stream().map(Fatura::clone).collect(Collectors.toSet());
    }

    public Map<String, Set<Fatura>> getClientes() throws EmptyMapException {
        if (this.cliente.size()==0) throw new EmptyMapException("Map com os respetivos clientes e as suas respetivas faturas ainda nao está preenchido");
        return this.cliente.entrySet().stream().collect(Collectors.toMap(l -> l.getKey(),
                l -> l.getValue().stream().map(Fatura::clone).collect(Collectors.toSet())));
    }

    public Set<Atividade> getAreas() throws EmptySetException{
        if (this.areas.size()==0) throw new EmptySetException("Set de atividades ainda nao preenchido");
        return this.areas.stream().map(Atividade::clone).collect(Collectors.toSet());
    }
 
    // Método que adiciona e devolve a fatura emitida pela Empresa de um determinado
    // Setor
    public Fatura Fatura_emi(Entidade x, List<Produto> compras) throws EmptySetException {

        Fatura f;
        if(compras.size() == 0) 
        
        if (this.artigos.containsAll(compras)) { // estou a verificar se a lista de Produtos corresponde com a lista de
                                                 // produtos da minha empresa
  
            /* Inicia o histograma */
            if (getAreas().size() == 0) throw new EmptySetException("Setor de areas inválido");
            
            Map<Atividade, Integer> s = new HashMap<Atividade, Integer>( compras.stream()
                .collect(Collectors.toMap( p -> p.getArea(), p-> new Integer(0)) ));
            /* cria histograma */
            
            /* Preenche o histograma */
            for (Produto pd : compras)
                s.put(pd.getArea(), new Integer(s.get(pd.getArea()).intValue() + 1));
            
            int max=0, current;
            Atividade maxim=null;
            
            for( Map.Entry<Atividade, Integer> k : s.entrySet() ){
                current = k.getValue().intValue();
                if( current >= max){
                    max = current;
                    maxim = k.getKey(); 
                }
            }

            f = new Fatura(this.getContacto(), maxim , x.getContacto().getNif(),
                compras.stream().map(Produto::clone).collect(Collectors.toList()));
            
            this.emissoes_data.add(f);
            this.emissoes_valor.add(f);
            
            if (!this.cliente.containsKey(x.getContacto().getNome())) {
                TreeSet<Fatura> l = new TreeSet<>(new Comparator<Fatura>() {
                    public int compare(Fatura x, Fatura y) {
                        // estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente
                        return (-1) * x.comparePreco(y);
                    }
                });

                l.add(f);
                this.cliente.put(x.getContacto().getNome(), l);

            } else {
                this.cliente.get(x.getContacto().getNome()).add(f);
            }
            // private Map<String,Set<Fatura>> cliente;
            return f.clone();
        }
        return null;

    }

    // Método que remove um Setor
    public boolean RemoverArea(Atividade x) throws EmptySetException {
        if(this.areas.size() == 0) throw new EmptySetException("Incorreto setor de areas");
        if (this.areas.contains(x)) {
            this.areas.remove(x);
            return true;
        }
        return false;
    }

    // Método que adiciona um Setor
    public boolean AdicionaArea(Atividade x) {
        if (this.areas.contains(x)) {
            this.areas.add(x);
            return true;
        }
        return false;
    }

    // Método que troca um conjunto de setores
    public void SetAreas(Set<Atividade> x) {
        for(Atividade act : x)
            this.areas.add(act);
        
    }

    // Adiciona um novo Produto ao Map
    public boolean AdicionarArtigo(Produto x) {
        if (!this.artigos.contains(x)) {
            this.artigos.add(x.clone());
            return true;
        }
        return false;
    }

    // Método que remove um Produto
    public boolean RemoveArtigo(Produto x) throws EmptySetException{
        if(this.artigos.size() == 0) throw new EmptySetException("Incorreto setor de areas");
        
        if (this.artigos.contains(x)) {
            this.artigos.remove(x);
            return true;

        }
        return false;
    }

    // Método que remove uma Fatura de uma determinada Pessoa
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
    
    // Metodo toString
    public String toString() {
        return super.toString() + "\nEmpresa\nSetores economico : " + this.areas.toString() + this.artigos.toString()
                + this.cliente.toString() + "\n";
    }

    // Método que devolve o total fatura pela empresa
    public double Total_faturado() {
        return this.emissoes_data.stream().mapToDouble(Fatura::getTotal).sum();
    }

    // Metodo que devolve as faturas que a empresa emitiu entre 2 datas
    public List<Fatura> Faturas_data(LocalDate begin, LocalDate end) throws InvalidIntervalException , EmptySetException { // ordenadas por data de emisssao
        if(this.emissoes_data.size() == 0) throw new EmptySetException("Emissoes de faturas ordenadas por data inválidas");
        List<Fatura> x = this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .       map(Fatura::clone).collect(Collectors.toList());
        if (x.size() != 0) return x;
        else throw new InvalidIntervalException("Intervalo inválido");
    }

    // Metodo que devolve as faturas que a empresa emitiu
    public List<Fatura> Faturas_valor() throws EmptySetException{// ordenadas por valor
        if(this.emissoes_valor.size() == 0) throw new EmptySetException("Emissoes de faturas ordenadas por valor inválidas");
        return this.emissoes_valor.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> Faturas_valor(LocalDate begin, LocalDate end) throws InvaliIntervalException , EmptySetException { // ordenadas por valor
        if (this.emissoes_valor.size() == 0) throw new EmptySetException("Emissoes de faturas por valor inválidas");
        List<Fatura> x = this.emissoes_valor.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                        .map(Fatura::clone).collect(Collectors.toList());
        if (x.size() != 0) return x;
        else throw InvalidIntervalarException("Intervalo inválido");
    }

    // Metodo clone
    public Empresa clone() {
        return new Empresa(this);
    }

    // Metodo equals
    // Metodo equals
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y.getClass() != this.getClass() || y == null)
            return false;
        Empresa x = (Empresa) y;
        try{
        if (super.equals(x) && this.emissoes_data.equals(x.getEmissoesD()) && this.emissoes_valor.equals(x.getEmissoesV())
            && this.artigos.equals( x.getArtigos()) &&  this.areas.equals(x.getAreas())) return true; 
        } 
            catch(EmptySetException e){
                return this.emissoes_data.equals(x.getEmissoesD()) && this.emissoes_valor.equals(x.getEmissoesV())
                        && this.artigos.equals(x.getArtigos()) && this.areas.equals(x.getAreas());
        }
        
       
    }

    // Metodo 6) Devolve as faturas emitidas pela Empresa , ordenadas
    public List<Fatura> Faturas_data() throws EmptySetException { // ordenadas por data de emisssao
        if(this.emissoes_data.size() == 0) throw new EmptySetException("Emissoes de faturas ordendas por data invalidas");  
        return this.emissoes_data.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    // Metodo 7) Lista das faturas por contribuinte num determinado intervalo de
    // datas
    public List<Fatura> Faturas_tempo(LocalDate begin, LocalDate end, String nome_cliente) throws InvalidIntervalException , EmptyMapException {
        
        if (this.cliente.size() == 0) throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");
        
        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> x = this.cliente.get(nome_cliente).stream()
                    .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone)
                    .collect(Collectors.toList());
            if(x.size()!=0) return x;
            else throw new InvalidIntervalException("Intervalo inválido");
        }
        return new ArrayList<Fatura>();

    }

    // Devolve todas as Faturas de um cliente ordenadas pela data de emissao
    public List<Fatura> Faturas_tempo(String nome_cliente) throws EmptyMapException {
        if(this.cliente.size() == 0) throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");
        
        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            return this.cliente.get(nome_cliente).stream().map(Fatura::clone).collect(Collectors.toList());
        }
        return new ArrayList<Fatura>();

    }

    // Metodo 8) Lista das faturas por contribuinte ordenadas por valor decrescente
    // da despesa
    public List<Fatura> Faturas_despesa(String nome_cliente) throws EmptyMapException {
        if(this.cliente.size() == 0) throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");
        
        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.Faturas_tempo(nome_cliente);
            l.sort(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    // estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente
                    return (-1) * x.comparePreco(y);
                }
            });
            return l;
        }
        return new ArrayList<Fatura>();

    }

    // Devolve o total de faturas entre uma respetiva data de um cliente
    public List<Fatura> Faturas_despesa(LocalDate begin, LocalDate end, String nome_cliente) throws InvalidIntervalException , EmptyMapException {
        if(this.cliente.size() == 0) throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");
        
        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.Faturas_tempo(begin, end, nome_cliente);
            l.sort(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    // estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente
                    return (-1) * x.comparePreco(y);
                }
            });
            if(l.size()!=0) return x;
            else throw new InvalidIntervalException("Intervalo inválido");
        }
        return new ArrayList<Fatura>();

    }

    // Metodo 9) Indicar o total faturado por uma Empresa num determinado periodo
    public double Total_faturado(LocalDate begin, LocalDate end) throws EmptySetException{
        if(this.emissoes_data.size() == 0) throw new EmptySetException("Emissoes de faturas ordendas por data invalidas"); 
        return this.emissoes_data.stream().filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                    .mapToDouble(Fatura::getTotal).sum();
    }

}
