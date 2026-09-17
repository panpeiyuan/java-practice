package chap3;

public class ForDemo2 {
    public static void main(String[] args){
        int s=0;//变量定义的位置，决定他的作用范围，定义在main大括号下，就能在整个main中运用
        for(int i=1;i<=100;i++){
            //若把变量定义在for循环的大括号内，则只能在for循环内部使用，在for循环外再调用该变量进行运算或输出会报错
            //若将变量定义在循环内部，则只能在本次循环中使用，第二次循环开始时又会重新定义一个新的变量
            s+=i;
        }
        System.out.print(s);
    }
}
