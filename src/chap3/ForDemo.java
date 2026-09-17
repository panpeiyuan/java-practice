package chap3;

public class ForDemo {
    public static void main(String[] args){
        //for(初始化语句；条件判断语句；条件控制语句)
        for(int i=1;i<=5;i++){
            System.out.println("这是第"+i+"次循环");
        }
        for(int i=5;i>=1;i--){//倒循环
            System.out.println("这是第"+i+"次循环");
        }
    }
}
