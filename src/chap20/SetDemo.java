package chap20;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class SetDemo {
    public static void main(String[] args){
        Set<String> s=new HashSet<>();
        //1.添加元素
        s.add("zhangsan");//Set系列集合不能添加重复的元素
        s.add("zhangsan");
        s.add("lisi");
        s.add("wangwu");
        //2.打印集合
        //Set系列集合打印时是无序的,且没有索引
        System.out.println(s);
        //3.遍历
        Iterator<String> it=s.iterator();
        while(it.hasNext()){
            String str=it.next();
            System.out.println(str);
        }
        for (String string : s) {
            System.out.println(string);
        }
        s.forEach(new Consumer<String>() {
            @Override
            public void accept(String s1) {
                System.out.println(s1);
            }
        });
    }
}
