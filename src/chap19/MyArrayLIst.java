package chap19;

import java.util.Arrays;

public class MyArrayLIst <E>{
    //当编写类时，如果不确定类型，那么这个类就可以定义为泛型类
    Object[] obj=new Object[10];
    int size;
    public boolean add(E e){//E表示不确定的类型，在类名后面已经定义过了
        obj[size]=e;
        size++;
        return true;
    }
    public E get(int index){
        return (E)obj[index];//默认输出的是object类型，在这里进行一个强转，强转为E类型
    }

    @Override
    public String toString(){
        return "MyArrayList{"+
                "obj="+ Arrays.toString(obj)+
                "obj="+ size +
                '}';
    }
}
