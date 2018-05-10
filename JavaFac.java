import java.util.stream.Collectors;
import java.util.Colections.Set;

public class JavaFac
{
    private Set<Entidade> Contribuintes;
   
    public JavaFac(){    
        this.Contribuintes = new HashSet<Entidade>(); 
    }

    // load

    public JavaFac( JavaFac o ){
        this.Contribuintes = o.getContribuintes();
    }

    public JavaFac( String filename ){
        
    }
    
    public Set<Entidade> getContribuintes(){
        return this.Contribuintes.stream().map(Entidade::clone).collect( Collectors.toSet()  );
    }


}

