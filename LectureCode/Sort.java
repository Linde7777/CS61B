import java.util.*;
public class Sort{
    public static void main(String[] args) {
        testSort();       
    }
    public static void sort(int[] arr){
        for(int i=0;i<arr.length;i++){

            int min = 9999;
            int indexOfMin = 0;
            //We need to find the min number first
            for(int j=i;j<arr.length;j++){
                if(min>arr[j]){
                    min=arr[j];
                    indexOfMin=j;
                }
            }

            //now we swap the min and the "first" element
            int temp=arr[indexOfMin];
            arr[indexOfMin]=arr[i];
            arr[i]=temp;

        }
    }

    public static void testSort(){
        int[] input=new int[]{3,2,1};
        int[] expected=new int[]{1,2,3};
        Sort.sort(input);
        org.junit.Assert.assertArrayEquals(expected,input);
    }
}