import java.util.*;
import java.util.stream.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta classe implementa uma Pessoa. Uma Pessoa é uma sub-classe da Entidade
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

public class Pessoa extends Entidade implements Serializable, Reducao {
    /** Números fiscais do agregado familiar da Pessoa */
    private List<Long> agregado;
    /** Emprego da Pessoa */
    private Atividade emprego;
    /** Nif da Pessoa */
    private long nifEmpregador;
    private boolean numerosa;
    private double coeficiente;

    /**
     * Construtor por omissão de Entidade.
     */
    public Pessoa() {
        super();
        this.agregado = new ArrayList<Long>();
        this.emprego = null;
        this.nifEmpregador = -1;
        this.numerosa = false;
        this.coeficiente = 1.0;
    }

    /**
     * Construtor parametrizado de Pessoa.
     * 
     * @param Contacto
     * @param Password
     * @param Agregado
     * @param Emprego
     * @param Nif
     */
    public Pessoa(Contacto cont, String password, List<Long> agr, Atividade emprego, long empr, double coe) {
        super(cont, password);
        this.emprego = emprego.clone();
        this.agregado = new ArrayList(agr);
        this.nifEmpregador = empr;
        this.coeficiente = coe;

        this.refNumerosa();
    }

    /**
     * Construtor parametrizado de Pessoa.
     * 
     * @param Contacto
     * @param Password
     * @param Agregado
     */

    public Pessoa(Contacto cont, String password, List<Long> agr, double coe) {
        super(cont, password);
        this.agregado = new ArrayList(agr);
        this.emprego = null;
        this.nifEmpregador = -1;
        this.coeficiente = coe;

        this.refNumerosa();
    }

    public Pessoa(Contacto cont, String password, double coe) {
        super(cont, password);
        this.agregado = new ArrayList();
        this.emprego = null;
        this.nifEmpregador = -1;
        this.coeficiente = coe;
        this.numerosa = false;
    }

    /**
     * Construtor de cópia da Pessoa. Aceita como parâmetro outra Pessoa e utiliza
     * os métodos de acesso aos valores das variáveis de instância. A
     * 
     * @param Pessoa
     */
    public Pessoa(Pessoa p) {
        super(p);

        this.coeficiente = p.getCoeficiente();
        try {
            this.agregado = p.getAgregado();
        } catch (EmptySetException a) {
            this.agregado = new ArrayList<Long>();
        }

        try {
            this.emprego = p.getEmprego();
        } catch (InvalidActivityException a) {
            this.emprego = null;
        }

        try {
            this.nifEmpregador = p.getNifEmpregador();
        } catch (InvalidFieldException a) {
            this.nifEmpregador = -1;
        }

        this.refNumerosa();

    }

    public double reducaoImposto() {
        if (this.numerosa) {
            return this.agregado.size() * 0.05;
        } else
            return 0.0;
    }

    public double calculoDeducao(LocalDate begin, LocalDate end) {
        return this.emprego.regraCalculo(this, begin, end);
    }

    /**
     * Obtem uma lista do agregado familiar
     */
    public List<Long> getAgregado() throws EmptySetException {
        if (this.agregado.size() == 0)
            throw new EmptySetException("Lista de agregados inválida");
        return this.agregado.stream().collect(Collectors.toList());
    }

    public double getCoeficiente() {
        return this.coeficiente;
    }

    /**
     * Obtem uma Atividade relacionada ao emprego da Pessoa
     */
    public Atividade getEmprego() throws InvalidActivityException {
        if (this.emprego == null)
            throw new InvalidActivityException(" É Desempregado ");

        return this.emprego.clone();
    }

    /**
     * Obtem um Nif da Pessoa
     */
    public long getNifEmpregador() throws InvalidFieldException {
        if (this.nifEmpregador == -1)
            throw new InvalidFieldException(" Campo de nif invalido");

        return this.nifEmpregador;
    }

    /**
     * Obtem um Set com todas as Atividades associadas ás faturas da Pessoa
     */
    public Set<String> codigosdeAtividadesDeduziveis() throws EmptySetException {

        Set<String> x = new HashSet<String>();
        for (Fatura f : this.getfaturas_Valor()) {
            Atividade g;
            try {
                g = f.getArea();
            } catch (InvalidActivityException aaa) {
                continue;
            }

            if (g.areaDedusivel() && (!x.contains(g))) {
                try {
                    x.add(g.getCodidigoActividade());
                } catch (InvalidFieldException aaa) {
                    continue;
                }
            }
        }
        if (x.size() == 0)
            throw new EmptySetException();

        return x;
    }

    /**
     * Obtem o numero de pessoas do agregado familiar
     */
    public int numeroDeElementosDoAgregado() {
        return this.agregado.size();
    }

    /**
     * Método que devolve a representação em String de toda a Pessoa.
     * 
     * @return String com todas as variáveis de instâncias.
     */
    public String toString() {
        String x = super.toString() + "\nNúmero de dependentes do agregado Familiar : " + this.agregado.size()
                + "\nNúmeros de fiscais do agregado Familiar : " + this.agregado.toString();
        String y;

        try {
            y = this.emprego.getCodidigoActividade();
        } catch (InvalidFieldException a) {
            y = "";
        }
        return x + y;
    }

    /**
     * Redefine uma nova lista de numeros fiscais do agregado familiar
     */
    public void setAgregado(List<Long> agregado) {
        this.agregado = agregado.stream().collect(Collectors.toList());

        this.refNumerosa();

    }

    public void setCoeficiente(double coe) {
        this.coeficiente = coe;
    }

    private void refNumerosa() {

        if (this.agregado.size() > 4) {
            this.numerosa = true;
        } else {
            this.numerosa = false;
        }
    }

    /**
     * Adiciona uma novo membro ao agregado familiar
     * 
     * @param Long
     */
    public void addAgregado(Long membro) {
        this.agregado.add(membro);
        this.refNumerosa();
    }

    /**
     * Adiciona um novo membro ao agregado familiar
     * 
     * @param long
     */
    public void addAgregado(long membro) {
        this.agregado.add(new Long(membro));

        this.refNumerosa();
    }

    /**
     * Redefine a atividade da Pessoa
     */
    public void setEmprego(Atividade emprego) {
        this.emprego = emprego.clone();
    }

    /**
     * Redefine o nif da Pessoa
     * 
     * @param Empregador
     */
    public void setNifEmpregador(long empregador) {
        this.nifEmpregador = empregador;
    }

    /**
     * Redefine o nif da Pessoa
     * 
     * @param Empresa
     */
    public void setNifEmpregador(Empresa empregador) {
        try {
            this.nifEmpregador = empregador.getContacto().getNif();
        } catch (InvalidFieldException a) {
            this.nifEmpregador = -1;
        }
    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Pessoa clone() {
        return (new Pessoa(this));
    }

    /**
     * Método que determina se 2 Pessoas são iguais. Esta função é deterministica,
     * reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso as Pessoas sejam iguais e falso caso
     *         contrário.
     */
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Pessoa inc = (Pessoa) o;

        boolean r = super.equals(inc) && (this.numeroDeElementosDoAgregado() == inc.numeroDeElementosDoAgregado());
        boolean l;

        // && (this.getNifEmpregador() == inc.getNifEmpregador()))
        try {
            l = this.emprego.equals(inc.getEmprego());
        } catch (InvalidActivityException e) {
            l = (this.emprego == null);
        }
        r = r && l;

        try {
            l = (this.nifEmpregador == inc.getNifEmpregador());
        } catch (InvalidFieldException e) {
            l = (this.nifEmpregador == -1);
        }

        return r && l;
    }

}
