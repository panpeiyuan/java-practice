package chap16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class collectionDemo4 {
    //lambda表达式遍历:default void forEach(Consumer<? super E>action):
    public static void main(String[] args){
        Collection<String> coll=new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");
        coll.forEach(new Consumer<String>() {
            @Override
            //s依次表示集合中的每一个数据
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
