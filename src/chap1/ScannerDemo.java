package chap1;
import java.util.Scanner;//导包，必须写在类定义的上面
public class ScannerDemo {
    //键盘输入类练习
    public static void main(String[] args){
        //创建对象 表示我准备要用Scanner这个类了,只有sc是变量名，可以变，其余不可变
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入整数:");
        //接受键盘录入的数据
        int i = sc.nextInt();
        System.out.print(i);
    }
}
