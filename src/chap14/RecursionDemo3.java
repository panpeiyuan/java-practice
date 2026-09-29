package chap14;

public class RecursionDemo3 {
    //递归输出测试
    public static void method(int n){
        if(n>1){
            method(n-1);
        }
        System.out.println("n="+n);
    }
    public static void main(String[] args){
        method(5);
    }
}
