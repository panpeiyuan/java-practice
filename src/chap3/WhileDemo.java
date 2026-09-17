package chap3;

public class WhileDemo {
    public static void main(String[] args){
        /*while语句结构
        初始化语句：
        while(条件判断语句){
            循环体语句;
            条件控制语句;
        }
        循环下面的其他语句
         */
        int i =1;
        while(i<=100){
            System.out.println(i);
            i++;
        }
    }
}
/* for循环与while循环的区别
for循环中。控制循环的变量归属于for循环的语法结构中，for循环结束后就不能被访问了
while循环中，控制循环的变量不归属于其语法结构，while循环结束后，该变量可继续被使用
一般来说for循环用于知道循环次数或循环范围，while用于不知道循环的次数和范围，只知道循环的结束条件
 */