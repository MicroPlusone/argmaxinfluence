import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

public class ReadEdgeSet {
	public static HashMap<Edge,Double> vec = new HashMap<>();
    public static HashMap<Edge, Double> load(String path, HashMap<Integer, Node> nodeset){
    	LineNumberReader reader = null;
    	FileReader in = null;
    	String [] pairs = new String[4];
    	try {
    	      in = new FileReader(path);
    	      reader = new LineNumberReader(in);//reader是文件的行数
    	      while (true) {
				  String str = reader.readLine();
				  if (str == null)
					  break;
				  //System.out.println(str);
				  pairs= str.split(" ");
				  String strLine = pairs[0] + " "+pairs[1]+" "+pairs[2]+" "+pairs[3];
				  nodeset.get(Integer.valueOf(pairs[0])).next.add(new Node(pairs[1]));//结点next更新
				  nodeset.get(Integer.valueOf(pairs[1])).prev.add(new Node(pairs[0]));//结点prev更新
				  if(pairs[2].equals("1")){
					 nodeset.get(Integer.valueOf(pairs[0])).positiveOutGegree++;//正出度++
				  }
				  nodeset.get(Integer.valueOf(pairs[0])).outDegree++;//出度++
				  if(pairs[2].equals("1"))
					  vec.put(new Edge(new Node(pairs[0]),new Node(pairs[1]),Integer.parseInt(pairs[2])), Double.valueOf(pairs[3]));
				  else
					  vec.put(new Edge(new Node(pairs[0]),new Node(pairs[1]),Integer.parseInt(pairs[2])), -Double.parseDouble(pairs[3]));
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
		return vec;
    }

}