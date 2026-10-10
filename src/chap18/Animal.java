package chap18;

public abstract  class Animal {
    public abstract void eat();
}
class Dog extends Animal{

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}
