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

//ska kunna skicka in vilken fil som helst



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
            
        
        
         

      /*     
            FileInputStream file = new FileInputStream(filename);
            buffread = new BufferedInputStream(file);
*/
           /* try{buffread.read();}catch(FileNotFoundException ex){
                System.exit(1);
                System.err.println(""+ filename + "was not found");
            }
            */


            FileInputStream infil = new FileInputStream(inputFile);
            buffreadin = new BufferedInputStream(infil);

            FileOutputStream out=new FileOutputStream(outputFile);

            buffreadout = new BufferedOutputStream(out);
          

            byte[] buffer = new byte[4096];
            int bytes = 0;

            
         
        /*    while((bytes = buffread.read(buffer)) != -1){  //läser
                  key = new String(buffer,0, bytes);
           //     System.out.println(key); skriv ut key
            }
        */


            try{
            long seedKey = Long.parseLong(args[0].trim()); 
            MyRandom randomised = new MyRandom(seedKey); //skapar random objekt
            int n= 0;
           
           
            while((n = buffreadin.read()) != -1){ //läs inputfilen
                 // System.out.println(n);                
                    char characters = (char) n; //gör om till character
                    int r = randomised.next(25); //generate random bytes

                    int numberscipher = characters ^ r;
                  //  System.out.println(numberscipher);
                  //  char n = (char) numberscipher;
                 //System.out.println(numberscipher + ""); 
                //    String st = String.valueOf(n);;                      
                  //  byte[] bytArr = st.getBytes();
                    buffreadout.write(numberscipher); //skriv in 
                    buffreadout.flush(); //rensar
            
              
            }  buffreadin.close();//stänger stream
              buffreadout.close();//stänger stream
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
  

    
/*
    public String encode(String inputfiletext, String seed) {
        return base64Encode(xor(inputfiletext.getBytes(), seed.getBytes()));
    }

    private byte[] xor(byte[] arr, byte[] seed){
        byte[] output = new byte[arr.length];
        for(int k = 0; k<arr.length; k++){
            output[k] = (byte) (arr[k] ^ seed[k%seed.length]);
        }
        return output;
    }
    private String base64Encode(byte[] bytes){
        BASE64Encoder encrypt = new BASE64Encoder();
        return encrypt.encode(bytes).replaceAll("\\s", "");
    }


  /* public static String string_Binary(int number){

        String string = "";
        while(number>0){
            string = ((number%2) == 0 ? "0" : "1") + string;
            number = number/2;

        }
        return string;

    }
    */ 


     /*   File filename = null; //läser fil
        
        if(0<args.length){
         filename = new File(args[0]);
        } 

        BufferedReader buffread = null;

        try{
            String line;

            buffread = new BufferedReader(new FileReader(filename));

            while((line = buffread.readLine()) !=null){  //läser

                System.out.println(line);
                long seedKey = Long.parseLong(line); //gör om från string till long
                Random randomised = new Random(seedKey);
                int key = randomised.nextInt(256);
                System.out.println(key); //skriv ut det nya random 
            }
            

            //    System.out.println("myRandom");
        
        }

        catch(IOException error){
            error.printStackTrace();
        }

        finally{
            try{
                if(buffread!=null)buffread.close();
            }catch(IOException exception){
                exception.printStackTrace();
            }
        }   
}    }

*/