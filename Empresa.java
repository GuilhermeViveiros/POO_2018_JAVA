import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
import java.lang.String;//string
import java.io.Serializable;

/**
 * Esta classe implementa uma Empresa. Uma Empresa é uma sub-classe da Entidade
 * 
 * @author (Gonçalo Faria);
 * @version (v1);
 * 
 * @author (Guilherme Viveiros);
 * @version (v1);
 * 
 * @author (Angelo Andre);
 * @version (v1);
 */

public class Empresa extends Entidade implements Serializable {
    /** Conjunto de faturas ordenadas por ordem cronologica da Empresa */
    private TreeSet<Fatura> emissoes_data;
    /** Conjunto de faturas ordenadas por valor referidos nas faturas da Empresa */
    /** Conjunto que associa um Utilizador a todas as suas faturas */
    private  Map<String, Set<Fatura>> cliente;
    /** Os produtos da Empresa */
    private Set<Produto> artigos;
    /** As areas da Empresa */
    private Set<Atividade> areas;

    /**
     * Construtor por omissão de Empresa.
     */
    public Empresa() {
        super();
        this.emissoes_data = new TreeSet<Fatura>();
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.areas = new HashSet<Atividade>();

    }

    /**
     * Construtor parametrizado da Empresa.
     * 
     * @param Contacto
     * @param Password
     * @param Areas
     */
    public Empresa(Contacto ct, String password, Set<Atividade> areas) {
        super(ct, password);
        this.emissoes_data = new TreeSet<Fatura>();
        this.cliente = new HashMap<String, Set<Fatura>>();
        this.artigos = new HashSet<Produto>();
        this.areas = areas.stream().map(Atividade::clone).collect(Collectors.toSet());

    }

    /**
     * Construtor de cópia da Empresa. Aceita como parâmetro outra Empresa e utiliza
     * os métodos de acesso aos valores das variáveis de instância. As faturas dos
     * clientes ordenadas por data e valor não são copiadas.
     * 
     * @param Empresa
     */
    public Empresa(Empresa x) {
        super(x);
        try {
            this.cliente = x.getClientes();
            this.makeClienteData();
        } catch (EmptyMapException a) {
            this.cliente = new HashMap<String, Set<Fatura>>();
            this.emissoes_data = new TreeSet<Fatura>();

        }

        try {
            this.artigos = x.getArtigos();
        } catch (EmptySetException a) {
            this.artigos = new HashSet<>();
        }

        try {
            this.areas = x.getAreas();
        } catch (EmptySetException a) {
            this.areas = new HashSet<>();
        }

    }

    /**
     * Privado -> apenas para os construtores pois nao queremos estar a repetir
     * clones Atraves do Map clientes criamos um set de Faturas iguais com ordenacao
     * baseada na data
     */
    private void makeClienteData() {

        this.emissoes_data = new TreeSet<>();
        for (Set<Fatura> l : this.cliente.values()) {

            for (Fatura k : l) {
                this.emissoes_data.add(k);
            }
        }
    }


    /**
     * Privado -> apenas para os construtores pois nao queremos estar a repetir
     * clones Atraves do Map clientes criamos um set de Faturas iguais com ordenacao
     * baseada na data
     */

    public double calculoDeducao(LocalDate begin, LocalDate end) {
        return this.areas.stream().mapToDouble(l -> l.regraCalculo(this, begin, end)).sum();
    }

    /**
     * Métodos de instância
     */

    public void updateFatura(Fatura old ,Fatura newer) throws InvalidFieldException{
        if( this.emissoes_data.contains(old) ){
            
            this.emissoes_data.remove(old);
            this.cliente.get(old.getServidor().getNome()).remove(old);
            
            Fatura acnewer = newer.clone();
            this.emissoes_data.add(acnewer);
            this.cliente.get(old.getServidor().getNome()).add(acnewer);
        }
    }
    /**
     * Obtem o conjunto de artigos da Empresa
     */
    public Set<Produto> getArtigos() throws EmptySetException {
        if (this.artigos.size() == 0)
            throw new EmptySetException("Set de artigos ainda nao foi preenchido");
        return this.artigos.stream().map(Produto::clone).collect(Collectors.toSet());
    }

