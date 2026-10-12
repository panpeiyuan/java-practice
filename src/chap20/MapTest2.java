package chap20;

import java.util.*;

public class MapTest2 {
    public static void main(String[] args){
       //1.先让同学们投票
       String[] arr={"A","B","C","D"};
        ArrayList<String> list=new ArrayList<>();
        Random r=new Random();
        for (int i = 0; i <80; i++) {
            int index=r.nextInt(arr.length);
            list.add(arr[index]);//把80个投票结果存入list集合
        }
        //2.利用map集合进行统计
        HashMap<String,Integer> hm =new HashMap<>();
        for (String name : list) {
            //判断当前景点在map集合中是否存在
            if(hm.containsKey(name)){
                //存在
                //先获取当前景点已经被投的次数
                int count=hm.get(name);
                count++;
                hm.put(name,count);

            }else{
                hm.put(name,1);
            }
        }
        Set<String> label=hm.keySet();
        int mostnumber=0;
        String mostindex="-1";
        for (String name : label) {
            int number=hm.get(name);
            if(number>mostnumber){
                mostnumber=number;
                mostindex=name;
            }
        }
        System.out.println(hm);
        System.out.println("获得最高票数的景点为"+mostindex);
    }
}
