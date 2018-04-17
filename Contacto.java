
/**
 * Escreva a descrição da classe Contacto aqui.
 * 
 * @author (Gonçalo Faria) 
 * @version (v1)
 */
public class Contacto {
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
        this.nif = x.getNif();
        this.nome = x.getNome();
        this.mail = x.getMail();
        this.morada = x.getMorada();
        this.telefone = x.getTelefone();
    }

    // ---- Getters --------------------------------
    public long getNif() {
        return this.nif;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMail() {
        return this.mail;
    }

    public String getMorada() {
        return this.morada;
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

        if ( (this.nif == inc.getNif() ) &&
                (this.nome.equals( inc.getNome() ) ) &&
                    (this.morada.equals( inc.getMorada() ) ) )
            return true;

        return false;

    }

    public Contacto clone() {

        return (new Contacto(this));
    }
}