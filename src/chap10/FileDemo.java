package chap10;

import java.io.File;

public class FileDemo {
    public static void main(String[] args){
        //1.根据字符串表示的路径，变成File对象
        String str="C:\\Users\\31376\\Desktop\\a.txt";
        File f1=new File(str);
        System.out.println(f1);
        //2.父级路径：C:Users31376Desktop
        //子集路径：a.text
        String parent="C:\\Users\\31376\\Desktop";
        String child="a.txt";
        File f2=new File(parent,child);
        System.out.println(f2);
        //3.把一个File表示的路径和String表示路径进行拼接
        File parent2=new File("C:\\Users\\31376\\Desktop");
        String child2="a.text";
        File f4=new File(parent2,child2);
        System.out.println(f4);
    }
}