    /**
     * Obtem conjunto de Faturas ordenadas por ordem cronológica da Empresa
     */
    public Set<Fatura> getEmissoesD() throws EmptySetException {
        if (this.artigos.size() == 0)
            throw new EmptySetException("Set de Faturas ainda nao preenchido");
        return this.emissoes_data.stream().filter(l -> ! l.isPendente() ).map(Fatura::clone).collect(Collectors.toSet());
    }

    /**
     * Obtem o conjunto de Faturas ordenadas por valor referidos nas Faturas da
     * Empresa
     */
    public Set<Fatura> getEmissoesV() throws EmptySetException {
        if (this.emissoes_data.size() == 0)
            throw new EmptySetException("Set de Faturas ainda nao preenchido");

        TreeSet<Fatura> emissoes_valor = new TreeSet<Fatura>(this.getPriority());
        for(Fatura x : this.emissoes_data)
            emissoes_valor.add(x);

        return emissoes_valor.stream().filter(f -> ! f.isPendente() ).map(Fatura::clone).collect(Collectors.toSet());
    }

    /**
     * Obtem o conjuntos de clientes tais como as suas faturas referentes ha Empresa
     */
    public Map<String, Set<Fatura>> getClientes() throws EmptyMapException {
        if (this.cliente.size() == 0)
            throw new EmptyMapException(
                    "Map com os respetivos clientes e as suas respetivas faturas ainda nao está preenchido");
        return this.cliente.entrySet().stream().collect(Collectors.toMap(l -> l.getKey(),
                l -> l.getValue().stream().filter(f -> ! f.isPendente() ).map(Fatura::clone).collect(Collectors.toSet())));
    }

    /**
     * Obtem o conjunto de areas da Empresa
     */
    public Set<Atividade> getAreas() throws EmptySetException {
        if (this.areas.size() == 0)
            throw new EmptySetException("Set de atividades ainda nao preenchido");
        return this.areas.stream().map(Atividade::clone).collect(Collectors.toSet());
    }

    /**
     * Método que adiciona uma determinado fatura no sistema Devolve a fatura
     * emitida pela Empresa de um determinado utilizador que comprou algo na Empresa
     * 
     * @param Entidade
     * @param Compras
     */
    public Fatura faturaEmi(Entidade x, List<Produto> compras)
            throws EmptySetException, EmptyListException, InvalidFieldException {

        Fatura f;
        if (compras.size() == 0)
            throw new EmptyListException(" Não indicou nenhum produto na lista de compras ");

        if (this.areas.size() == 0)
            throw new EmptySetException(" A empresa não tem nenhuma Atividade económica ");

        if (!this.artigos.containsAll(compras))
            throw new InvalidFieldException(" Pelo menos uma das compras indicadas nao sao vendidadas na empresa");

        if (this.areas.size() == 1) {
            f = new Fatura(this.getContacto(), (Atividade) this.areas.toArray()[0], x.getContacto().getNif(), compras);

        } else {
            f = new Fatura(this.getContacto(), x.getContacto().getNif(), compras);
        }

        this.emissoes_data.add(f);

        if (!this.cliente.containsKey(x.getContacto().getNome())) {
            TreeSet<Fatura> l = new TreeSet<>(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    return (-1) * x.comparePreco(y);
                }
            });

