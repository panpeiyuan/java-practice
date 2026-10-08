package chap16;

import java.util.ArrayList;
import java.util.Collection;

public class collectionDemo {
    public static void main(String[] args){
        /*Collection是一个接口，我们不能直接创建他的对象
        所以在学习他的方法时，只能创建他实现类的对象
        实现类：ArrayList
         */
        Collection<String> coll=new ArrayList<>();
        /*1.添加元素
        若向List系列集合中添加数据，那么方法永远返回true，因为List系列允许元素重复
        若向Set系列集合中添加数据，若当前元素不存在则返回true，若当前元素已经存在则返回false，因为set系列的集合不允许重复
         */
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        System.out.println(coll);
        /*2.清空元素
        coll.clear();
         */
        /*3.删除元素
        因为Collection里面定义的是共性的方法，所以不能使用索引删除，只能通过元素对象删除
        方法会返回一个布尔类型的值，删除成功返回true，删除失败返回false（要删除的元素不存在则删除失败）
         */
        coll.remove("aaa");
        System.out.println(coll);
        /*4.判断元素是否包含
        底层是依赖equals方法进行判断是否存在的
        如果集合中存储的是自定义对象，也想通过contains方法判断是否包含，则需要在javabean类中重写equals方法(alt+insert快捷键可以直接重写）
         */
        boolean result=coll.contains("bbb");
        System.out.println(result);
        /*5.判断集合是否为空
         */
        boolean result2=coll.isEmpty();
        System.out.println(result2);
        /*6.获取集合的长度
         */
        coll.add("ddd");
        int size=coll.size();
        System.out.println(size);

    }
}
