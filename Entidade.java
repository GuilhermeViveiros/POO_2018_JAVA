import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

/**
 * Esta classe implementa uma Entidade. Uma Entidade será a unidade basica à
 * qual será aplicada tributação fiscal.
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

public abstract class Entidade implements Serializable {
    /** O contacto da entidade */
    private Contacto info;
    /** As faturas da entidade ordendas por data */
    private TreeSet<Fatura> faturas_dt;
    /** As faturas da entidade ordenadas por valor */
    private TreeSet<Fatura> faturas_val;
    /** A password da entidade */
    private String password;

    transient private Comparator<Fatura> cmpval;

    public abstract double calculoDeducao(LocalDate begin, LocalDate end);

    public abstract Entidade clone();

    /**
     * Construtor por omissão de Entidade.
     */
    public Entidade() {
        this.info = new Contacto();
        this.faturas_dt = new TreeSet<Fatura>();
        this.cmpval = new Comparator<Fatura>(){
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        };
        this.faturas_val = new TreeSet<Fatura>(this.cmpval);
        this.password = "invalido";
    }

    /**
     * Construtor parametrizado de Entidade. Aceita como parâmetros os valores para
     * cada variável de instância.
     * 
     * @param Contacto
     * @param Password
     */
    public Entidade(Contacto x, String pw) {
        this.info = x.clone();
        this.cmpval = new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        };
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(this.cmpval);
        this.password = pw;
    }

    /**
     * Construtor parametrizado de Entidade.
     * 
     * @param Contacto
     * @param Password
     * @param Faturas
     */
    public Entidade(Contacto x, String pw, Set<Fatura> fat) {

        this.info = x.clone();
        this.cmpval = new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        };
        this.faturas_dt = new TreeSet<Fatura>();
        this.faturas_val = new TreeSet<Fatura>(this.cmpval);
        this.password = pw;

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
     * 
     * @param Entidade
     */
    public Entidade(Entidade inc) {
        this.info = new Contacto(inc.getContacto());
        this.cmpval = new Comparator<Fatura>() {
            public int compare(Fatura x, Fatura y) {
                return x.comparePreco(y);
            }
        };
        try {
            this.faturas_val = inc.getfaturas_Valor();
        } catch (EmptySetException e) {
            this.faturas_val = new TreeSet(this.cmpval);
        }

        try {
            this.password = inc.getPassword();
        } catch (InvalidFieldException a) {
            this.password = "invalido";
        }

        this.faturas_dt = new TreeSet(this.faturas_val);
        // a password está vazia.
    }

    /**
     * Métodos de instância
     */

    /**
     * Obter um set de faturas por ordem cronológica
     */
    public TreeSet<Fatura> getfaturas_Crono() throws EmptySetException {
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas\n");
        else
            return this.faturas_dt.stream().filter(l -> !l.isPendente()).map(Fatura::clone)
                    .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Obter um set de faturas ordenadas por valor
     */
    public TreeSet<Fatura> getfaturas_Valor() throws EmptySetException {
        if (this.faturas_val.size() == 0)
            throw new EmptySetException("Não contém faturas\n");
        else
            return this.faturas_val.stream().filter(l -> !l.isPendente()).map(Fatura::clone)
                    .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Obter uma lista de faturas ordenadas por ordem cronológica
     */
    public List<Fatura> listafaturas_Crono() throws EmptySetException {
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas \n");
        else
            return this.faturas_dt.stream().filter(l -> !l.isPendente()).map(Fatura::clone)
                    .collect(Collectors.toList());
    }

    /**
     * Obter uma lista de faturas ordenadas por ordem cronológica entre duas datas
     */
    public List<Fatura> listafaturas_Crono(LocalDate begin, LocalDate end)
            throws EmptySetException, InvalidIntervalException {

        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Não contém faturas \n");

        List<Fatura> l = this.faturas_dt.stream().filter(c -> !c.isPendente()).map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());

        if (l.size() == 0)
            throw new InvalidIntervalException("O intervalo é invalido\n");
        else
            return l;

    }

    /**
     * Obter uma lista de faturas ordenadas pelo valor
     */
    public List<Fatura> listafaturas_Valor() throws EmptySetException {

        if (this.faturas_val.size() == 0)
            throw new EmptySetException("Não contém faturas \n");
        else
            return this.faturas_val.stream().filter(l -> !l.isPendente()).map(Fatura::clone)
                    .collect(Collectors.toList());
    }

    public List<Fatura> listafaturas_Pendente() {
        return this.faturas_val.stream().filter(l -> !l.isPendente()).map(Fatura::clone).collect(Collectors.toList());
    }

    /**
     * Obter uma lista de faturas ordendas pelo valor entre duas datas
     */
    public List<Fatura> listafaturas_Valor(LocalDate begin, LocalDate end)
            throws EmptySetException, InvalidIntervalException {

        if (this.faturas_val.size() == 0)
            throw new EmptySetException(" Conjunto de faturas vazio \n");

        List<Fatura> l = this.faturas_val.stream().filter(f -> !f.isPendente()).map(Fatura::clone)
                .filter(p -> p.getDate().isAfter(begin) && p.getDate().isBefore(end)).collect(Collectors.toList());

        if (l.size() == 0)
            throw new InvalidIntervalException(" O intervalo é invalido\n");
        else
            return l;
    }

    /**
     * Obtem uma copia do Contacto
     */
    public Contacto getContacto() {
        return this.info.clone();
    }

    /**
     * Obter a password
     */
    public String getPassword() throws InvalidFieldException {
        if (this.password == "invalido")
            throw new InvalidFieldException(" Ainda não foi inserida uma password ");
        return this.password;
    }

    /**
     * Obter as despesas totais do conjunto de Faturas da Entidade
     */
    public double getDespesa() {
        if (this.faturas_val.size() == 0) {
            return 0;
        } else {
            return this.faturas_dt.stream().filter(l -> ! l.isPendente() )
                    .mapToDouble(Fatura::getTotal).sum();
        }
    }

    /**
     * Obter as despesas totais do conjunto de Faturas da Entidade entre duas datas
     */
    public double getDespesa(LocalDate begin, LocalDate end) {
        if (this.faturas_val.size() == 0) {
            return 0;
        } else {
            return this.faturas_dt.stream().filter(l -> ! l.isPendente() ).filter(p -> (p.getDate().isAfter(begin)) && (p.getDate().isBefore(end)))
                    .mapToDouble(Fatura::getTotal).sum();
        }
    }

    /**
     * Obter um Map que associa as despesas totais a uma dada Atividade(area) Cria
     * se um histograma , se a atividade ainda na estiver no histograma sua Despesa
     * é inicializada a 0 Se a atividade já estiver contida no histograma entao soma
     * se o valor atual + a despesa associada ha atual Fatura
     */
    public Map<Atividade, Double> getDespesaArea() throws EmptySetException {
        HashMap<Atividade, Double> hist = new HashMap<>();
        Double count;
        if (this.faturas_dt.size() == 0)
            throw new EmptySetException("Conjunto de faturas vazio\n");

        Atividade a;
        for (Fatura l : this.faturas_dt) {
            if (!l.isPendente()) {
                try {
                    a = l.getArea();
                } catch (InvalidActivityException b) {
                    continue;
                }

                if (hist.containsKey(a)) {
                    count = hist.get(a);

                } else {
                    count = new Double(0);
                }
                hist.put(a, new Double(count.doubleValue() + l.getTotal()));
            }
        }
        return hist;

    }

    /**
     * Remove uma determinada fatura
     */
    public boolean removerFatura(Fatura bh) {
        return this.faturas_dt.remove(bh);
    }

    /**
     * Método que devolve a representação em String de toda a Entidade.
     * 
     * @return String com todas as variáveis de instâncias(exceto password).
     */
    public String toString() {
        String text, space;

        text = this.info.toString() + " " + faturas_dt.toString();

        return text;
    }

    /**
     * Adiciona uma fatura
     */
    public void addFatura(Fatura old, Fatura x) {
        
        if( old != null && this.faturas_dt.contains(old) ){
            this.faturas_dt.remove(old);
            this.faturas_val.remove(old);
        }

        Fatura newer= x.clone();
        this.faturas_dt.add(newer);
        this.faturas_val.add(newer);
    }

    /**
     * Modifica o contacto da Entidade para outro Contacto
     */
    public void setContacto(Contacto x) {
        this.info = x.clone();
    }

    /**
     * Modifica a password da Entidade
     */
    public void setPassword(String pw) {
        this.password = pw;
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

        boolean r = this.info.equals(inc.getContacto());
        boolean l;
        try {
            l = this.faturas_val.containsAll(inc.getfaturas_Valor());

        } catch (EmptySetException e) {
            l = (this.faturas_val.size() == 0);
        }

        return r && l;
    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */

    /**
     * Obtem um hasCode para um Nif associado ao Contacto
     */
    public int hashCode() {
        long v;
        try {
            v = this.info.getNif();
        } catch (InvalidFieldException e) {
            return -1;
        }
        return (int) (v ^ (v >>> 32));
    }
}
