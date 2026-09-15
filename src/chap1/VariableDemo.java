package chap1;

public class VariableDemo {
    //变量练习一
    public static void main(String[] args) {
        //整数
        System.out.println(666);
        System.out.println(-777);
        //小数
        System.out.println(1.93);
        //字符
        System.out.println('男');
        //空 null只能以字符串的形式打印
        System.out.println("null");
        //字符串
        System.out.println("aaaa");
        // \t制表符，在打印时把字符串长度补齐到4，或4的整数倍，最少补一个空格，最多补4个空格
        System.out.println("abc"+'\t');//补1个空格
        //\t用来让数据对齐
        System.out.println("name"+'\t'+"age");
        System.out.println("tomy"+'\t'+"18");
    }
}
