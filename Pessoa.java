import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pessoa extends Entidade {

    private List<Long> agregado; // números fiscais do agregado familiar
    private Atividade emprego;
    private long nifEmpregador;

    public Pessoa() { // cria uma pessoa do ponto zero(DEFAULT)

        super();
        this.agregado = new ArrayList<Long>();
        this.emprego = null;
        this.nifEmpregador = 0;

    }

    // cria uma pessoa ao receber todos os dados necessários
    public Pessoa(Contacto cont, List<Long> agr, Atividade emprego, long empregador) {
        super(cont);
        this.emprego = emprego.clone();
        this.agregado = agr.stream().collect(Collectors.toList());
        this.nifEmpregador = empregador;
    }

    public Pessoa(Contacto cont, List<Long> agr) {
        super(cont);
        this.agregado = agr.stream().collect(Collectors.toList());
        this.emprego = null;
        this.nifEmpregador = 0;
    }

    // cria uma pessoa a partir de outra
    public Pessoa(Pessoa p) {
        super(p);

        this.agregado = p.getAgregado();
        this.emprego = p.getEmprego();
        this.nifEmpregador = p.getNifEmpregador();
    }

    // getters

    public List<Long> getAgregado() {
        return this.agregado.stream().collect(Collectors.toList());
    }

    public Atividade getEmprego() {
        return this.emprego.clone();
    }

    public long getNifEmpregador() {
        return this.nifEmpregador;
    }

    public int numeroDeElementosDoAgregado() {
        return this.agregado.size();
    }

    // retorna como string toda a informação fiscal disponivel da pessoa
    public String toString() {
        return super.toString() + "\nNúmero de dependentes do agregado Familiar : " + this.agregado.size()
                + "\nNúmeros de fiscais do agregado Familiar : " + this.agregado.toString()
                + this.emprego.getCodidigoActividade();
    }

    // setters
    public void setAgregado(List<Long> agregado) {
        this.agregado = agregado.stream().collect(Collectors.toList());
    }

    public void addAgregado(Long membro) {
        this.agregado.add(membro);
    }

    public void addAgregado(long membro) {
        this.agregado.add(new Long(membro));
    }

    public void setEmprego(Atividade emprego) {
        this.emprego = emprego.clone();
    }

    public void setNifEmpregador(long empregador) {
        this.nifEmpregador = empregador;
    }

    public void setNifEmpregador(Empresa empregador) {
        this.nifEmpregador = empregador.getContacto().getNif();
    }

    /*
     * private void ArrayToArrayList(long[] b){
     * 
     * this.nrFAF = new ArrayList<Long>();
     * 
     * for(int i = 0; i < b.length; i++){ this.nrFAF.add(b[i]); } }
     */

    public Pessoa clone() {
        return (new Pessoa(this));
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Pessoa inc = (Pessoa) o;

        if (super.equals(inc) && this.getEmprego().equals(inc.getEmprego())
                && (this.numeroDeElementosDoAgregado() == inc.numeroDeElementosDoAgregado())
                && (this.getNifEmpregador() == inc.getNifEmpregador()))
            return true;

        // não é neces

        return false;

    }

}
