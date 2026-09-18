package chap4;

public class SkipLopDemo2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                System.out.println("吃饱了不吃了");
                break;//结束整个循环,直接运行循环下面的代码
            }
            System.out.println("这是吃的第" + i + "个包子");
        }
    }
}