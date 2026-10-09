package chap17;

public interface Swim {
    //接口不能创建对象。所以没有构造方法
    //接口的成员变量只能是常量，默认修饰符：public static final
    //接口中的成员方法默认使用public abstract修饰
    public abstract void Swim();
}
