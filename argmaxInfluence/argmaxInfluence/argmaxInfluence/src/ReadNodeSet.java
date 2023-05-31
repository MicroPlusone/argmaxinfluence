import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;

public class ReadNodeSet {
    public static HashMap<Integer,Node> nodeSet=new HashMap<>();
    public static HashMap<Integer, Node> load(String path){
        LineNumberReader reader = null;
        FileReader in = null;
        try {
            in = new FileReader(path);
            reader = new LineNumberReader(in);
            while (true) {
                String str = reader.readLine();
                if (str == null)
                    break;
                Node tmp=new Node(str);
                nodeSet.put(Integer.valueOf(str),tmp);
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
        return nodeSet;
    }

}
