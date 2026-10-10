package chap16;

import java.util.ArrayList;
import java.util.Collection;

public class collectionDemo5 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("zhangsan");
        coll.add("lisi");
        coll.add("wangwu");
        //利用匿名内部类的形式
        //coll.forEach(new Consumer<String>(){//forEach底层原理也是for遍历集合，依次得到每一个元素，并把得到的每一个元素，依次传递给下面的accept方法
//            @Override
//            public void accept(String s) {//s依次表示集合中的每一个数据，想对数据进行的操作，直接操作s即可
//                System.out.println(s);
//            }
//
//            @Override
//            public Consumer<String> andThen(Consumer<? super String> after) {
//                return Consumer.super.andThen(after);
//            }
//        });
        //lambda表达式
        //()->{}
        coll.forEach((String s)->{
                System.out.println(s);
            }
        );
    }
}
