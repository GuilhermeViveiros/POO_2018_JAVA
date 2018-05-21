import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class EmpresaInterior extends Empresa implements Serializable, Reducao {
    static Map<String, Double> conselhos;

    static {
        EmpresaInterior.conselhos = new HashMap<String, Double>();
        EmpresaInterior.conselhos.put("Covilha", new Double(0.1));
        EmpresaInterior.conselhos.put("Guarda", new Double(0.8));
        EmpresaInterior.conselhos.put("Fundao", new Double(0.14));
    }

    static void addCidade( String  cidade ,double desconto ){
        EmpresaInterior.conselhos.put(cidade ,new Double(desconto));
    }

    static void recover() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("EmpresaInteriorvar.var");
        ObjectInputStream ois = new ObjectInputStream(fis);
        EmpresaInterior.conselhos = (Map<String, Double>) ois.readObject();
        ois.close();
    }

    static void save() throws IOException{
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("EmpresaInteriorvar.var"));
        oout.writeObject(EmpresaInterior.conselhos);
        oout.flush();
        oout.close();
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
        try {
            this.conselho = a.getConselho();
        } catch (InvalidFieldException b) {
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

    @Override
    public double calculoDeducao(LocalDate begin, LocalDate end) {

        try {
            return this.getAreas().stream().mapToDouble(l -> l.regraCalculo(this, begin, end)).sum()
                    * (1 - this.reducaoImposto());
        } catch (EmptySetException aa) {
            return 0.0;
        }
    }
}
