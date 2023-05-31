public class Edge {
    public Node first;
    public Node next;
    public int state;
    public Edge(Node first,Node next){
        this.first=first;
        this.next=next;
    }
    public Edge(Node first, Node next,int state){
        this.first=first;
        this.next=next;
        this.state=state;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;//和自己
        if (o == null || getClass() != o.getClass()) return false;//一个为空 或 类型 不一样
        Edge s = (Edge) o;
        return this.first.name.equals(s.first.name) &&
                this.next.name.equals(s.next.name);
    }


    @Override
    public int hashCode() {
        if (this.first != null ){
            return this.first.name.hashCode() + this.next.name.hashCode();
        }
        return 0;
    }

    @Override
    public String toString(){
        return this.first.name+","+this.next.name+";"+this.state;
    }

}
