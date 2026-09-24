package chap9;

public class GirlFriendTest {
    public static void main(String[] args){
        GirlFriend gf1=new GirlFriend();
        //通过set方法设置对象参数
        gf1.setName("asas");
        gf1.setAge(22);
        gf1.setGender("女");
        //通过get方法调用对象参数
        System.out.println(gf1.getName());
        System.out.println(gf1.getGender());
        System.out.println(gf1.getAge());
        //public方法可以直接调用
        gf1.sleep();
        gf1.eat();
        System.out.println("======================");
        GirlFriend gf2=new GirlFriend();
        gf2.setName("sasa");
        gf2.setAge(22);
        gf2.setGender("女");
        System.out.println(gf2.getName());
        System.out.println(gf2.getGender());
        System.out.println(gf2.getAge());
        gf2.sleep();
        gf2.eat();

    }
}
