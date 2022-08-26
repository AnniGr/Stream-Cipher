
import java.util.*;

import java.util.concurrent.atomic.AtomicLong;



public class MyRandom extends StreamCipher{

     final static int a = 16807;
     private final AtomicLong seed = new AtomicLong(8682522807148012L);
     final static int m = 2147483647;
     final static int b = 0;
     long s;
   // long x ; //nuvarande värde som ska returneras
    


   public MyRandom(){
       //creates a new number generator
       setSeed(System.nanoTime());//generate random seed by using the time
    }

    public MyRandom(long seed){
               //creates a new number generator using a single long seed
         setSeed(seed);
          //  ++seed;
        }

    int next(int bits){

        if (bits <0){ //kan inte vara negativt
        throw new IllegalArgumentException("Must be positive value of bits");

    }
    
    if(bits> 31){
   //    MyRandom rd = new MyRandom();
     int t = (int) System.nanoTime();
     if(t>0){
     return (t);
     }
     
    }

    /*
    The PRNG is defined by D.H. Lehmer and described in the book The Art of Computer Programming
    , volume 3: Seminumerical Algorithms, section 3.2.1.
    */
    long old, next;
           AtomicLong seed = this.seed;
           do{
               old = seed.get();
               next =(old*a+b)%m;
           }while(!seed.compareAndSet(old, next));
           
        
        return (int) ((next)  * -1);   //from https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
        
    }


    void setSeed(long seed){

     this.s = (seed * 0x7FFFFFFF ) & ((1L << 31) - 1);
    // long s= (seed ^ 0X7fffffff) & ((1L << 31) - 1);    //from https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    
    }

}