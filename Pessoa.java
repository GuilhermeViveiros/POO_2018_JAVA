import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

public class Pessoa extends Entidade implements Serializable {

    private List<Long> agregado; // números fiscais do agregado familiar
    private Atividade emprego;
    private long nifEmpregador;

    public Pessoa() {
        super();
        this.agregado = new ArrayList<Long>();
        this.emprego = null;
        this.nifEmpregador = -1;

    }

    public Pessoa(Contacto cont,String password, List<Long> agr, Atividade emprego, long empr) {
        super(cont,password);
        this.emprego = emprego.clone();
        this.agregado = new ArrayList(agr);
        this.nifEmpregador = empr;
    }

    public Pessoa(Contacto cont,String password, List<Long> agr) {
        super(cont,password);
        this.agregado = new ArrayList(agr);
        this.emprego = null;
        this.nifEmpregador = -1;
    }

    public Pessoa(Pessoa p) {
        super(p);

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
    }

    public List<Long> getAgregado() throws EmptySetException {
        if (this.agregado.size() == 0)
            throw new EmptySetException("Lista de agregados inválida");
        return this.agregado.stream().collect(Collectors.toList());
    }

    public Atividade getEmprego() throws InvalidActivityException {
        if (this.emprego == null)
            throw new InvalidActivityException(" É Desempregado ");

        return this.emprego.clone();
    }

    public long getNifEmpregador() throws InvalidFieldException {
        if (this.nifEmpregador == -1)
            throw new InvalidFieldException(" Campo de nif invalido");

        return this.nifEmpregador;
    }

    public Set<String> codigosdeAtividadesDeduziveis() throws EmptySetException{
        
        Set<String> x = new HashSet<String>(); 
        for( Fatura f: this.getfaturas_Valor()){
            Atividade g;
            try{
                g = f.getArea();
            }catch (InvalidActivityException aaa){
                continue;
            }

            if( g.areaDedusivel() && (!x.contains(g)) ){
                try{ 
                    x.add(g.getCodidigoActividade());
                }catch (InvalidFieldException aaa){
                    continue;
                }
            }
        } 
        if( x.size() == 0 )
            throw new EmptySetException();

        return x;
    }

    public int numeroDeElementosDoAgregado() {
        return this.agregado.size();
    }

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

        try {
            this.nifEmpregador = empregador.getContacto().getNif();
        } catch (InvalidFieldException a) {
            this.nifEmpregador = -1;
        }
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
