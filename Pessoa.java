import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pessoa{

    private Entidade info;
    private int nrDAF; // número de dependentes do agregado familiar
    private ArrayList<Long> nrFAF; // números fiscais do agregado familiar
    private float cF; // coeficiente fiscal
    private String cAE; // códigos das atividades economicas

    public Pessoa(){ // cria uma pessoa do ponto zero(DEFAULT)

        this.info = new Entidade();
        this.nrDAF = 0;
        this.nrFAF = new ArrayList<Long>();
        this.cF = 0;
        this.cAE = "Indefinido";

    }
    
    // cria uma pessoa ao receber todos os dados necessários

    public Pessoa(long ni_P, String nom_P, String mai_P, String morad_P, int nrDAF_P, long[] nrFAF_P, float cF_P, String cAE_P){

        this.info = new Entidade(ni_P, nom_P, mai_P, morad_P);
        this.nrDAF = nrDAF_P;
        ArrayToArrayList(nrFAF_P);
        this.cF = cF_P;
        this.cAE = cAE_P;
    }

    // cria uma pessoa a partir de outra

    public Pessoa(Pessoa iD){
        this.info = iD.getInfo();
        this.nrDAF = iD.getNrDAF();
        iD.getNrFAF();
        this.cF = iD.getCF();
        this.cAE = iD.getCAE();
    }

    // getters

    public int getNrDAF(){
        return this.nrDAF;
    }

    public void getNrFAF(){
        this.nrFAF.toArray();
    }

    public float getCF(){
        return this.cF;
    }

    public String getCAE(){
        return this.cAE;
    }

    public Entidade getInfo(){
        return (new Entidade(this.info));
    }

    //retorna como string toda a informação fiscal disponivel da pessoa

    public String toString(){

        return this.info.toString() +
        "\nNúmero de dependentes do agregado Familiar : " + this.getNrDAF() +
        "\nNúmeros de fiscais do agregado Familiar : " + this.nrFAF +
        "\nCoeficiente Fiscal : " + this.getCF() +
        "\nCódigos das atividades ecónimas : " + this.getCAE();
    }
    
    //setters
    
    public void setNrDAF(int novo){
        this.nrDAF = novo;
    }
    
    public void setNrFAF(long[] novo){
        ArrayToArrayList(novo);
    }
    
    public void setNrDAF(float novo){
        this.cF = novo;
    }
    
    public void setNrDAF(String novo){
        this.cAE = novo;
    }   

    private void ArrayToArrayList(long[] b){

        this.nrFAF = new ArrayList<Long>();

        for(int i = 0; i < b.length; i++){
            this.nrFAF.add(b[i]);
        }
    }
    
}
