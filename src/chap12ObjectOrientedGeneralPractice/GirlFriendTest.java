package chap12ObjectOrientedGeneralPractice;

import java.util.Scanner;

public class GirlFriendTest {
    public static void main(String[] args){
        GirlFriend[] arr=new GirlFriend[4];
        Scanner s=new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            GirlFriend g=new GirlFriend();
            System.out.println("请输入第"+(i+1)+"个女朋友的姓名:");
            g.setName(s.next());
            System.out.println("请输入第"+(i+1)+"个女朋友的年龄:");
            g.setAge(s.nextInt());
            System.out.println("请输入第"+(i+1)+"个女朋友的性别");
            g.setGender(s.next());
            System.out.println("请输入第"+(i+1)+"个女朋友的爱好");
            g.setHabits(s.next());
            arr[i]=g;
        }
        double sum=0;
        for (int i = 0; i < arr.length; i++) {
            int age=arr[i].getAge();
            sum+=age;
        }
        double avgage=sum/arr.length;
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].getAge()<avgage){
                count++;
                System.out.println("年龄小于平均年龄的女朋友的个人信息为： 姓名:"+arr[i].getName()+"年龄:"+arr[i].getAge()+"性别:"+arr[i].getGender()+"爱好:"+arr[i].getHabits());
            }
        }
        System.out.println("年龄小于平均年龄的女朋友有"+count+"个");
    }
}
