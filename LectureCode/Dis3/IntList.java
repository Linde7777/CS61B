public class IntList {
    int first;
    IntList rest;
    public IntList(int first, IntList rest){
        this.first=first;
        this.rest=rest;
    }

    public static void removeDuplicates(IntList p){
        if(p==null){
            return;
        }
        IntList current=p;
        IntList previous=null;

        while(current!=null){
            while (previous!=null && previous.first == current.first) {
                current=current.rest;
                previous.rest=current;
            }
            previous=current;
            current=current.rest;
        }
    }
    public static void main(String[] args) {
        var list=new IntList(10, null);
        list = new IntList(3, list);
        list = new IntList(3, list);
        list = new IntList(3, list);
        list = new IntList(1, list);

        removeDuplicates(list);
        list.printList();
    }

    public void skippify(){
        IntList p=this;
        int times=2;
        while(p.rest!=null){
        IntList next = p;
            for(int i=0;i<times;i++){
                next=next.rest;
            }
            times++;
            p.rest=next;
            p=next;
        }
    }

    public void printList(){
        IntList p=this;
        while(p!=null){
            System.out.print(" "+p.first);
            p=p.rest;
        }
    }
}
