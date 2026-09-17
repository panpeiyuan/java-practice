package chap3;

public class WhileTest {
    public static void main(String[] args){
        double i=0.1;
        int count=0;
        while(i<8844430){
            i*=2;
            count++;
        }
        System.out.print(count);
    }
}
