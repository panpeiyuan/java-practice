package chap19;

public class GenericsDemo2 {
    public static void main(String[] args){
        MyArrayLIst<String> list=new MyArrayLIst<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list);
    }
}
