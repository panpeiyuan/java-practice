package chap7;

public class GeneralPractice2 {//判断101-200里有几个质数
    public static void main(String[] args){
        int count=0;
        for(int i=101;i<=200;i++){
            boolean flag =true;
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;//break跳出单层循环，这里是跳出内循环
                }
            }
            if(flag){
                System.out.println(i+"是质数");
                count++;
            }
        }
        System.out.print("一共"+count+"个质数");
    }
}
