package chap5;

public class ArrayDemo {
    public static void main(String[] args){
        //1.定义数组存储5个学生的年龄
        //2.定义数组存储3个学生的姓名
        //3.定义数组存储4个学生的身高
        //静态初始化
        int[] age=new int[] {22,21,23};
        String[] name={"小明","小刚","小王"};//简化写法
        double[] high={181.1,182.3,189};
        System.out.print(age);//输出的是地址值，即数组在内存中的地址，而不是数组中的元素
        /*扩展：
        地址值中，开头的【表示是数组，I表示数组类型，@为间隔符（固定格式，无含义），b4c966a为数组真正的地址值，是16进制表现形式
         */

    }
}
