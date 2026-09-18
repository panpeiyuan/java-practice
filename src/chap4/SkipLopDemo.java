package chap4;

public class SkipLopDemo {
    public static void main(String[] args){
        //跳过第一次循环
        for(int i=1;i<=5;i++){
            if(i==3){
                //continue表示结束本次循环，继续下次循环
                System.out.println("第"+i+"个包子有虫子，不吃了");
                continue;
            }
            System.out.println("小老虎在吃第"+i+"个包子");
        }
    }
}
