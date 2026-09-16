package chap1;

public class LogicoperatorDemo {
    public static void main(String[] args){
        //单个逻辑运算符，不管左边true还是false，右边都要再判断一次
        System.out.println(true&true);
        System.out.println(true&false);
        System.out.println(true|false);
        System.out.println(!true);

    }
}
