package chap12ObjectOrientedGeneralPractice;

import java.util.Random;

public class Role {
    private String name;
    private int blood;
    public Role(){}
    public Role(String name, int blood) {
        this.name = name;
        this.blood = blood;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setBlood(int blood){
        this.blood==blood;
    }
    public String getName(){
        return name;
    }
    public int getblood(){
        return blood;
    }
    public int bit(String name1,int blood){
        Random r=new Random();
        int blood1=r.nextInt(19)+1;
        //该方法由出击的对象调用，方法中的参数是被攻击的人
        System.out.println(this.getName()+"举起拳头，打了"+name1+"一拳"+"，造成了"+

    }

}
