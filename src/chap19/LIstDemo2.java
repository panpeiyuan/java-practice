package chap19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class LIstDemo2 {
    public static void main(String[] args){
        //List集合的5中遍历方式
        List<String> list=new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        //1.迭代器方式进行遍历
        Iterator<String> it=list.iterator();
        while(it.hasNext()){
            String s=it.next();
            System.out.println(s);
        }
        //2.增强for遍历
        for (String s : list) {//这里的s就是一个第三方标记
            System.out.println(s);
        }
        //3.lambda遍历
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s){
                System.out.println(s);
            }
            }
        );
        //4.普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //5.列表迭代器
        //获取一个列表迭代器对象，里面的指针默认也是指向0索引
        //额外添加了一个方法：在遍历的过程中，可以添加元素
        ListIterator<String> it1=list.listIterator();
        while(it1.hasNext()){
            String str=it1.next();
            if("bbb".equals(str)){
                it1.add("ddd");
            }
        }
        System.out.println(list);
    }
}
