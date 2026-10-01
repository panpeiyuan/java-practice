package chap15;

import java.util.ArrayList;

public class ArrayListDemo {
    //集合长度可以改变
    //集合不能存基本数据类型，只能存引用数据类型
    public static void main(String[] args){
        //创建集合的对象
        //泛型：限制集合中存储数据的类型(不能写基本数据类型)
        ArrayList<String> list=new ArrayList<>();
        System.out.println(list);
        //添加元素
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list);
        //删除元素
        list.remove("aaa");//此方法返回的是boolean类型变量，删除成功为true，失败为false
        System.out.println(list);
        String str1=list.remove(0);//用索引删除返回的是被删除对象的值
        System.out.println(str1);
        //修改元素
        String result=list.set(0,"ddd");
        System.out.println(result);//set方法返回的是被覆盖的元素
        //查询
        String s=list.get(0);
        System.out.println(s);//查询单个元素
        int size=list.size();//获取集合长度的方法
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
