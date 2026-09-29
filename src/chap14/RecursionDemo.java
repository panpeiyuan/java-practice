package chap14;

public class RecursionDemo {
    public static void main(String[] args) {
        //求阶乘
        int num = method(3);
        System.out.println("n的阶乘为:"+num);
    }
    public static int method(int n){
        if(n==1){
            return 1;
        }
        return n*method(n-1);//有方法先调方法，方法运行完毕才会返回
    }
}
