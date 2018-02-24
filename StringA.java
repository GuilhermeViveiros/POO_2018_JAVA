public class StringA extends Empresa 
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String[] x;
    private int use;

    /**
     * COnstrutor para objetos da classe Inteiros
     */
    public StringA()
    {
        this.x = new String[1];
        this.use = 0; 
    }
    
    public StringA ( StringA [] v )
    {
        // inicializa variáveis de instância
        this.x = new String[v.length];
        System.arraycopy(v, 0, this.x, 0, v.length);
        this.use = v.length;
        
    }
    // copia de uma string para a original
    public StringA ( StringA aux ){
        
        String[] v = aux.getA();
        
        this.x = new String[v.length];
        System.arraycopy(v, 0, this.x, 0, v.length);

        this.use = aux.getU();
    }
    
    
    public String[] getA(){
        return this.x;
    }
    
    public int getU(){
        return this.use;
    }
    
    
    // aumenta para o dobro o tamanho
    private void tabledoubling(){
        String[] v = new String[ this.x.length * 2 ];
        System.arraycopy(this.x, 0, v, 0, this.x.length);
        this.x=v;
    }
    
    public boolean full( ){
        return this.use == this.x.length;
    }

    // mete um int no fim da lista
    public void append( String val ){
        
        if( this.full() ){
            
            this.tabledoubling();
            this.append(val);
            
        } else {
            
            this.x[this.use] = val;
            this.use++;
        
        }
        
    }
   // Acabar esta funcao que retira uns elementos proprios de StringA 
/*  
    public void rmv ( int StringA[] v) {
    
        for ( int i=0 ; i < v.length ; i++) {
            if ( elem v[i] ) 
              1_back ( this.x , i);   
        }
    }

*/

    
    
 // cria um sub-array com as strings entre from e tu   
    public String[] getSub(int from , int to){
        
        if (from >this.x.length || to >this.x.length || from<0 || to-from <= 0 ) {
            return (new String[1]);
        }
        String[] result = new String [to - from];
        
        System.arraycopy(this.x, 0, result, 0, this.x.length);
        return result;
        
    }

}
