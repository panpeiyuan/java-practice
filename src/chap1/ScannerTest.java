package chap1;
import java.util.Scanner;
public class ScannerTest {
    //键盘输入类测试
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        System.out.print("请输入第一个整数");
        int num1=sc.nextInt();
        System.out.print("请输入第二个整数");
        int num2=sc.nextInt();
        System.out.println("他们的和为"+(num1+num2));
    }
}
