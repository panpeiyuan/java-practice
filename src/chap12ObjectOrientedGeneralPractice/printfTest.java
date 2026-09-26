package chap12ObjectOrientedGeneralPractice;

public class printfTest {
    public static void main(String[] args){
        //printf有两部分参数
        //第一部分参数：要输出的内容%s（占位）
        //第二部分参数：填充的数据
        System.out.printf("你好啊%s","张三");
        System.out.println();//printf没有换行功能。只能在中间加println进行换行
        System.out.printf("%s你好啊%s","张三","李四");
    }
}
