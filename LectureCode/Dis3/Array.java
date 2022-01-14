package Dis3;
public class Array {
    public static void main(String[] args) {
        int[] arr=new int[]{3,2,1};
        int[] newArr=replicate(arr);
        for(int elem:newArr){
            System.out.print(" "+elem);
        }
        
    }

    public static int[] flatten(int[][] x){
        int totalLength=0;
        for(int i=0;i<x.length;i++){
            totalLength+=x[i].length;
        }

        int[] a=new int[totalLength];
        int aIndex=0;

        for(int i=0;i<x.length;i++){
            for(int j=0;j<x[i].length;j++){
                a[aIndex]=x[i][j];
                aIndex++;
            }
        }

        return a;
    }

    public static void reverse(int[] arr){
        int len=arr.length;
        for(int i=0;i<len/2;i++){
            int temp;
            temp=arr[i];
            arr[i]=arr[len-1-i];
            arr[len-1-i]=temp;
        }

    }

    public static int[] replicate(int[] arr){
        int newArrSize=0;
        for(int i=0;i<arr.length;i++){
            newArrSize+=arr[i];
        }
        int[] newArr=new int[newArrSize];

        /*
        newArr[0]=arr[0];
        newArr[1]=arr[0];
        newArr[2]=arr[0];

        newArr[3]=2;
        newArr[4]=2;

        newArr[5]=1;
        */
        
        int index=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i];j++){
                newArr[index] = arr[i];
                index++;
            }
        }

        return newArr;
    }


}
