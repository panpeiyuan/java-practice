package chap4;

public class InfiniteloopDemo {
    public static void main(String[] args){
        //for格式的无限循环
//        for(;;){
//            System.out.print("学习");
//        }
        while(true){
            System.out.print("学习");
        }
        //无限循环下面不能再写其他代码了，因为循环停不下来，下面的代码永远执行不到
    }
}
