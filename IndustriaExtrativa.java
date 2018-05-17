import java.time.LocalDate;
import java.io.Serializable;

public class IndustriaExtrativa implements Atividade, Serializable {

    private String nome; // nome da atividade
    private String codigo; // codigo da atividade
    static boolean check; // estou a assumir que nao irá contar para as despesas
    
    static{
        check = false;
    }

    public IndustriaExtrativa() {
        this.nome = "nenhum";
        this.codigo = "nenhum";
    }

    public IndustriaExtrativa(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public IndustriaExtrativa(IndustriaExtrativa x) {
        
        try{
            this.nome = x.getNomeActividade();
        } catch (InvalidFieldException e){
            this.nome = "nenhum" ;
        }

        try{
            this.codigo = x.getCodidigoActividade();
        } catch (InvalidFieldException e){
            this.codigo = "nenhum" ;
        }
    }
    

    public String setNomeActividade(String nome){
        return this.nome = nome;
    }

    public String setCodidigoActividade(String code) {
        return this.codigo = code;
    }

    // Metodos
    public String getCodidigoActividade() throws InvalidFieldException {
        if( this.codigo.equals("nenhum") ){
            throw new InvalidFieldException(" Código de Atividade não indicado ");
        }
        
        return this.codigo;
    }

    public String getNomeActividade() throws InvalidFieldException{
        if( this.nome.equals("nenhum") ){
            throw new InvalidFieldException(" Nome da Atividade não indicado ");
        }
        
        return this.nome;
    }

    public boolean areaDedusivel() {
        return check;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Empresa x, LocalDate begin, LocalDate end) {
        return 0.0;
    }

    // (Algoritmo improvisado)
    public double regraCalculo(Pessoa x, LocalDate begin, LocalDate end) {
        return 0.0;
    }

    public Atividade clone() {
        return (Atividade)(new IndustriaExtrativa(this));
    }

    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x.getClass() != this.getClass() || x == null)
            return false;
        Saude y = (Saude) x;
        
        boolean r = true;
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

    public int hashCode() {
        String word = this.nome + this.codigo;
        return word.hashCode();
    }

}
