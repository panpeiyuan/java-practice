package chap12ObjectOrientedGeneralPractice;

public class GoodsTest {
    public static void main(String[] args){
        //创建一个商品数组用来存储商品
        Goods[] goodsrole=new Goods[3];
        //创建三个商品对象
        Goods g1=new Goods("001","华为手机",9999.9,100);
        Goods g2=new Goods("002","保温杯",227.0,50);
        Goods g3=new Goods("003","枸杞",12.7,70);
        //把商品对象存入数组
        goodsrole[0]=g1;
        goodsrole[1]=g2;
        goodsrole[2]=g3;
        for (int i = 0; i < goodsrole.length; i++) {
            Goods good=goodsrole[i];
            System.out.println(good.getGoodid()+","+good.getGoodname()+","+good.getGoodprice()+","+good.getGoodinventory());
        }
    }
}
