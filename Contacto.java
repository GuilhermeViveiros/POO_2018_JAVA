import java.io.Serializable;
 

/**
 * Escreva a descrição da classe Contacto aqui.
 * 
 * @author (Gonçalo Faria)
 * @version (v1)
 */
public class Contacto implements Serializable{
    private long nif;
    private String nome;
    private String mail;
    private String morada;
    private String telefone;

    public Contacto() {
        this.nif = -1;
        this.nome = "campo vazio";
        this.mail = "campo vazio";
        this.morada = "campo vazio";
        this.telefone = "campo vazio";
    }

    public Contacto(long ni_p, String nom_p, String mai_p, String morad_p, String telefone) {
        this.nif = ni_p;
        this.nome = nom_p;
        this.mail = mai_p;
        this.morada = morad_p;
        this.telefone = telefone;
    }

    public Contacto(Contacto x) {
        
        try{this.nif = x.getNif();}catch (InvalidFieldException a){this.nif = -1;}
        try{ this.nome = x.getNome(); }catch (InvalidFieldException a){this.nome ="campo vazio";}
        try{ this.mail = x.getMail(); }catch (InvalidFieldException a){this.mail ="campo vazio";}
        try{ this.morada = x.getMorada(); }catch (InvalidFieldException a){this.morada ="campo vazio";}
        try{ this.telefone = x.getTelefone(); }catch (InvalidFieldException a){this.telefone ="campo vazio";}
    }

    // ---- Getters --------------------------------
    public long getNif() throws InvalidFieldException {
        if (this.nif == -1) {
            throw new InvalidFieldException("O campo Nif ainda não foi preenchido\n");
        } else {
            return this.nif;
        }
    }

    public String getTelefone() throws InvalidFieldException {
        if (this.telefone.equals("campo vazio")) {
            throw new InvalidFieldException("O campo telefone ainda não foi preenchido\n");
        } else {
            return this.telefone;
        }
    }

    public String getNome() throws InvalidFieldException {
        if (this.nome.equals("campo vazio")) {
            throw new InvalidFieldException("O campo nome ainda não foi preenchido\n");
        } else {
            return this.nome;
        }
    }

    public String getMail() throws InvalidFieldException{
        if (this.mail.equals("campo vazio")) {
            throw new InvalidFieldException("O campo mail ainda não foi preenchido\n");

        } else {
            return this.mail;
        }
    }

    public String getMorada() throws InvalidFieldException {
        if (this.morada.equals("campo vazio")) {
            throw new InvalidFieldException("O campo morada ainda não foi preenchido\n");

        } else {
            return this.morada;
        }
    }

    public String toString() {
        String text, space;
        space = "________________________________________\n";

        text = space;
        text += "nome: " + this.nome + "\n\n";
        text += "nif: " + this.nif + "\n";
        text += space;

        text += "morada: " + this.morada + "\n";
        text += space;

        text += "mail: " + this.mail + "\n";
        text += "telefone: " + this.telefone + "\n";

        return (text);
    }

    // ---- Setters --------------------------------

    public void setNif(long n) {
        this.nif = n;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public void setMail(String n) {
        this.mail = n;
    }

    public void setMorada(String n) {
        this.morada = n;
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Contacto inc = (Contacto) o;

        boolean r = true;
        boolean l;

        try{ l = (this.nif == inc.getNif());} catch (InvalidFieldException a){ l =(this.nif == -1);}
        r = r && l;

        try{ l = this.nome.equals(inc.getNome()); }catch (InvalidFieldException a){ l = this.nome.equals("campo vazio");}
        r = r && l;

        try{ l = this.morada.equals(inc.getMorada()); }catch (InvalidFieldException a){ l = this.morada.equals("campo vazio");}
        r = r && l;

        try{ l = this.telefone.equals(inc.getTelefone()); }catch (InvalidFieldException a){ l = this.telefone.equals("campo vazio");}

        return r && l;
    }

    public Contacto clone() {

        return (new Contacto(this));
    }
}
