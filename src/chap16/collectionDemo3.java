package chap16;

import java.util.ArrayList;
import java.util.Collection;

public class collectionDemo3 {
    public static void main(String[] args){
        //所有的单列集合和数组才能使用增强for进行遍历
        //for(元素的数据类型 变量名：数组或者集合）{}
        Collection<String> coll=new ArrayList<>();
        coll.add("张三");
        coll.add("李四");
        coll.add("王五");
        for (String s : coll) {//快捷生成格式：coll.for
            System.out.println(s);//修改这里的s，不会改变原集合中的数据，s仅仅是一个第三方变量
        }
    }
}
