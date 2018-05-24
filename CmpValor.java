import java.io.Serializable;
import java.util.Comparator;

public class CmpValor implements Comparator<Fatura>,Serializable
{
    @Override
    public int compare(Fatura x, Fatura y) {
        return x.comparePreco(y);
    }    
}
