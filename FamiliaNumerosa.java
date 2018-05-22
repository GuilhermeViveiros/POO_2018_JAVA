import java.io.Serializable;

/**
 * Escreva a descrição da classe FamliaNumerosa aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class FamiliaNumerosa extends Pessoa implements Reducao ,Serializable{

    public FamiliaNumerosa( Pessoa x)
    {
        super(x);
    }

    public double reducaoImposto() {
        
        try {
            return this.getAgregado().size() * 0.05;
        }catch( EmptySetException aa){
            return 0;
        }
    }

}
