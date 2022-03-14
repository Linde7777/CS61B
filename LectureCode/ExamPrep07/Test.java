package ExamPrep07;

public class Test {
    public static void main(String[] args) {
        p3(8);
    }
    public static void p3(int N){
        if(N<=1){
            return;
        }
        p3(N/2);
        p3(N/2);
    }
}
