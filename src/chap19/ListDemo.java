package chap19;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args){
        //List也是接口，与Collection一样，不能直接创建对象，只能创建其实现类的对象
        //List也属于单列集合，继承collection的方法
        //List集合有索引，所以有一些独有的方法
        List<String> list=new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        //在指定位置添加元素(原来索引处的元素依次往后移)
        list.add(1,"qqq");
        System.out.println(list);
        //删除指定位置的元素
        list.remove(0);
        System.out.println(list);
        //修改指定位置的元素
        list.set(0,"ttt");
        System.out.println(list);
        //通过索引获取集合的每一个元素
        String s=list.get(2);
        System.out.println(s);
    }
}
