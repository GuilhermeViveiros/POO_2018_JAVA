import java.io.Serializable;

/**
 * Esta classe implementa um Contacto.
 * Um Contacto é uma classe que faz parte de uma Entidade
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

public class Contacto implements Serializable {
    /** O nif do Contacto */
    private Long nif;
    /** O nome de Contacto*/
    private String nome;
    /** O mail do Contacto */
    private String mail;
    /** A morada do Contacto */
    private String morada;
    /** O telefone do Contacto */
    private String telefone;

    /**
    * Construtor por omissão de Contacto.
    */
    public Contacto() {
        this.nif = new Long(-1);
        this.nome = "campo vazio";
        this.mail = "campo vazio";
        this.morada = "campo vazio";
        this.telefone = "campo vazio";
    }

    /**
     * Construtor parametrizado de Fatura. Aceita como parâmetros os valores para
     * cada variável de instância.
     * @param Nif
     * @param Nome
     * @param Mail
     * @param Morada
     * @param Telefone
     */
    public Contacto(Long ni_p, String nom_p, String mai_p, String morad_p, String telefone) {
        this.nif = ni_p;
        this.nome = nom_p;
        this.mail = mai_p;
        this.morada = morad_p;
        this.telefone = telefone;
    }

     /**
     * Construtor de cópia de Contacto. Aceita como parâmetro outro Contacto e
     * utiliza os métodos de acesso aos valores das variáveis de instância. 
     * @param Contacto
     */
    public Contacto(Contacto x) {

        try {
            this.nif = x.getNif();
        } catch (InvalidFieldException a) {
            this.nif = new Long(-1);
        }
        try {
            this.nome = x.getNome();
        } catch (InvalidFieldException a) {
            this.nome = "campo vazio";
        }
        try {
            this.mail = x.getMail();
        } catch (InvalidFieldException a) {
            this.mail = "campo vazio";
        }
        try {
            this.morada = x.getMorada();
        } catch (InvalidFieldException a) {
            this.morada = "campo vazio";
        }
        try {
            this.telefone = x.getTelefone();
        } catch (InvalidFieldException a) {
            this.telefone = "campo vazio";
        }
    }

    /**
     * Métodos de instância
     */

     /**
      * Obtem o nif do Contacto
      */
    public Long getNif() throws InvalidFieldException {
        if (this.nif.longValue() == -1) {
            throw new InvalidFieldException("O campo Nif ainda não foi preenchido\n");
        } else {
            return this.nif;
        }
    }

    /**
     * Obtem o numero de telefone do contacto
     */
    public String getTelefone() throws InvalidFieldException {
        if (this.telefone.equals("campo vazio")) {
            throw new InvalidFieldException("O campo telefone ainda não foi preenchido\n");
        } else {
            return this.telefone;
        }
    }

    /**
     * Obtem o nome do Contacto
     */
    public String getNome() throws InvalidFieldException {
        if (this.nome.equals("campo vazio")) {
            throw new InvalidFieldException("O campo nome ainda não foi preenchido\n");
        } else {
            return this.nome;
        }
    }

    /**
     * Obtem o mail do contacto
     */
    public String getMail() throws InvalidFieldException {
        if (this.mail.equals("campo vazio")) {
            throw new InvalidFieldException("O campo mail ainda não foi preenchido\n");

        } else {
            return this.mail;
        }
    }

    /**
     * Obtem a morada do Contacto
     */
    public String getMorada() throws InvalidFieldException {
        if (this.morada.equals("campo vazio")) {
            throw new InvalidFieldException("O campo morada ainda não foi preenchido\n");

        } else {
            return this.morada;
        }
    }

     /**
     * Método que devolve a representação em String de toda o Contacto. 
     */
    public String toString() {
        String text, space;
        space = "________________________________________\n";

        text = space;
        text += "nome: " + this.nome + "\n\n";
        text += "nif: " + this.nif.toString() + "\n";
        text += space;

        text += "morada: " + this.morada + "\n";
        text += space;

        text += "mail: " + this.mail + "\n";
        text += "telefone: " + this.telefone + "\n";

        return (text);
    }

    /**
     * Redefine o nif do Contacto
     */
    public void setNif(Long n) {
        this.nif = n;
    }

    /**
     * Redefine o numero de telefone de um Contacto
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Redefine o nome de Contacto
     */
    public void setNome(String n) {
        this.nome = n;
    }

    /**
     * Redefine o mail do Contacto
     */
    public void setMail(String n) {
        this.mail = n;
    }

    /**
     * Redefine a morada do Contacto
     */
    public void setMorada(String n) {
        this.morada = n;
    }

    /**
     * Método que determina se 2 Contactos são iguais.
     * Esta função é deterministica, reflexiva, transitiva e simétrica.
     * 
     * @return booleano que é verdadeiro caso os Contactos sejam iguais e falso caso
     *         contrário.
     */
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Contacto inc = (Contacto) o;

        boolean r = true;
        boolean l;

        try {
            l = (this.nif.longValue() == inc.getNif().longValue());
        } catch (InvalidFieldException a) {
            l = (this.nif.longValue() == -1);
        }
        r = r && l;

        try {
            l = this.nome.equals(inc.getNome());
        } catch (InvalidFieldException a) {
            l = this.nome.equals("campo vazio");
        }
        r = r && l;

        try {
            l = this.morada.equals(inc.getMorada());
        } catch (InvalidFieldException a) {
            l = this.morada.equals("campo vazio");
        }
        r = r && l;

        try {
            l = this.telefone.equals(inc.getTelefone());
        } catch (InvalidFieldException a) {
            l = this.telefone.equals("campo vazio");
        }

        return r && l;
    }

    /**
     * Método que faz o clone do objeto receptor da mensagem. Para tal invoca o
     * construtor de cópia.
     * 
     * @return objecto clone do objeto que recebe mensagem.
     */
    public Contacto clone() {
        return (new Contacto(this));
    }
}
