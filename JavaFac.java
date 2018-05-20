import java.util.stream.Collectors;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class JavaFac implements Serializable {
    private Map<Long, Entidade> contribuintes;
    private Map<Long, Empresa> empresas;
    private String adminstrador;

    public JavaFac() {
        this.contribuintes = new HashMap<Long, Entidade>();
        this.empresas = new HashMap<Long, Empresa>();
        this.adminstrador = "@@invalid";
    }

    public JavaFac(Collection<Entidade> x, String admin) {
        this.contribuintes = new HashMap<Long, Entidade>();
        this.empresas = new HashMap<Long, Empresa>();

        for (Entidade e : x) {
            try {
                
                Entidade k = e.clone();
                this.contribuintes.put(e.getContacto().getNif(), e.clone());
                
                if( e instanceof Empresa){
                    this.empresas.put(e.getContacto().getNif(), (Empresa)e.clone());
                }
                
            } catch (InvalidFieldException a) {
                continue;
            }
        }
        this.adminstrador = admin;
    }

    public JavaFac(JavaFac o) {
        
        this.contribuintes = new HashMap<Long, Entidade>();
        
        try {
            for( Entidade e : o.getConjuntoContribuintes()){
                try{
                    this.contribuintes.put( e.getContacto().getNif(), e );
                } catch (InvalidFieldException aaA){
                   continue;
                }
            }
            
        } catch (EmptySetException a) {
            this.contribuintes = new HashMap<Long, Entidade>();
        }

        try {
            this.adminstrador = o.getAdminPassword();
        } catch (InvalidFieldException aa) {
            this.adminstrador = "@@invalid";
        }
        this.extractEmpresas();
    }

    private void extractEmpresas(){
        this.empresas = new HashMap<Long,Empresa>();

        for( Map.Entry<Long,Entidade> e : this.contribuintes.entrySet() ){

            if( e.getValue() instanceof Empresa){
                 this.empresas.put(e.getKey(), (Empresa)e.getValue());
                
            }
        }
    }

    public void checkAdminPassword(String admin) throws IncorrectPasswordException {
        if (admin.equals(this.adminstrador)) {
            throw new IncorrectPasswordException(" Palavra-passe de admnistrador errada ");
        }
    }

    public String getAdminPassword() throws InvalidFieldException {
        if (this.adminstrador.equals("@@invalid")) {
            throw new InvalidFieldException(" Ainda não foi indicada uma password para o Admistrador ");
        }
        return this.adminstrador;
    }

    public void setAdminPassword(String password) {
        this.adminstrador = password;
    }

    public Set<Entidade> getConjuntoContribuintes() throws EmptySetException {
        if (this.contribuintes.size() == 0) {
            throw new EmptySetException(" O conjunto está Vazio ");
        }
        return this.contribuintes.values().stream().map(Entidade::clone).collect(Collectors.toSet());
    }

    public Entidade getContribuinte(Long nif, String password)
            throws IncorrectPasswordException,NonExistentEntityException, InvalidFieldException{
        if (!this.contribuintes.containsKey(nif))
            throw new NonExistentEntityException(" Esse nif não se encontra na base de dados");

        Entidade x = this.contribuintes.get(nif);
        
        if (!x.getPassword().equals(password)) {
            throw new IncorrectPasswordException(" A Palavra-passe indica está errada");
        }

        return x.clone();
    }

    public void addContribuinte(Entidade x, String password) throws IncorrectPasswordException, InvalidFieldException {
        if (this.contribuintes.containsValue(x)) {
            Entidade e = this.contribuintes.get(x.getContacto().getNif());

            if (!e.getPassword().equals(password))
                throw new IncorrectPasswordException(" A Palavra-passe indica está errado ");

            this.contribuintes.put(x.getContacto().getNif(), x.clone());

        } else {
            this.contribuintes.put(x.getContacto().getNif(), x.clone());
        }
    }

    public Collection<Entidade> maisGasta(){

        PriorityQueue<Entidade> pq = new PriorityQueue<Entidade>(new Comparator<Entidade>() {
            public int compare(Entidade x, Entidade y) {
                return (int)(x.getDespesa() - y.getDespesa());
            }
        });

        for( Entidade o  : this.contribuintes.values()){
            if( pq.size()< 10 ){
                pq.add(o);
            }
            else{
                Empresa x = (Empresa)pq.peek();
                if ( x.getDespesa() < o.getDespesa() ){
                    pq.poll();
                    pq.add(o);
                }
            }
        }

        return pq.stream().map(Entidade::clone).collect(Collectors.toSet());
    }

    public Collection<Empresa> maisFaturam(int n){
        PriorityQueue<Empresa> pq = new PriorityQueue<Empresa>(new Comparator<Empresa>() {
            public int compare(Empresa x, Empresa y) {
                return (int)(x.totalFaturado() - y.totalFaturado());
            }
        });

        for( Empresa o  : this.empresas.values()){
            if( pq.size()< n ){
                pq.add(o);
            }
            else{
                Empresa x = (Empresa)pq.peek();
                if ( x.totalFaturado() < o.totalFaturado() ){
                    pq.poll();
                    pq.add(o);
                }
            }
        }

        return pq.stream().map(Empresa::clone).collect(Collectors.toSet());
    }

    public double deducaoMaisFaturam( int n ,LocalDate begin, LocalDate end){
        return this.maisFaturam(n).stream().mapToDouble(l -> l.calculoDeducao(begin,end)).sum();
    }

    public void gravarEstado(String filename) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
        oout.writeObject(this);
        oout.flush();
        oout.close();
    }

    static JavaFac recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        JavaFac fact = (JavaFac) ois.readObject();
        ois.close();
        return fact;
    }
}
