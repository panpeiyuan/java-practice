package chap19;

import java.util.ArrayList;

public class GenericsTest {
    public static void main(String[] args){
        ArrayList<String> arr1=new ArrayList<>();
        ArrayList<Integer> arr2=new ArrayList<>();
        arr1.add("aaa");
        arr2.add(123);
        ListUtil.addAll(arr1,"aaa","bbb");
        System.out.println(arr1);
    }
}
class ListUtil<E>{//工具类私有化构造方法
    private ListUtil() {};
    //参数一：集合
    //参数二到最后：表示要添加的元素
    //注意：静态方法要使用泛型，必须把<E>写在static后面
    public static<E> void addAll(ArrayList<E> list,E...e){
        for (E element : e) {
            list.add(element);
        }
    }
}