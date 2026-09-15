package chap1;

public class ArithmeticOperationDemo3 {
    public static void main(String[] args){
        int a=10;
        int b=a++;//先用后加
        System.out.println(b);
        int c=10;
        int d=++c;//先加后用
        System.out.print(c);
    }
}
