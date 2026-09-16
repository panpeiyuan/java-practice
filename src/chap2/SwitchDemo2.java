package chap2;

public class SwitchDemo2 {
    //需求：把1，2，3用switch进行匹配并输出一，二，三
    //switch新用法
    public static void main(String[] args){
        int number=1;
        switch(number){
            case 1 -> System.out.print("一");//->可以省略break，又由于case中只有一条语句，所有大括号也可以省略
            case 2 -> System.out.print("二");
            case 3 -> System.out.print("三");
            default -> System.out.print("没有这个选项");
        }
    }
}
