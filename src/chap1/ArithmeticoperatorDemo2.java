package chap1;

public class ArithmeticoperatorDemo2 {
    public static void main(String[] args){
        byte b1=100;
        byte b2=100;//byte，short，char运算都是先将类型转换为int然后运算
        byte result=(byte)(b1+b2);//对b1+b2结果整体进行强转
        System.out.println(result);//数值超范围后运算结果回出错
    }
}
