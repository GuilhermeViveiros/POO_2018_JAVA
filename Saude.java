import java.io.Serializable;
import java.time.LocalDate;

public class Saude implements Atividade, Serializable {
    private String nome; // nome da atividade
    private String codigo; // codigo da atividade
    private boolean check;

    public Saude() {
        this.nome = "nenhum";
        this.codigo = "nenhum";
        this.check = false;
    }

    public Saude(String nome, String codigo, boolean check) {
        this.nome = nome;
        this.codigo = codigo;
        this.check = check;
    }

    public Saude(Saude x) {
        try {
            this.nome = x.getNomeActividade();
        } catch (InvalidFieldException e) {
            this.nome = "nenhum";
        }

        try {
            this.codigo = x.getCodidigoActividade();
        } catch (InvalidFieldException e) {
            this.codigo = "nenhum";
        }
        this.check = x.areaDedusivel();
    }

    // Metodos
    public String getCodidigoActividade() throws InvalidFieldException {
        if (this.codigo.equals("nenhum")) {
            throw new InvalidFieldException(" Código de Atividade não indicado ");
        }
        return this.codigo;
    }

    public String getNomeActividade() throws InvalidFieldException {
        if (this.nome.equals("nenhum")) {
            throw new InvalidFieldException(" Nome da Atividade não indicado ");
        }

        return this.nome;
    }

    public boolean areaDedusivel() {
        return check;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Empresa x, LocalDate begin, LocalDate end) {
        double valor;

        if (check) {

            valor = x.totalFaturado(begin, end);

            valor += x.getDespesa(begin, end);

            return valor * 0.3;
        }
        return 0;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Pessoa x, LocalDate begin, LocalDate end) {
        double valor;

        if (this.check) {

            valor = x.getDespesa(begin, end);

            return valor * 0.3;
        }
        return 0;
    }

    // (Algoritmo improvisado)
    public int hashCode() {
        String word = this.nome + this.codigo;
        return word.hashCode();
    }

    public Atividade clone() {
        return (Atividade) (new Saude(this));
    }

    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x.getClass() != this.getClass() || x == null)
            return false;
        Saude y = (Saude) x;

        boolean r = (this.check == y.areaDedusivel());
        boolean l;

        try {
            l = (this.nome == y.getNomeActividade());
        } catch (InvalidFieldException e) {
            l = (this.nome == "nenhum");
        }
        r = r && l;
        try {
            l = (this.codigo == y.getCodidigoActividade());
        } catch (InvalidFieldException e) {
            l = (this.codigo == "nenhum");
        }
        return (r && l);
    }

}
