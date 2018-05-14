import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

import javax.activity.InvalidActivityException;
import Exception.*;

/**
 * Esta classe implementa uma Entidade. Uma Entidade será a unidade basica à
 * qual será aplicada tributação fiscal.
 * 
 * @author (Gonçalo Faria);
 * @version (v1);
 */

public class Entidade implements Serializable{
    // variáveis de instância
    private Contacto info;
    private TreeSet<Fatura> faturas_dt;
    private TreeSet<Fatura> faturas_val;

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
    }

    /**
     * Construtor parametrizado de Entidade. Aceita como parâmetros os valores para
     * cada variável de instância.
     */

    public Entidade(Contacto x) {
        this.info = x.clone();
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        });
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
    }

    /**
     * Construtor de cópia de Entidade. Aceita como parâmetro outra Entidade e
     * utiliza os métodos de acesso aos valores das variáveis de instância. A
     * password não é copiada. É iniciada por omissão.
     */

    public Entidade(Entidade inc) {
        this.info = new Contacto(inc.getContacto());
        try{
            this.faturas_val = inc.getfaturas_Valor();
        } catch(EmptySetException e){
            this.faturas_val= new TreeSet();
            this.faturas_dt = new TreeSet();
        }
        this.faturas_dt = new TreeSet( this.faturas_val );
        // a password está vazia.
    }

    /**
     * Métodos de instância
     */

    public TreeSet<Fatura> getfaturas_Crono() throws EmptySetException {
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas\n");
        else
            return this.faturas_dt.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
    }

    public TreeSet<Fatura> getfaturas_Valor() throws EmptySetException {
        if (this.faturas_val.size() == 0)
            throw new EmptySetException("Não contém faturas\n");
        else
            return this.faturas_val.stream().map(Fatura::clone).collect(Collectors.toCollection(TreeSet::new));
    }

    public List<Fatura> listafaturas_Crono() throws EmptySetException {
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas \n");
        else
            return this.faturas_dt.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Crono(LocalDate begin, LocalDate end)
            throws EmptySetException, InvalidIntervalException {

        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas \n");

        List<Fatura> l = this.faturas_dt.stream().map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());

        if (l.size() == 0)
            throw new InvalidIntervalException("O intervalo é invalido\n");
        else
            return l;

    }

    public List<Fatura> listafaturas_Valor() throws EmptySetException {

        if (this.faturas_val.size() == 0)
            throw new EmptySetException("Não contém faturas \n");
        else
            return this.faturas_val.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Valor(LocalDate begin, LocalDate end)
            throws EmptySetException, InvalidIntervalException {

        if (this.faturas_val.size() == 0)
            throw new EmptySetException(" Conjunto de faturas vazio \n");

        List<Fatura> l = this.faturas_val.stream().map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());
        if (l.size() == 0)
            throw new InvalidActivityException(" O intervalo é invalido\n");
        else
            return l;
    }

    public Contacto getContacto() {
        return this.info.clone();
    }

    public double getDespesa() throws EmptySetException {
        if (this.faturas_dt.size() == 0) {
            throw new EmptySetException(" Conjunto de faturas vazio \n");
        } else {
            return this.faturas_dt.stream().mapToDouble(Fatura::getTotal).sum();
        }
    }

    public double getDespesa(LocalDate begin, LocalDate end) throws EmptySetException {
        if (this.faturas_val.size() == 0) {
            throw new EmptySetException(" Conjunto de faturas vazio \n");
        } else {
            return this.faturas_dt.stream().filter(p -> (p.getDate().isAfter(begin)) && (p.getDate().isBefore(end)))
                    .mapToDouble(Fatura::getTotal).sum();
        }
    }

    public Map<String, Double> getDespesaArea() throws EmptySetException {
        HashMap<Atividade, Double> hist = new HashMap<>();
        Double count;
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Conjunto de faturas vazio\n");
        else {

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
    }

    public boolean removerFatura(Fatura bh) {

        return this.faturas_dt.remove(bh);
    }

    /**
     * Método que devolve a representação em String de toda a Entidade.
     * 
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
     * Método que determina se 2 Entidades são iguais. Apenas é necessário o mesmo
     * nif. Esta função é deterministica, reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso as Entidades sejam iguais e falso caso
     *         contrário.
     */
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Entidade inc = (Entidade) o;

        try {
            if (this.info.equals(inc.getContacto()) && this.faturas_val.containsAll(inc.getfaturas_Valor()))
                return true;
        } catch (EmptySetException e) {
            if (this.info.equals(inc.getContacto())) {
                return true;
            } else {
                return false;
            }

        }
        // não é neces

        return false;

    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
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