            l.add(f);
            this.cliente.put(x.getContacto().getNome(), l);

        } else {
            this.cliente.get(x.getContacto().getNome()).add(f);
        }
        return f.clone();
    }

    /**
     * Obtem o numero de Faturas emitidas da Empresa
     */
    public int numeroDeFaturasEmitidas() {
        return emissoes_data.size();
    }

    /**
     * Remove uma determinada area da Empresa
     */
    public boolean removerArea(Atividade x) throws EmptySetException {
        if (this.areas.size() == 0)
            throw new EmptySetException("Incorreto setor de areas");
        if (this.areas.contains(x)) {
            this.areas.remove(x);
            return true;
        }
        return false;
    }

    /**
     * Adiciona uma area na Empresa
     */
    public boolean adicionaArea(Atividade x) {
        if (!this.areas.contains(x)) {
            this.areas.add(x);
            return true;
        }
        return false;
    }

    /**
     * Redefine o conjunto de areas da Empresa para outro conjunto de areas
     */
    public void setAreas(Set<Atividade> x) {
        for (Atividade act : x)
            this.areas.add(act);

    }

    /**
     * Adiciona um novo artigo na Empresa
     */
    public boolean adicionarArtigo(Produto x) {
        if (!this.artigos.contains(x)) {
            this.artigos.add(x.clone());
            return true;
        }
        return false;
    }

    /**
     * Remove um determinado artigo da Empresa
     */
    public boolean removeArtigo(Produto x) throws EmptySetException {
        if (this.artigos.size() == 0)
            throw new EmptySetException("Nao existe nenhum produto disponivel");

        if (this.artigos.contains(x)) {
            this.artigos.remove(x);
            return true;
        }

        return false;
    }

    /**
     * Remove uma fatura de um determinado Utilizador
     * 
     * @param Entidade
     * @param Fatura
     */
    public boolean removeRegisto(Entidade ent, Fatura x) throws InvalidFieldException, EmptyMapException {
        if (this.cliente.size() == 0)
            throw new EmptyMapException("Nao existem clientes/faturas na base de dados");
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

    /**
     * Método que devolve a representação em String de toda a Empresa.
     */
    public String toString() {
        return super.toString() + "\nEmpresa\nSetores economico : " + this.areas.toString() + this.artigos.toString()
                + this.cliente.toString() + "\n";
    }

    /**
     * Obtem o total faturado pela Empresa
     */
    public double totalFaturado() {
        if (this.emissoes_data.size() == 0)
            return 0;

        return this.emissoes_data.stream().filter(l -> ! l.isPendente() )
                .mapToDouble(Fatura::getTotal).sum();
    
    }

    /**
     * Obtem as faturas que a Empresa emitiu ordenadas por ordem cronológica entre 2
     * datas
     */
    public List<Fatura> faturasData(LocalDate begin, LocalDate end) throws InvalidIntervalException, EmptySetException {
        if (this.emissoes_data.size() == 0)
            throw new EmptySetException("Emissoes de faturas ordenadas por data inválidas");
        List<Fatura> x = this.emissoes_data.stream()
                .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).filter(l -> ! l.isPendente() ).map(Fatura::clone)
                .collect(Collectors.toList());
        if (x.size() != 0)
            return x;
        else
            throw new InvalidIntervalException("Intervalo inválido");
    }

    /**
     * Obtem todas as faturas que a Empresa emitiu ordenadas por valor
     */
    public List<Fatura> faturasValor() throws EmptySetException {// ordenadas por valor
        if (this.emissoes_data.size() == 0)
            throw new EmptySetException("Emissoes de faturas ordenadas por valor inválidas");

        
        TreeSet<Fatura> emissoes_valor = new TreeSet<Fatura>(this.getPriority());
        for(Fatura x : this.emissoes_data)
            emissoes_valor.add(x);

        return emissoes_valor.stream().filter(l -> ! l.isPendente() ).map(Fatura::clone).collect(Collectors.toList());
    }

    /**
     * Obtem a lista de faturas que a empresa emitiu ordenadas por valor entre 2
     * datas
     */
    public List<Fatura> faturasValor(LocalDate begin, LocalDate end)
            throws InvalidIntervalException, EmptySetException { // ordenadas por valor
        if (this.emissoes_data.size() == 0)
            throw new EmptySetException("Emissoes de faturas por valor inválidas");

        TreeSet<Fatura> emissoes_valor = new TreeSet<Fatura>(this.getPriority());
            for(Fatura x : this.emissoes_data)
                emissoes_valor.add(x);

        List<Fatura> x = emissoes_valor.stream().filter(l -> ! l.isPendente() )
                .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone)
                .collect(Collectors.toList());
        if (x.size() != 0)
            return x;
        else
            throw new InvalidIntervalException("Intervalo inválido");
    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Empresa clone() {
        return new Empresa(this);
    }

    /**
     * Método que determina se 2 Empresas são iguais. Esta função é deterministica,
     * reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso as Empresas sejam iguais e falso caso
     *         contrário.
     */
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y.getClass() != this.getClass() || y == null)
            return false;
        Empresa x = (Empresa) y;

        boolean r = super.equals(x);
        boolean l;

        try {
            l = this.emissoes_data.containsAll(x.getEmissoesD());
        } catch (EmptySetException e) {
            l = (this.emissoes_data.size() == 0);
        }
        r = r && l;

        try {
            l = this.artigos.containsAll(x.getArtigos());
        } catch (EmptySetException e) {
            l = (this.artigos.size() == 0);
        }
        r = r && l;

        try {
            l = this.areas.containsAll(x.getAreas());
        } catch (EmptySetException e) {
            l = (this.areas.size() == 0);
        }
        return r && l;
    }

    /**
     * Obtem a lista de faturas emitidas pela Empresa , ordenadas por ordem
     * cronologica
     */
    public List<Fatura> faturasData() throws EmptySetException { // ordenadas por data de emisssao
        if (this.emissoes_data.size() == 0)
            throw new EmptySetException("Emissoes de faturas ordendas por data invalidas");
        return this.emissoes_data.stream().filter(l -> ! l.isPendente() ).map(Fatura::clone).collect(Collectors.toList());
    }

    /**
     * Obtem a lista das faturas por ordem cronologica de um dado Utilizador
     * 
     * @param Datas
     * @param String
     */
    public List<Fatura> faturasTempo(LocalDate begin, LocalDate end, String nome_cliente)
            throws InvalidIntervalException, EmptyMapException {

        if (this.cliente.size() == 0)
            throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> x = this.cliente.get(nome_cliente).stream().filter(l -> ! l.isPendente() )
                    .filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end)).map(Fatura::clone)
                    .collect(Collectors.toList());
            if (x.size() != 0)
                return x;
            else
                throw new InvalidIntervalException("Intervalo inválido");
        }
        return new ArrayList<Fatura>();

    }

    /**
     * Obtem todas as Faturas de um cliente ordenadas por ordem cronologica
     */
    public List<Fatura> faturasTempo(String nome_cliente) throws EmptyMapException {
        if (this.cliente.size() == 0)
            throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            return this.cliente.get(nome_cliente).stream().filter(l -> ! l.isPendente() ).map(Fatura::clone).collect(Collectors.toList());
        }
        return new ArrayList<Fatura>();

    }

    /**
     * Obtem a lista das faturas ordenadas em ordem decrescente da despesa
     */
    public List<Fatura> faturasDespesa(String nome_cliente) throws EmptyMapException {
        if (this.cliente.size() == 0)
            throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.faturasTempo(nome_cliente);
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

    public List<Fatura> listarPendentes(String nome) throws EmptySetException{
        if( !this.contemCliente(nome) )
            throw new EmptySetException( nome + " utilizador não existente");
        return cliente.get(nome).stream().filter(Fatura::isPendente).map(Fatura::clone).collect(Collectors.toList());
    }

    public boolean contemCliente(String nome){
        return cliente.containsKey(nome);
    }
    /**
     * Obtem a Lista de faturas entre uma respetiva data de um cliente em ordem da
     * despesa
     */
    public List<Fatura> faturasDespesa(LocalDate begin, LocalDate end, String nome_cliente)
            throws InvalidIntervalException, EmptyMapException {
        if (this.cliente.size() == 0)
            throw new EmptyMapException("Nao existem clientes nem as respetivas faturas");

        if (this.cliente.containsKey(nome_cliente)) {
            // existe;
            List<Fatura> l = this.faturasTempo(begin, end, nome_cliente);
            l.sort(new Comparator<Fatura>() {
                public int compare(Fatura x, Fatura y) {
                    // estamos a fazer (-1) pois o Metodo 8 pede as despesas por ordem decresecente
                    return (-1) * x.comparePreco(y);
                }
            });
            if (l.size() != 0)
                return l;
            else
                throw new InvalidIntervalException("Intervalo inválido");
        }
        return new ArrayList<Fatura>();

    }

    /**
     * Obtem o total faturado por uma Empresa num determinado periodo
     */
    public double totalFaturado(LocalDate begin, LocalDate end) {
        if (this.emissoes_data.size() == 0)
            return 0;

        return this.emissoes_data.stream().filter(l -> ! l.isPendente() ).filter(h -> h.getDate().isAfter(begin) && h.getDate().isBefore(end))
                .mapToDouble(Fatura::getTotal).sum();
    }

}
