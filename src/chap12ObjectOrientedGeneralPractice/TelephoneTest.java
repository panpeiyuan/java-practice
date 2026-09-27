package chap12ObjectOrientedGeneralPractice;

import static chap12ObjectOrientedGeneralPractice.Telephone.avgprice;

public class TelephoneTest {
    public static void main(String[] args){
        Telephone[] arr=new Telephone[3];
        Telephone t1=new Telephone("苹果",8999,"白色");
        Telephone t2=new Telephone("华为",7999,"黑色");
        Telephone t3=new Telephone("小米",6999,"银色");
        arr[0]=t1;
        arr[1]=t2;
        arr[2]=t3;
        double avgprice=avgprice(t1,t2,t3);
        System.out.println("三部手机的平均价格为"+avgprice);
    }
}
