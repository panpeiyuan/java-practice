package chap7;

import java.util.Scanner;

public class GeneralPractice {//判断机票价格
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入机票原价:");
        double price = sc.nextDouble();
        System.out.print("请输入月份:");
        int month = sc.nextInt();
        System.out.print("请输入头等舱或经济舱:");
        String seat = sc.next();
        if (month >= 5 && month <= 10) {
            double price_new = ticket(price, seat, 0.9, 0.85);
            if (price_new < 0) {
                System.out.print("请输入正确的舱位");
            } else {
                System.out.print("机票价格为:" + price_new);
            }
        } else if ((month >= 11 && month <= 12) || (month >= 1 && month <= 4)) {
            double price_new = ticket(price, seat, 0.7, 0.65);
            if (price_new < 0) {
                System.out.print("请输入正确的舱位");
            } else {
                System.out.print("机票价格为" + price_new);
            }
        }else{
            System.out.print("请输入正确的月份");
        }
    }
    public static double ticket ( double price, String seat,double v1, double v2){
            if (seat.equals("头等舱")) {
                price = price * v1;
            } else if (seat.equals("经济舱")) {
                price = price * v2;
            } else {
                System.out.print("请输入正确的舱级");
                return -1;
            }
            return price;
        }
    }



//    System.out.print("请输入头等舱或经济舱:");
////        String seat=sc.next();
////        if(month>=5&&month<=10&&seat.equals("头等舱")){
////            System.out.print("机票价格为:"+price*0.9);
////        }else if(month>=5&&month<=10&&seat.equals("经济舱")){
////            System.out.print("机票价格为:"+price*0.85);
////        }else if((month>=11&&month<=12)||(month>=1&&month<=4)&&seat.equals("头等舱")){
////            System.out.print("机票价格为:"+price*0.7);
////        }else if((month>=11&&month<=12)||(month>=1&&month<=4)&&seat.equals("经济舱")){
////            System.out.print("折后机票价格为:"+price*0.65);
////        }
