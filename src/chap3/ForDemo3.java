package chap3;

public class ForDemo3 {
    //求1-100中偶数的和
    public static void main(String[] args){
        int sum=0;
        for(int i=1;i<=100;i++){
            if(i%2==0){
                sum+=i;
            }
        }
        System.out.print(sum);
    }
}
