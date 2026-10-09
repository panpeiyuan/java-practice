package chap17;

public class extendsDemo {
    public static void main(String[] args){
        zi z=new zi();
        z.zishow();

    }
}
class Fu{
    String name="fu";
    String hobby="喝茶";
}
class zi extends Fu{
    String name="zi";
    String game="吃鸡";
    public void zishow(){
        String name="zishow";
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(this.game);
        System.out.println(super.name);
        System.out.println(super.hobby);
    }
}
