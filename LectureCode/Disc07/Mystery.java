package Disc07;

public class Mystery {
    public static void main(String[] args) {
        int[] array=new int[]{1,2,1,2};
        System.out.println(mystery(array));
    }

    public static boolean mystery(int[] array) {
        int N = array.length;
        for (int i = 0; i < N; i += 1) {
            boolean x = false;
            for (int j = 0; j < N; j += 1) {
                if (i != j && array[i] == array[j])
                    x = true;
            }
            if (!x) {
                return false;
            }
        }
        return true;
    }
}
