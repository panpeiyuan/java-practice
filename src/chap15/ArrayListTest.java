package chap15;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest {
    public static void main(String[] args){
        ArrayList<String> array=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        for (int i=0;i<3;i++){
            System.out.print("请输入第"+(i+1)+"个元素:");
            array.add(i,sc.next());
        }
        System.out.print("[");
        for (int i = 0; i < array.size()-1; i++) {
            System.out.print(array.get(i)+",");
        }
        System.out.print(array.get(array.size()-1));
        System.out.print("]");
    }
}
