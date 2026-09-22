package chap8;

import java.util.Random;
import java.util.Scanner;

public class GeneralPractice9 {
    //1.随机生成中奖号码2.用户输入中奖号码3.判断中奖情况
    public static void main(String[] args){
        int[] arr=createarr();
        System.out.println("======================");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("======================");
        int[] userinputarr=userinput();
        int redcount=0;
        int bluecount=0;
        for (int i = 0; i < arr.length-1; i++) {
            int rednumber=userinputarr[i];
            for (int j = 0; j < arr.length-1; j++) {
                if(rednumber==arr[j]){
                    redcount++;
                    break;//有匹配的之后，直接判断下一个
                }
            }
        }
        if(userinputarr[6]==arr[6]){
            bluecount++;
        }
        System.out.println(redcount);
        System.out.println(bluecount);
    }
    public static int[] createarr() {
        int[] arr = new int[7];
        Random r = new Random();
        int bulenumber=r.nextInt(16)+1;
        for (int i = 0; i <= 6; ) {
            //获取红球号码
            int rednumber = r.nextInt(33) + 1;
            boolean flag = contains(arr, rednumber);
            if (flag) {
                arr[i] = rednumber;
                i++;
            }
        }
        arr[6]=bulenumber;
        return arr;
    }
    public static boolean contains(int[] arr,int num){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=num){
                return true;
            }
        }
        return false;
    }
    public static int[] userinput(){
        int[] arr=new int[7];
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<arr.length-1;){
            System.out.println("请输入第"+(i+1)+"个红球号码");
            int rednumber=sc.nextInt();
            if(rednumber>=1&&rednumber<=33){
                boolean flag=contains(arr,rednumber);
                if(flag){
                    arr[i]=rednumber;
                    i++;
                }
            }else{
                System.out.println("超出范围");
            }
        }
        System.out.println("请输入篮球号码");
        while (true) {
            int bulenumber=sc.nextInt();
            if(bulenumber>=1&&bulenumber<=16){
                arr[6]=bulenumber;
                break;
            }else{
                System.out.println("请输入正确的篮球数字");
            }
        }
        return arr;
    }

}
