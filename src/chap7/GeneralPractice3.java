package chap7;

import java.util.Random;

public class GeneralPractice3 {//随机生成验证码（前四位是随机大小写字母，最后一位是数字）
    public static void main(String[] args){
        //难点：造字母表的代码
        char[] chars=new char[52];
        for(int i=0;i<chars.length;i++){
            //ASCII码表强转
            if(i<=25){
                chars[i]=(char) (97+i);//添加小写字母(a对应数字97）
            }else{
                chars[i]=(char)(65+i-26);
            }
        }
        Random r=new Random();
        String result="";
        for(int i=0;i<4;i++){
            int Index=r.nextInt(chars.length);
            result+=chars[Index];
        }
        System.out.print(result+r.nextInt(10));
    }
}
