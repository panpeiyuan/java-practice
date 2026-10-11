package chap20;

import java.util.*;
import java.util.function.Consumer;

public class MapDemo2 {
    public static void main(String[] args){
        Map<String,String> map=new HashMap<>();
        map.put("yinzhiping","xiaolongnv");
        map.put("guojing","munianci");
        map.put("ouyangke","huangrong");
        //MAp集合的第一种遍历方式
        //获取所有的键，把这些建放入一个单列集合中，再通过遍历这个存放键的集合进行键找值
        Set<String> key=map.keySet();
        for (String s : key) {
            String str=map.get(s);
            System.out.println(str);
        }
        //练习，使用迭代器遍历
        Iterator<String> it=key.iterator();
        while(it.hasNext()){
            String str= it.next();
            System.out.println(map.get(str));
        }
        //练习，使用lambda遍历
        key.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(map.get(s));
            }
        });
    }
}
