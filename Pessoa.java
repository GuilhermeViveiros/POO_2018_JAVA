import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

public class Pessoa extends Entidade implements Serializable{

    private List<Long> agregado; // números fiscais do agregado familiar
    private Atividade emprego;
    private long nifEmpregador;

    public Pessoa(){

        super();
        this.agregado = new ArrayList<Long>();
        this.emprego = null;
        this.nifEmpregador = 0;

    }

    public Pessoa(Contacto cont, List<Long> agr, Atividade emprego, long empr) {
        super(cont);
        this.emprego = emprego.clone();
        this.agregado = agr.stream().collect(Collectors.toList());
        this.nifEmpregador = empr;
    }

    public Pessoa(Contacto cont, List<Long> agr) {
        super(cont);
        this.agregado = agr.stream().collect(Collectors.toList());
        this.emprego = null;
        this.nifEmpregador = 0;
    }

    public Pessoa(Pessoa p) {
        super(p);
        this.agregado = p.getAgregado();
        this.emprego = p.getEmprego();
        this.nifEmpregador = p.getNifEmpregador();
    }

    public List<Long> getAgregado() throws EmptyListException{
        if(this.agregado.size() == 0) throw new EmptyListException("Lista de agregados inválida");
        return this.agregado.stream().collect(Collectors.toList());
    }

    public Atividade getEmprego() {
        return this.emprego.clone();
    }

    public long getNifEmpregador() {
        return this.nifEmpregador;
    }

    public int numeroDeElementosDoAgregado() throws EmptyListException{
        if(this.agregado.size() == 0) throw new EmptyListException("Lista de agregados inválida");
        return this.agregado.size();
    }

    public String toString() {
        return super.toString() + "\nNúmero de dependentes do agregado Familiar : " + this.agregado.size()
                + "\nNúmeros de fiscais do agregado Familiar : " + this.agregado.toString()
                + this.emprego.getCodidigoActividade();
    }

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

    public Pessoa clone() {
        return (new Pessoa(this));
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Pessoa inc = (Pessoa) o;
        try{
        if (super.equals(inc) && this.getEmprego().equals(inc.getEmprego())
                && (this.numeroDeElementosDoAgregado() == inc.numeroDeElementosDoAgregado())
                && (this.getNifEmpregador() == inc.getNifEmpregador()))
            return true;
        } catch (EmptyListException e) {
            return this.numeroDeElementosDoAgregado() == inc.numeroDeElementosDoAgregado();
        }

        return false;

    }

}
