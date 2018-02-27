
/**
 * Escreva a descrição da classe Fatura aqui.
 * 
 * @author (Gonçalo Faria) 
 * @version (v1)
 */
public class Fatura
{
    //

    private Empresa servidor;
    private Entidade cliente;




    public Fatura(){
        Empresa servidor = new Empresa();
        Entidade cliente1 = new Entidade();
    }

    public Fatura( Empresa servidor , Pessoa cliente ){
        this.servidor = servidor; // recebe apontador.
        this.cliente  = client.getInfo();// recebe novo pedaço de memória. (sem pass)



    }

    public Fatura( Empresa servidor , Empresa client ){
        this.servidor = servidor; // recebe apontador.
        this.cliente  = client.getID();// recebe novo pedaço de memória. (sem pass)
    }

    
}
