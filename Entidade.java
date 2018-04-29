import java.util.TreeSet;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Esta classe implementa uma Entidade.
 * Uma Entidade será a unidade basica à qual será aplicada tributação fiscal.
 * 
 * @author (Gonçalo Faria); 
 * @version (v1);
 */

public class Entidade {
    // variáveis de instância
    private Contacto info;
    private TreeSet<Fatura> faturas_dt;
    private TreeSet<Fatura> faturas_val;
    private double despesa;

    /**
    * Construtor por omissão de Entidade.
    */

    public Entidade() {
        this.info = new Contacto();
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.despesa = 0.0;
    }

    /**
     * Construtor parametrizado de Entidade.
     * Aceita como parâmetros os valores para cada variável de instância.
     */

    public Entidade(Contacto x) {
        this.info = x.clone();
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        this.despesa = 0.0;
    }

    public Entidade(Contacto x, TreeSet<Fatura> fat) {

        this.info = x.clone();
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
        Fatura j;
        for (Fatura l : fat) {
            j = l.clone();
            this.faturas_dt.add(j);
            this.faturas_val.add(j);
        }
        this.despesa = fat.stream().mapToDouble(Fatura::getTotal).sum();
    }

    /**
     * Construtor de cópia de Entidade.
     * Aceita como parâmetro outra Entidade e utiliza os métodos
     * de acesso aos valores das variáveis de instância.
     * A password não é copiada. É iniciada por omissão.
     */

    public Entidade(Entidade inc) {
        this.info = new Contacto(inc.getContacto());
        this.faturas_val = inc.getfaturas_Valor();
        this.faturas_dt = this.faturas_val.stream().collect(Collectors.toCollection(TreeSet::new));
        this.despesa = inc.getDespesa();

        // a password está vazia.
    }

    /**
     * Métodos de instância
     */

    public TreeSet<Fatura> getfaturas_Crono() {
        return this.faturas_dt.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Fatura> getfaturas_Valor() {
        return this.faturas_val.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
    }

    public List<Fatura> listafaturas_Crono() {
        return this.faturas_dt.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Crono(LocalDate begin, LocalDate end) {
        return this.faturas_dt.stream().map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Valor() {
        return this.faturas_val.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Valor(LocalDate begin, LocalDate end) {
        return this.faturas_val.stream().map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());
    }

    public Contacto getContacto() {
        return this.info.clone();
    }

    public double getDespesa() {
        return this.despesa;
    }

    public double getDespesa(LocalDate begin, LocalDate end) {
        return this.faturas_dt.stream().filter(p -> (p.getDate().isAfter(begin)) && (p.getDate().isBefore(end)))
                .mapToDouble(Fatura::getTotal).sum();
    }

    public Map<String, Double> getDespesaArea() {
        HashMap<String, Double> hist = new HashMap<>();
        Double count;

        for (Fatura l : this.faturas_dt) {
            if (hist.containsKey(l.getArea())) {
                count = hist.get(l.getArea());

            } else {
                count = new Double(0);
            }
            hist.put(l.getArea(), new Double(count.doubleValue() + l.getTotal()));

        }
        return hist;
    }

    public boolean removerFatura(Fatura bh) {

        return this.faturas_dt.remove(bh);
    }

    /**
     * Método que devolve a representação em String de toda a Entidade.
     * @return String com tudas as variáveis de instâncias(exceto password).
     */
    public String toString() {
        String text, space;

        text = this.info.toString() + " " + faturas_dt.toString();

        return text;
    }

    public void addFatura(Fatura x) {
        this.faturas_dt.add(x.clone());
        this.despesa += x.getTotal();
    }

    public void setContacto(Contacto x) {
        this.info = x.clone();
    }

    /**
     * Método que determina se 2 Entidades são iguais.
     * Apenas é necessário o mesmo nif.
     * Esta função é deterministica, reflexiva, transitiva e simétrica.
     * @return booleano que é verdadeiro caso as Entidades sejam iguais e falso caso contrário. 
     */
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Entidade inc = (Entidade) o;

        if (this.info.equals(inc.getContacto()))
            return true;

        // não é neces 

        return false;

    }

    /**
     * Método que faz o clone do objeto receptor da mensagem.
     * Para tal invoca o construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Entidade clone() {

        return (new Entidade(this));
    }

    public int hashCode() {
        long v = this.info.getNif();
        return (int) (v ^ (v >>> 32));
    }
}
