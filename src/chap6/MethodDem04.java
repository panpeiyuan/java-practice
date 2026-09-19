package chap6;

public class MethodDem04 {
    //方法的重载：同一个类中，方法名相同，参数不同的方法。与返回值无关（参数不同包括个数不同、类型不同、顺序不同）
    public static void main(String[] args){
        comp(10,20);
        comp((byte)10,(byte)20);
    }
    public static void comp(byte i,byte j) {
        if (i == j) {
            System.out.println("相等");
        } else {
            System.out.print("不相等");
        }
    }
    public static void comp(short i,short j) {
        if (i == j) {
            System.out.println("相等");
        } else {
            System.out.print("不相等");
        }
    }
    public static void comp(int i,int j) {
        if (i == j) {
            System.out.println("相等");
        } else {
            System.out.print("不相等");
        }
    }
    public static void comp(long i,long j) {
        if (i == j) {
            System.out.println("相等");
        } else {
            System.out.print("不相等");
        }
    }
}
