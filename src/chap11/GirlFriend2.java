package chap11;

public class GirlFriend2 {
    //区分成员变量与局部变量
    private int age;
    public void method(){
        int age=10;
        System.out.println(age);//这里的age取就近原则，取局部变量
        System.out.println(this.age);//强制取成员变量
    }
}
