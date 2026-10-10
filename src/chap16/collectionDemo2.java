package chap16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class collectionDemo2 {
    //在遍历的过程中需要删除元素，则使用迭代器遍历
    public static void main(String[] args){
        Collection<String> coll=new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        //获取迭代器对象（迭代器遍历集合时不依赖索引）
        //Iterator<E> iterator();
        Iterator<String> it=coll.iterator();
        while(it.hasNext()){
            //boolean hasNext():判断当前指向的位置是否有元素
            String str=it.next();
            //E next():获取当前指向的元素并移动指针
            System.out.println(str);
        }
        //当上面的循环结束后，迭代器的指针已经指向了最后没有元素的位置
//      System.out.println(it.next());//报错
        //若要第二次遍历集合，则只能再次获取一个新的迭代器对象
        Iterator<String> it2=coll.iterator();
        while(it2.hasNext()){
            String str2=it2.next();
            if(str2.equals("bbb")){
                it2.remove();//迭代器遍历时，不能使用集合的方法进行增加或者删除，实在要删除只能用迭代器的方法进行删除
            }
        }
        System.out.println(coll);
    }
}
