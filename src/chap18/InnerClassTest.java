package chap18;

public class InnerClassTest {
    public static void main(String[] args){
        //匿名内部类的编写
        /*new 类名或接口（）{
            重写方法；
         };
         */
        new Swim(){//匿名内部类实现Swim接口，new的是没有名字的类的对象
            @Override
            public void swim() {
                System.out.println("重写了游泳的方法");
            }
        };
        new Animal(){//若前面是一个类，则这个匿名内部类是继承前面这个类，且new后面是在创建匿名内部类的对象
            @Override
            public void eat(){
                System.out.println("重写了eat方法");
            }
        };
        Dog dog=new Dog();
        method(dog);
        method(
            new Animal(){//Animal抽象类的实现类对象
                @Override
                public void eat(){
                    System.out.println("不用创建Dog类就可以实现狗吃骨头");
                }
            }
        );
    }
    public static void method(Animal a){//Animal的子类都可以作为这个方法的参数
        a.eat();
    }

}
