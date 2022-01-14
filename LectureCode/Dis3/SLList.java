public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void printAll(){
        var p=this.first;
        while(p!=null){
            System.out.print(" "+p.item);
            p=p.next;
        }
    }

    public void insert(int item, int position){
        var p=this.first;
        var newNode=new IntNode(item, null);

        for(int i=0;i<position-1;i++){
            p=p.next;

            if(p==null){
                System.out.println("Position is larger than the length");
                return;
            }
        }
        //now p is located at (position - 1)

        if(p.next!=null){
            newNode.next=p.next;
            p.next=newNode;
        }else{
            newNode.next=p;
        }
    }

    public void reverse(){
        IntNode prev=null;
        IntNode curr=this.first;
        IntNode currNext=curr.next;

        while(curr!=null){
            curr.next=prev;
            prev=curr;
            curr=currNext;
            currNext=currNext.next;
        }
        //return prev;
    }
    public static void main(String[] args) {
        var list=new SLList();
        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(5);
        //list.insert(99, 2);
        list.printAll();
        list.reverse();
        list.printAll();
        //var node=new list.IntNode();
    }
}