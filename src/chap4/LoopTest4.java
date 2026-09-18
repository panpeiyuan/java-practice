package chap4;

import java.util.Random;

public class LoopTest4 {
    //获取随机数
    Random r=new Random();
    int number=r.nextInt(100)+1;//限制随机数范围，只写终止值即可，代表从0-99随机生成一个数字，0和99均可以取到
    /*生成任意数到任意数的方法：
    eg：7-15中随机生成一个数字
    1.把这个范围头尾部各减去一个值，使范围从0开始，7-15->0-8
    2.尾巴+1 0-9
    3.最终的结果再加上第一步减去的值
     */

}
