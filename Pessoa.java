import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Pessoa extends Entidade{

   
    private int nrDAF; // número de dependentes do agregado familiar
    private ArrayList<Long> nrFAF; // números fiscais do agregado familiar
    private float cF; // coeficiente fiscal
    private String cAE; // códigos das atividades economicas

    public Pessoa(){ // cria uma pessoa do ponto zero(DEFAULT)
        
        super();
        this.nrDAF = 0;
        this.nrFAF = new ArrayList<Long>();
        this.cF = 0;
        this.cAE = "Indefinido";

    }
    
    // cria uma pessoa ao receber todos os dados necessários

    public Pessoa(long nif, String nome, String mail, String morada, int nrDAF_P, ArrayList<Long> nrFAF_P, float cF_P, String cAE_P, String number){
        
        super(nif, nome, mail, morada, number);
        this.nrDAF = nrDAF_P;
        this.nrFAF = new ArrayList<Long>();
        for(long s : nrFAF_P){
            this.nrFAF.add(s);
        }
        this.cF = cF_P;
        this.cAE = cAE_P;
    }

    // cria uma pessoa a partir de outra

    public Pessoa(Pessoa iD){
        super(iD.getNif(), iD.getNome(), iD.getMail(),iD.getMorada(), iD.getTelefone());
        this.nrDAF = iD.getNrDAF();
        this.nrFAF = iD.getNrFAF();
        //ArrayToArrayList(iD.getNrFAF());
        this.cF = iD.getCF();
        this.cAE = iD.getCAE();
    }

    // getters

    public int getNrDAF(){
        return this.nrDAF;
    }

    /*
    public long[] getNrFAF(){
        Long[] l= this.nrFAF.toArray(new Long[0]);
        long[] r= new long[l.length];
        for(int i=0; i< l.length; i++)
            r[i]= l[i].longValue();
        return r;
    }
    */

    public ArrayList getNrFAF(){
        
        ArrayList<Long> nr = new ArrayList();
        for(long l : this.nrFAF){
            nr.add(l);
        }
        return nr;
    }

    public float getCF(){
        return this.cF;
    }

    public String getCAE(){
        return this.cAE;
    }

    //retorna como string toda a informação fiscal disponivel da pessoa
    public String toString(){

        return super.toString() +
        "\nNúmero de dependentes do agregado Familiar : " + this.getNrDAF() +
        "\nNúmeros de fiscais do agregado Familiar : " + this.getNrFAF() +
        "\nCoeficiente Fiscal : " + this.getCF() +
        "\nCódigos das atividades ecónimas : " + this.getCAE();
    }
    
    //setters
    
    public void setNrDAF(int novo){
        this.nrDAF = novo;
    }
    
    public void setNrFAF(ArrayList novo){
        
    }
    
    public void setNrDAF(float novo){
        this.cF = novo;
    }
    
    public void setNrDAF(String novo){
        this.cAE = novo;
    }   

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
