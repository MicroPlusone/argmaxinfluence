import java.util.*;

public class MainSolution {
    public static HashMap<Edge, Double> edgeSet;//定义哈希表成员变量edgeset，用于导入边的数据
    //还有一个配套的nodeset

    static Node argmaxInfluenceNode_mode1(HashMap<Integer,Node> V){//寻找结点u,采用最大出度准则
        Node result = null;
        int maxOutDegree=-1;
        for(int x:V.keySet()){
            if(V.get(x).outDegree>maxOutDegree){
                result=V.get(x);
                maxOutDegree=V.get(x).outDegree;
            }
        }
        return result;
    }

    static Node argmaxInfluenceNode_mode2(HashMap<Integer,Node> V){//寻找结点u,采用最大正出度准则
        Node result = null;
        int maxOutDegree=-1;
        for(int x:V.keySet()){
            if(V.get(x).positiveOutGegree>maxOutDegree){//区别于mode1,使用正出度
                result=V.get(x);
                maxOutDegree=V.get(x).positiveOutGegree;
            }
        }
        return result;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //v是节点，s是边，格式是hashmap

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入贪心选择模式：mode1 or mode2:");//mode1:节点出度最大，mode2:节点正出度最大
        String mode=in.nextLine();
        System.out.print("请输入k值:");//获得规模为k的节点集合
        int k=in.nextInt();
        System.out.print("请输入运行次数:");
        int runTimes=in.nextInt();
        double sumInfluence=0.0;//总影响力大小
        double sumTimes=0.0;
        LinkedList<Node> final_S = new LinkedList<>();//链表final_s
        for (int t=0;t<runTimes;t++){
            int influence=0;//单次运行影响力
            long times=0;
            HashMap<Integer, Node> nodeSet=ReadNodeSet.load("argmaxInfluence/src/alluserlist.txt");
            //结点是alluser，格式是integer，node
            //node和edge是自定义格式
            edgeSet=ReadEdgeSet.load("argmaxInfluence/src/links.txt",nodeSet);
            //边是links,格式为Edge, Double。
            LinkedList<Node> S=new LinkedList<>();//链表s
            HashMap<Integer,Node> V= (HashMap) nodeSet.clone();//初始状态节点均为未激活，属于V
            //深拷贝（克隆）nodeset里的数据
            LinkedList<Node> activeList=new LinkedList<>();//某时刻活性结点列表
            long before = System.currentTimeMillis();//计时，便于性能分析
            for(int i=0;i<k;i++){//k是节点的规模
                Node selectedNode;
                if(mode.equals("mode1")){
                    selectedNode= argmaxInfluenceNode_mode1(V);//启发式搜索,选择出度最大的结点
                }
                else
                    selectedNode= argmaxInfluenceNode_mode2(V);//启发式搜索,选择正出度最大的结点
                //结点状态：0代表未激活，1代表已正向激活，-1代表已负向激活，tmp1代表当前正向激活，tmp-1代表当前负向激活
                selectedNode.state=1;//被激活之后的标记
                V.remove(Integer.valueOf(selectedNode.name));//移出V
                //删除指定key对应的键值对儿～
                S.offer(selectedNode);
                //防止加入新值后unchecked异常，防止满了
                influence++;
                activeList.offer(selectedNode);//加入当前活性结点列表
                LinkedList<Node> newAdd=new LinkedList<>();
                while(!activeList.isEmpty()){
                    //System.out.println(activeList);
                    for(Node x:activeList){
                        Vector<Node> neighbors= (Vector<Node>) x.next.clone();//next只读空格之前的数据
                        //System.out.println(x+".nbr"+neighbors);
                        for(Node neighbor:neighbors){
                            if(neighbor.state!=1){
                                Edge tmp=new Edge(x,neighbor);
                                double det=Math.random();//引入随机衰减
                                //double det=1;
                                neighbor.p*=((1- det*edgeSet.get(tmp)));
                            }
                        }
                        for(Node neighbor:neighbors){
                            if((neighbor.p*neighbor.p*neighbor.p)<0.5&&neighbor.state==0){//p^3<0.5时，更新该节点
                                neighbor.state=1;//进行标记
                                Node add=nodeSet.get(Integer.valueOf(neighbor.name));
                                add.state=1;
                                newAdd.offer(add);
                                influence++;
                            }
                            neighbor.p=1;//回溯
                        }
                    }
                    activeList.clear();
                    activeList.addAll(newAdd);
                    newAdd.clear();
                }
            }
            long later = System.currentTimeMillis();
            //System.out.println("本次正向影响力:"+influence);
            times=later-before;
            //System.out.println("本次runTime:"+times);
            sumInfluence+=influence;
            sumTimes+=times;
            final_S= (LinkedList<Node>) S.clone();
        }
        System.out.println("======= Final Result ======");
        System.out.println(" 总运行时间:        "+sumTimes);
        System.out.println(" 平均正向影响力:     "+Math.ceil(sumInfluence/runTimes));//runtimes是运行次数，最后取平均值
        //math的ceil是取小于该值的最大整型数值（小数点后是0）
        for(int i=1;i<=k;i++){
            Node a=final_S.poll();//final_s是边的结果（先进行了一次深拷贝）
            if(i==10)//输出美观
                System.out.println(" selectedNode-"+i+": "+a.name);
            else
                System.out.println(" selectedNode-"+i+":  "+a.name);
        }
        System.out.println("===========================");
    }
}