
import java.time.LocalDate;

public class Agricultura implements Atividade {

    private String nome; // nome da atividade
    private String codigo; // codigo da atividade
    private boolean check; // estou a assumir que nao irá contar para as despesas

    private double a;
    private double b;

    public Agricultura() {
        this.nome = "nenhum";
        this.codigo = "nenhum";
        this.check = false;
        this.a = 1;
        this.b = 1;
    }

    public Agricultura(String nome, String codigo, boolean check, double a, double b) {
        this.nome = nome;
        this.codigo = codigo;
        this.check = check;
        this.a = 1;
        this.b = 1;
    }

    public Agricultura(Agricultura x) {
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
        this.a = getParamA();
        this.b = getParamB();
    }

    public double getParamA() {
        return this.a;
    }

    public double getParamB() {
        return this.b;
    }

    public double setParamA(double a) {
        return this.a = a;
    }

    public double setParamB(double b) {
        return this.b = b;
    }

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

            valor = x.totalFaturado(begin, end) * this.a;

            valor += x.getDespesa(begin, end) * this.b;

            return valor;
        }
        return 0;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Pessoa x, LocalDate begin, LocalDate end) {
        double valor;

        if (this.check) {
            valor = x.getDespesa(begin, end);

            return valor * this.b;
        }
        return 0;
    }

    public Atividade clone() {
        return (Atividade) (new Agricultura(this));
    }

    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x.getClass() != this.getClass() || x == null)
            return false;
        Agricultura y = (Agricultura) x;

        boolean r = (this.check == y.areaDedusivel()) && (this.a == y.getParamA()) && (this.b == y.getParamB());
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

    public int hashCode() {
        String word = this.nome + this.codigo;
        return word.hashCode();
    }

}
