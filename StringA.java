public class StringA
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

    // copia de uma string para a original
    public StringA ( StringA aux ){
        
        String[] v = aux.getA();
        
        this.x = new String[v.length];
        System.arraycopy(v, 0, this.x, 0, v.length);

        this.use = aux.getU();
    }
    
    //
    public String[] getA(){   
        return Arrays.copOfRange(this.x , 0 , this.use );
    }
    //
    public int getU(){
        return this.use;
    }
    
    // aumenta para o dobro o tamanho
    public void tabledoubling(){
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
            this.x[this.use++] = val;        
        }
        
    }
   // Acabar esta funcao que retira uns elementos proprios de StringA 
 
    public boolean elem ( String b) {
        for ( int i=0; i < this.use ; i++) {
            if ( b == this.x[i] ) return true;
        }
        return false;
    }

    public boolean remove ( String v) {
        
        String tmp ;
        for ( int i=0 ; i < this.x.use ; i++) {
            if ( this.x[i] == v ) {
                
                tmp = this.x[i];
                this.x[i]=this.x[--this.use];
                this.x[this.use]= tmp;

                return true;
            }
        }
        return false;
    }
    
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
