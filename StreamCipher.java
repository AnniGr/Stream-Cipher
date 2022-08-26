import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.util.List;

public class StreamCipher {    

    //private long keyStream = null;
    public static void main(String[] args) throws IOException{
     
        //File filename = null; 
        File inputFile = null; 
        File outputFile = null;



        if(args.length == 3){ //skickar fil som argument
       
        // filename = new File(args[0]); //encryption key
         inputFile = new File(args[1]); //inputfilen
         outputFile = new File(args[2]); //outputfilen
        } else{
            System.err.println("Need to enter three arguments: " + args.length);
            System.exit(1);
        }
           
    
//      System.out.println("The file "+ filename.getPath() + inputFile.getPath() + " was not found.");

             
        BufferedInputStream buffread = null;
        BufferedInputStream buffreadin = null;
        BufferedOutputStream buffreadout = null;
        
        try{
            
        
    

            FileInputStream infil = new FileInputStream(inputFile);
            buffreadin = new BufferedInputStream(infil);

            FileOutputStream out=new FileOutputStream(outputFile);

            buffreadout = new BufferedOutputStream(out);
          

            byte[] buffer = new byte[4096];
            int bytes = 0;

            


            try{
            long seedKey = Long.parseLong(args[0].trim()); 
            MyRandom randomised = new MyRandom(seedKey); //skapar random objekt
            int n= 0;
           
           
            while((n = buffreadin.read()) != -1){ //read input
                 // System.out.println(n);                
                    char characters = (char) n; //to character
                    int r = randomised.next(25); //generate random bytes

                    int numberscipher = characters ^ r;
                  //  System.out.println(numberscipher);
                  //  char n = (char) numberscipher;
                 //System.out.println(numberscipher + ""); 
                //    String st = String.valueOf(n);;                      
                  //  byte[] bytArr = st.getBytes();
                    buffreadout.write(numberscipher); //skriv in 
                    buffreadout.flush(); //rensar
            
              
            }  buffreadin.close();//close stream
              buffreadout.close();//close stream
            } catch(NumberFormatException ex){
                System.out.println("Not appropriate format of key:  " + args[0]);
                System.exit(1);
            }
      //      System.exit(0);
     
                 }  catch(FileNotFoundException error){
                     
            
        // if(!filename.exists()){System.err.println("The file " + filename.getPath() + " does not exist.");} //om key.txt inte existerar 
         
        if(!inputFile.exists())
            {System.err.println("The inputfile " + inputFile.getPath() + " does not exist.");} //om infile.txt inte existerar
         
            System.exit(1);
        }
    

        finally{
         try{
                if(buffread!=null)buffread.close();
                if(buffreadin!=null)buffreadin.close();
      
            }catch(IOException exception){
                exception.printStackTrace();
                System.exit(1);
            }
         } 
            System.out.println("Done");
          //  System.exit(0);
         
        }
            }
  

 
