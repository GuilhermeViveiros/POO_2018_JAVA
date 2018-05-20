
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

/**
 * Esta classe implementa uma Fatura.
 * Uma Fatura é uma classe que faz parte de uma Entidade.
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

public class Fatura implements Serializable {
    
     /** O contacto da Fatura */
     private Contacto servidor;
     /** A area da Fatura */
     private Atividade area;
     /** O nif da Fatura */
     private long nifcliente;
     /** A descricao da Fatura */
     private String desc;
     /** A data da fatura */
     private LocalDate date;
     /** A Lista de Compras da Fatura*/
     private List<Produto> compras;
     /** O total faturado */
     private double total;
     /** O codigo da fatura */
     private Long code;
     /** Numero da fatura (contador) */
     static Long contagem;

    static {
        Fatura.contagem.valueOf(0);
    }

    /**
    * Construtor por omissão de Fatura.
    */
    public Fatura() {
        this.servidor = new Contacto();
        this.area = null;
        this.desc = "campo vazio";
        this.total = 0.0;
        this.nifcliente = -1;
        this.date = LocalDate.now();
        this.compras = new ArrayList<Produto>();
        this.history = new Stack<Atividade>();

        Fatura.contagem.valueOf(contagem.longValue() + 1);
        this.code = Fatura.contagem;
    }

    /**
     * Construtor parametrizado de Fatura.
     * @param Contacto
     * @param Area
     * @param Nif
     * @param Compras
     */
    public Fatura(Contacto x, Atividade area, long nifCliente, List<Produto> compras) {
        this.servidor = x.clone();
        this.area = area.clone();
        this.desc = "campo vazio";
        this.date = LocalDate.now();
        this.nifcliente = nifCliente;
        this.compras = compras.stream().map(Produto::clone).collect(Collectors.toList());
        this.total = compras.stream().mapToDouble(Produto::getPreco).sum();
        
        this.history = new Stack<Atividade>();
        this.history.push(area.clone());

        Fatura.contagem.valueOf(contagem.longValue() + 1);
        this.code = Fatura.contagem;
    }

    /**
     * Construtor de cópia de Contacto. Aceita como parâmetro outro Contacto e
     * utiliza os métodos de acesso aos valores das variáveis de instância. 
     * @param Contacto
     */
    public Fatura(Fatura x) {
        this.servidor = x.getServidor();
        this.total = x.getTotal();
        this.date = x.getDate();
        this.code = getCode();

        try {
            this.area = x.getArea();
        } catch (InvalidActivityException a) {
            this.area = null;
        }

        try {
            this.nifcliente = x.getCnif();
        } catch (InvalidFieldException a) {
            this.nifcliente = -1;
        }

        try {
            this.desc = x.getDescricao();
        } catch (InvalidFieldException a) {
            this.desc = "campo vazio";
        }

        try {
            this.compras = x.getCompras();
        } catch (EmptySetException b) {
            this.compras = new ArrayList<Produto>();
        }

        try{
            this.history = x.getHistory(); 
        }catch (EmptyStackException b){
            this.history = new Stack<Atividade>();
        }
    }

    /**
     * Obtem a comparacao entre duas faturas através da data
     */
    public int compareTo(Fatura b) {
        return (this.getDate().compareTo(b.getDate()));
    }

    /**
     * Obtem a comparacao entre duas faturas através do valor
     */
    public int comparePreco(Fatura b) {
        return (int) (b.getTotal() - this.total);
    }

    /**
     * Método que determina se 2 Faturas são iguais.
     * Esta função é deterministica, reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso as Faturas sejam iguais e falso caso
     *         contrário.
     * */
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Fatura inc = (Fatura) o;
        boolean r = (this.date == inc.getDate()) && this.servidor.equals(inc.getServidor());
        boolean l;

        try {
            l = this.area.equals(inc.getArea());
        } catch (InvalidActivityException a) {
            l = (this.area == null);
        }
        r = r && l;

        try {
            l = this.compras.containsAll(inc.getCompras());
        } catch (EmptySetException a) {
            l = (this.compras.size() == 0);
        }
        r = r && l;

        try {
            l = (this.nifcliente == inc.getCnif());
        } catch (InvalidFieldException b) {
            l = (this.nifcliente == -1);
        }

        return r && l;
    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Fatura clone() {
        return new Fatura(this);
    }

    /**
     * Obtem o code da Fatura
     */
    public Long getCode() {
        return this.code;
    }

    /**
     * Obtem a data da Fatura
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Obtem o total faturado da fatura
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Obtem o Contacto da Fatura
     */
    public Contacto getServidor() {
        return this.servidor.clone();
    }

    /**
     * Obtem a descricao da Fatura
     */
    public String getDescricao() throws InvalidFieldException {
        if (this.desc.equals("campo vazio"))
            throw new InvalidFieldException();
        else
            return this.desc;
    }

    /**
     * Obtem a area da Fatura
     */
    public Atividade getArea() throws InvalidActivityException {
        if (this.area == null)
            throw new InvalidActivityException(" Nenhuma Atividade foi Indicada");
        else
            return this.area.clone();
    }
    
    public Stack<Atividade> getHistory() throws EmptyStackException {
        if(this.history.size() == 0)
            throw new EmptyStackException();

        return this.history.stream().map(Atividade::clone).collect(Collectors.toCollection(Stack::new));
    }

    /**
     * Obtem a lista de compras da Fatura
     */
    public List<Produto> getCompras() throws EmptySetException {
        if (this.compras.size() == 0)
            throw new EmptySetException("Lista de compras nao pode ser vazia");
        else
            return this.compras.stream().map(Produto::clone).collect(Collectors.toList());
    }

    /**
     * Obtem o nif da Fatura
     */
    public long getCnif() throws InvalidFieldException {
        if (this.nifcliente == -1)
            throw new InvalidFieldException("Nif inapropriado");
        else
            return this.nifcliente;
    }

    /**
     * Redefine o nif da Fatura
     */
    public void setCnif(long nif) {
        this.nifcliente = nif;
    }

    /**
     * Redefine o data da fatura
     */
    public void setDate(LocalDate newd) {
        this.date = newd;
    }

     /**
     * Redefine o contacto da Fatura
     */
    public void setServidor(Contacto serv) {
        this.servidor = serv.clone();
    }

     /**
     * Redefine a descricao da Fatura
     */
    public void setDesricao(String com) {
        this.desc = com;
    }

     /**
     * Redefine a area da Fatura
     */
    public void setArea(Atividade area) {
        this.area = area.clone();
        this.history.push(area.clone());
    }

    /**
     * Adiciona um Produto na lista de compras da Fatura
     */
    public void addCompra(Produto x) {
        this.compras.add(x.clone());
        this.total += x.getPreco();
    }
}
