package chap12ObjectOrientedGeneralPractice;

public class GameTest {
    public static void main(String[] args){
        Role r1=new Role("潘培源",'男',100);
        Role r2=new Role("曹瀚文",'女',100);
        System.out.println("姓名:"+r1.getName());
        System.out.println("性别:"+r1.getgender());
        System.out.println("血量:"+r1.getblood());
        System.out.println("长相:"+r1.getface());
        System.out.println("姓名:"+r2.getName());
        System.out.println("性别:"+r2.getgender());
        System.out.println("血量:"+r2.getblood());
        System.out.println("长相:"+r2.getface());
        while(true){
            //r1攻击r2
            r1.attack(r2);
            if(r2.getblood()==0){
                System.out.println(r1.getName()+"KO"+r2.getName());
                break;
            }
            r2.attack(r1);
            if(r1.getblood()==0){
                System.out.println(r2.getName()+"KO"+r1.getName());
                break;
            }
        }
    }
}
