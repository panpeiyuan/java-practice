package chap20;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args){
        //创建map集合对象
        Map<String,String> m=new HashMap<>();
        //添加元素
        m.put("guojing","huangrong");
        m.put("weixiaobao","gujianping");
        m.put("yinzhiping","xiaolongnv");
        m.put("yinzhiping","dalongnv");//在添加数据时，如果键存在，会把原有的键值对对象覆盖，并把被覆盖的值进行返回
        System.out.println(m);
        //删除元素
        m.remove("guojing");
        System.out.println(m);
        //判断是否存在
        boolean exists=m.containsKey("guojing");
        System.out.println(exists);
        //查询集合长度
        int size=m.size();
        System.out.println(size);
    }
}
