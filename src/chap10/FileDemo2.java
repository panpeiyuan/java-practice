package chap10;

import java.io.File;

public class FileDemo2 {
    public static void main(String[] args){
        /*public boolean isDirectory()
          public boolean isFile()
          public boolean exists()
         */
        //1.对一个文件的路径进行判断
        File f1=new File("C:\\Users\\31376\\Desktop\\aaa\\a.txt");
        System.out.println(f1.isDirectory());
        System.out.println(f1.isFile());
        System.out.println(f1.exists());
        //2.对一个文件夹进行判断
        File f2=new File("C:\\Users\\31376\\Desktop\\aaa\\bbb");
        System.out.println(f2.isDirectory());
        System.out.println(f2.isFile());
        System.out.println(f2.exists());

    }
}
