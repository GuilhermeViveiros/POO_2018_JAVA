 

import java.time.LocalDate;
import java.io.Serializable;

public class Educacao implements Atividade,Serializable
{
    
    private String nome; // nome da atividade
    private String codigo; // codigo da atividade
    private boolean check;

    public Educacao() {
        this.nome = "nenhum";
        this.codigo = "nenhum";
        this.check = false;
    }

    public Educacao(String nome, String codigo, boolean check) {
        this.nome = nome;
        this.codigo = codigo;
        this.check = check;
    }

    public Educacao(Educacao x) {
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
            try {
                valor = x.totalFaturado(begin, end);
            } catch (EmptySetException a) {
                valor = 0;
            }

            try {
                valor += x.getDespesa(begin, end);
            } catch (EmptySetException a) {

            }
            return valor * 0.3;
        }
        return 0;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Pessoa x, LocalDate begin, LocalDate end) {
        double valor;

        if (this.check) {

            try {
                valor = x.getDespesa(begin, end);
            } catch (EmptySetException e) {
                valor = 0;
            }
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
        return (Atividade)(new Educacao(this));
    }

    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x.getClass() != this.getClass() || x == null)
            return false;
        Educacao y = (Educacao) x;
        
        boolean r = (this.check == y.areaDedusivel());
        boolean l;

        try {
            l = (this.nome == y.getNomeActividade());
        } catch (InvalidFieldException e) {
            l = (this.nome ==  "nenhum");
        }
        r = r && l;
        try {
            l = ( this.codigo == y.getCodidigoActividade());
        } catch (InvalidFieldException e) {
            l = ( this.codigo == "nenhum");
        }
        return (r && l);
    }
    
}
