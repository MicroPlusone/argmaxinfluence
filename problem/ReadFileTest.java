import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Vector;

public class ReadFileTest{

	
	Vector vec = new Vector();
	
	
   public void load(String path){
    	  	
    	LineNumberReader reader = null;   
    	FileReader in = null; 
    	String key = null;
    	int value = 0;
    	String [] pairs = new String[4];
    	     
    	try {   
    	      in = new FileReader(path);   
    	      reader = new LineNumberReader(in);   
    	      while (true) {
    	        	 String str = reader.readLine();   
    	             if (str == null)   
    	                  break;   
    	              System.out.println(str);
    	              pairs= str.split(" ");
    	              String strLine = pairs[0] + " "+pairs[1]+" "+pairs[2]+" "+pairs[3];
    	              vec.add(strLine);

    	      }   

    	      } catch (FileNotFoundException e) {   
    	          e.printStackTrace();   
    	      } catch (IOException e) {   
    	          e.printStackTrace();   
    	      } finally {   
    	          try {   
    	              if (reader != null)   
    	                  reader.close();   
    	          } catch (IOException e) {   
    	              e.printStackTrace();   
    	          }   
    	          try {   
    	              if (in != null)   
    	                  in.close();   
    	          } catch (IOException e) {   
    	              e.printStackTrace();   
    	          }   
    	      } 
    	 	  
    	
    }
   
   
   public static void main(String args[]){
	   
	   
	   ReadFileTest test = new ReadFileTest();
	   test.load(".\\files\\links.txt");
	   int count = 0;
	   
	   for(int i=0;i<test.vec.size();i++){
		   
		   System.out.println(test.vec.get(i).toString());
		   count++;
		   
	   }
	   
	   System.out.println("link number:"+count);
	     
	   
   }
   
   

}