package chap20;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class MapDemo3 {
    public static void main(String[] args){
        Map<String,String> map=new HashMap<>();
        map.put("标枪选手","马超");
        map.put("人物挂件","明世隐");
        map.put("御龙骑士","尹志平");
        //map集合的第二种遍历方式
        //通过键值对对象进行遍历
        //通过一个方法获取所有的键值对对象，返回一个set集合
        Set<Map.Entry<String,String>> entries=map.entrySet();
        //遍历entries集合得到每一个键值对对象
        for (Map.Entry<String, String> entry : entries) {
            String key= entry.getKey();
            String value=entry.getValue();
            System.out.println(key+"="+value);
        }
        //练习：使用迭代器遍历set集合
        Iterator<Map.Entry<String,String>> it=entries.iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry=it.next();
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
        //练习：使用lambda遍历set集合
        entries.forEach(new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> stringStringEntry) {
                System.out.println(stringStringEntry);
            }
        });

    }
}
