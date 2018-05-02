import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class Pessoa extends Entidade{

   
    private int nrDAF; // número de dependentes do agregado familiar
    private List<Long> nrFAF; // números fiscais do agregado familiar
    private float cF; // coeficiente fiscal
    private String cAE; // códigos das atividades economicas
    private Set<Faturas> emissoes; //lista de Faturas de uma pessoa ordenadas por data
   
    public Pessoa(){ // cria uma pessoa do ponto zero(DEFAULT)
        
        super();
        this.nrDAF = 0;
        this.nrFAF = new ArrayList<Long>();
        this.cF = 0;
        this.cAE = "Indefinido";

    }
    
    // cria uma pessoa ao receber todos os dados necessários
    public Pessoa(long nif, String nome, String mail, String morada, int nrDAF_P, List<Long> nrFAF_P, float cF_P, String cAE_P, String number){
        super(new Contacto(nif, nome, mail, morada, number));
        this.nrDAF = nrDAF_P;
        //clone?
        this.nrFAF = nrFAF_P.stream().collect(Collectors.toList());
        this.cF = cF_P;
        this.cAE = cAE_P;
    }

    // cria uma pessoa a partir de outra
    public Pessoa(Pessoa iD){
        super(iD);
        this.nrDAF = iD.getNrDAF();
        this.nrFAF = iD.getNrFAF();
        //ArrayToArrayList(iD.getNrFAF());
        this.cF = iD.getCF();
        this.cAE = iD.getCAE();
    }

    // getters
    public int getNrDAF(){ return this.nrDAF;}

    public List getNrFAF(){return this.nrFAF.strem().collect(Collectors.toList());}

    public float getCF(){return this.cF;}

    public String getCAE(){return this.cAE;}

    //retorna como string toda a informação fiscal disponivel da pessoa
    public String toString(){
        return super.toString() +
        "\nNúmero de dependentes do agregado Familiar : " + this.getNrDAF() +
        "\nNúmeros de fiscais do agregado Familiar : " + this.getNrFAF() +
        "\nCoeficiente Fiscal : " + this.getCF() +
        "\nCódigos das atividades ecónimas : " + this.getCAE();
    }
    
    //setters
    public void setNrDAF(int x){this.nrDAF = novo;}
    
    public void setNrFAF(List x){this.nrDAF = x.stream().collect(Collectors.toList());}
    
    public void setNrDAF(float novo){this.cF = novo;}
    
    public void setNrDAF(String novo){this.cAE = novo;}   

    /* 
    private void ArrayToArrayList(long[] b){

        this.nrFAF = new ArrayList<Long>();

        for(int i = 0; i < b.length; i++){
            this.nrFAF.add(b[i]);
        }
    }
    */

    public Pessoa clone(){
        return (new Pessoa(this));
    }

}
