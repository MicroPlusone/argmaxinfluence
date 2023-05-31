import java.util.Vector;

public class Node implements Cloneable{
    public Vector<Node> next=new Vector<>();
    public Vector<Node> prev=new Vector<>();
    //结点状态：0代表未激活，1代表已正向激活，-1代表已负向激活，tmp1代表当前正向激活，tmp-1代表当前负向激活
    public int state=0;
    public String name;
    public double p=1.0;
    public int outDegree;
    public int positiveOutGegree;
    public Node(){}


    public Node(String name){
        this.name=name;
    }
    @Override
    public String toString(){
        return this.name+".state= "+this.state;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
