package hw3.hash;

public class Test {
    public static int hashCode(int[] array) {
        int total = 0;
        for (int element : array) {
            total = total * 256;
            total = total + element;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] array1 = {80, 103, 142, 91, 160, 250, 1, 2, 3, 4};
        int[] array2 = {130, 105, 209, 1, 2, 3, 4};
        /*
        这个hash很容易溢出，256是2的8次方，数组后四位分别对应2的24次方，
        2的16次方，2的8次方，2的0次方，符号数整数溢出会减去2的32次方，
        因为这个数会重复溢出，最后的结果就是减多次2的32次方，
        而其他位都比2的32次方大，最后减剩下的就是最后4位的结果
         */
        int hashCode1 = hashCode(array1);
        int hashCode2 = hashCode(array2);
        System.out.println("hashCode1: " + hashCode1);//117901063
        System.out.println("hashCode2: " + hashCode2);//117901063

    }
}
