package chap9;

public class GirlFriendTest {
    public static void main(String[] args){
        GirlFriend gf1=new GirlFriend();
        System.out.println(gf1.gender);
        gf1.name="asas";
        gf1.age=22;
        gf1.gender="女";
        System.out.println(gf1.name);
        System.out.println(gf1.gender);
        System.out.println(gf1.age);
        gf1.sleep();
        gf1.eat();
        System.out.println("======================");
        GirlFriend gf2=new GirlFriend();
        gf2.name="asas1";
        gf2.age=22;
        gf2.gender="女";
        System.out.println(gf2.name);
        System.out.println(gf2.gender);
        System.out.println(gf2.age);
        gf2.sleep();
        gf2.eat();

    }
}
