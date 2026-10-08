package chap15;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest4 {
    public static void main(String[] args){
        ArrayList<Users> u=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<3;i++){
            Users user=new Users();
            System.out.println("请输入第"+(i+1)+"个用户的id:");
            user.setId(sc.nextInt());
            System.out.println("请输入第"+(i+1)+"个用户的姓名:");
            user.setUsername(sc.next());
            System.out.println("请输入第"+(i+1)+"个用户的密码:");
            user.setPassword(sc.next());
            u.add(user);
        }
        System.out.println("请输入你要查找的id:");
        int select_id=sc.nextInt();
        boolean exists = false;
        for(int i=0;i<u.size();i++){
            exists=selectmethod(select_id,u.get(i).getId());
            if(exists){
                break;
            }
        }
        System.out.println(exists);
    }
    public static boolean selectmethod(int select_id,int id){
        if(select_id==id){
            return true;
        }
        return false;
    }
}
class Users{
    private int id;
    private String username;
    private String password;

    public Users() {
    }

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
