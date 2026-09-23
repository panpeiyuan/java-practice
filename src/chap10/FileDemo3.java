package chap10;

import java.io.File;

public class FileDemo3 {
    public static void main(String[] args){
        /*
        public long length()
        public String getAbsolutePath()
        public String getPath()
        public String getName()
        public long lastModified()
         */
        //1.length 返回文件大小（字节数）
        //这个方法只能获取文件的大小，单位是字节
        //如果单位是M，G，可以不断的除以1024
        //这个方法无法获取文件夹的大小
        File f1=new File("C:\\Users\\31376\\Desktop\\aaa\\a.txt");
        long len=f1.length();
        System.out.println(len);
        System.out.println("=======================");
        //2.getAbsolutePath返回文件的绝对路径
        File f3;
    }
}
