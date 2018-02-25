import java.time.LocalDate;

//

public class data
{
    private LocalDate[] arrays;
    private int used;
    
    public data() {
        this.arrays = new LocalDate[1];
        this.used = 0;
        
    }
    
    public data ( LocalDate[] data , int x) {
         this.arrays = new LocalDate[data.length];
         this.used = x;
        
    }
    
    public data ( data x) {
           this.arrays = x.getArray();
           this.used = x.getUsed();
        
    }
    
    public data check_full (data a ) {
        data novo;
        int x;
        if ( this.used >= this.arrays.length){
         x = (this.arrays.length)*2;   
        novo = new data();
        novo.setUsed(this.used);
        novo.setArray ( new LocalDate[x] );
        System.arraycopy ( this.arrays , 0 , novo.arrays , 0 , this.used ) ;
        return novo;
       }
        
        else return a;
    }
    
    public void insereData(LocalDate data){

        if ( this.used >= this.arrays.length) {
            
            
            
        }
        
    }
    
    public void setArray (  LocalDate[] x) {
        this.arrays = x;
    }
    
    public LocalDate[] getArray(){
        return this.arrays;   
    }
    
    public int getUsed () {
        return this.used;
    }
    
    public void setUsed ( int x) {
        
       this.used = x;
    }

}

