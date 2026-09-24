package chap9;

public class GirlFriend {
    private String name;
    private int age;
    private String gender;

    public void sleep() {//不需要返回值，直接void也行
        System.out.println("女朋友在睡觉");
    }

    public void eat() {
        System.out.println("女朋友在吃饭");
    }

    //针对于每一个私有化的成员变量，都要提供get和set方法
    //set方法：给成员变量赋值
    //get方法：对外提供成员信息
    public void setName(String name) {
        //局部变量表示测试类中调用方法传递过来的数据
        //等号的左边，就表示成员位置的name
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAge(int age){
        if(age>=18&&age<=50){
            this.age=age;
        }else{
            System.out.println("年龄超出范围");
        }
    }
    public int getAge(){
        return age;
    }

    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }
}
