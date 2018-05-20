import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class EmpresaInterior extends Empresa implements Serializable, Reducao {
    static Map<String, Double> conselhos;

    static {
        conselhos = new HashMap<String, Double>();

        conselhos.put("Covilha", new Double(0.1));
        conselhos.put("Guarda", new Double(0.8));
        conselhos.put("Fundao", new Double(0.14));
    }

    private String conselho;

    public EmpresaInterior() {
        super();
        this.conselho = "nenhum";
    }

    public EmpresaInterior(Contacto ct, String password, Set<Atividade> areas, String cons) {
        super(ct, password, areas);

        if (EmpresaInterior.conselhos.containsKey(cons))
            this.conselho = cons;
        else
            this.conselho = "nenhum";
    }

    public EmpresaInterior(EmpresaInterior a) {
        super(a);
        try{
            this.conselho = a.getConselho();
        } catch (InvalidFieldException b){
            this.conselho = "nenhum";
        }
    }

    public double reducaoImposto() {
        if (this.conselho.equals("nenhum")) {
            return 0.0;
        }


        Double x = EmpresaInterior.conselhos.get(this.conselho);
        return x.doubleValue();
    }

    private String getConselho() throws InvalidFieldException {

        if (this.conselho.equals("nenhum")) {
            throw new InvalidFieldException(" Consenlho não foi indicado");
        }

        return this.conselho;
    }

    public void setConselho(String cons) {
        if (EmpresaInterior.conselhos.containsKey(cons))
            this.conselho = cons;
        else
            this.conselho = "nenhum";
    }

    public double calculoDeducao(LocalDate begin, LocalDate end){
        
        try {
            return this.getAreas().stream().
                    mapToDouble( l -> l.regraCalculo(this, begin, end) ).
                        sum() * (1 - this.reducaoImposto());
        }
        catch ( EmptySetException aa){
            return 0.0;
        }
    }
}
