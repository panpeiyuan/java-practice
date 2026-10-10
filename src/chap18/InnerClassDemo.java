package chap18;

public class InnerClassDemo {
}
class car{//外部类
    String carname;
    int carage;
    String carcolor;
    public void show(){
        System.out.println(carcolor);
        Engine e=new Engine();//外部类只能访问外部类的成员，若要访问内部类成员则必须创建内部类的对象
        e.show();
    }
    class Engine{//内部类（内部类可以直接访问外部类的成员，外部类不可直接访问内部类的成员。若要访问必须创建内部类的对象）
        String enginebrand;
        int engineAge;
        public void show(){
            System.out.println(enginebrand);
            System.out.println(carname);//内部类可以直接访问外部类的成员
        }
    }
}
