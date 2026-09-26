package chap12ObjectOrientedGeneralPractice;

import java.util.Random;

public class Role {
    private String name;
    private int blood;
    private char gender;
    private String face;
    String[] boyface={"风流倜傥","气宇轩昂","相貌英俊","五官端正","相貌平平","一塌糊涂","面目狰狞"};
    String[] girlface={"美艳绝伦","沉鱼落雁","亭亭玉立","身材姣好","相貌平平","相貌简陋","惨不忍睹"};
    public Role(){}//无参构造
    public Role(String name, char gender,int blood) {//有参构造
        this.name = name;
        this.blood = blood;
        this.gender=gender;
        this.face=setface(gender);
    }
    public void setBlood(int blood){
        this.blood=blood;
    }
    public String getName(){
        return name;
    }
    public int getblood(){
        return blood;
    }
    public char getgender(){
        return gender;
    }
    public String getface(){
        return face;
    }
    public String setface(char gender){
        Random r=new Random();
        if(gender=='男'){
            int randomindex=r.nextInt(8);
            this.face=boyface[randomindex];
            //从boy里随机
        }else if(gender=='女'){
            int randomindex=r.nextInt(8);
            this.face=girlface[randomindex];
            //从girl里随机
        }else{
            this.face="面目狰狞";
        }
        return face;
    }
    //定义一个攻击的方法
    //Role r1=new Role（）
    //Role r2=new Rple（）
    //r1.攻击（r2）
    public void attack(Role role){
        Random r=new Random();
        int hurt=r.nextInt(blood)+1;
        int remainblood=role.getblood()-hurt;
        //验证剩余血量是否为负数
        if (remainblood<=0){
            remainblood=0;
        }
        role.setBlood(remainblood);
        System.out.println(this.getName()+"举起拳头，打了"+role.getName()+"一拳"+"，造成了"+hurt+"点伤害,"+role.getName()+"还剩下"+remainblood+"点血");
    }

}
