package chap12ObjectOrientedGeneralPractice;

import java.util.Scanner;

public class CarsTest {
    public static void main(String[] args){
        //键盘录入
        //第一套体系
        //nextInt（）：接受整数
        //nextDOuble（）：接受小数
        //next（）：接受字符串
        //这些方法在遇到空格、制表符、回车就停止接收，这些符号后面的数据就不会接受了
        //第二套体系
        //nextLine（）：接受字符串
        //可以接受空格、制表符，遇到回车才停止接受数据
        Cars[] arr=new Cars[3];
        Scanner s=new Scanner(System.in);
        System.out.println("请依次输入汽车的品牌，价格，颜色");
        Cars r1=new Cars(s.next(),s.next(),s.next());
        System.out.println("请依次输入汽车的品牌，价格，颜色");
        Cars r2=new Cars(s.next(),s.next(),s.next());
        System.out.println("请依次输入汽车的品牌，价格，颜色");
        Cars r3=new Cars(s.next(),s.next(),s.next());
        arr[0]=r1;
        arr[1]=r2;
        arr[2]=r3;
        for (int i = 0; i < arr.length; i++) {
            Cars carrole=arr[i];
            System.out.println(carrole.getBrand()+","+carrole.getPrice()+","+carrole.getColour());
        }

    }
}
