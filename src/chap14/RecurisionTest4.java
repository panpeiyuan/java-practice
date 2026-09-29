package chap14;

public class RecurisionTest4 {
    public static void main(String[] args){
        Tower tower=new Tower();
        tower.move(5,'A','B','C');
    }
}
class Tower{
    //num表示要移动的个数，abc表示三个塔
    public void move(int num,char a,char b,char c){
        if(num==1){
            System.out.println(a+"->"+c);
        }else{
            //如果有多个盘，可以看作两个，一个是最下面的，一个是上面所有的
            //先移动上面所有的盘到b，借助c
            move(num-1,a,c,b);
            //把最下面这个盘移动到c
            System.out.println(a+"->"+c);
            //再把b塔所有的盘移动到c，借助a
            move(num-1,b,a,c);
        }
    }
}
