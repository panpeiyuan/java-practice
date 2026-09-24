package chap11;

public class KFoldEncapsulationTest {
    public static void main(String[] args){
        int m=150;
        int k=10;
        int[] hit=new int[m];
        KFoldEncapsulation kf=new KFoldEncapsulation(m,k);
        for(int fold=0;fold<k;fold++){
            int test[] =kf.testIndex(fold);
            for(int p=0;p<test.length;p++){
                hit[test[p]]++;
            }
        }
        boolean ok = true;
        for (int i = 0; i < hit.length; i++) {
            if (hit[i] != 1) {
                ok = false;
                System.out.println("异常编号:" + i + "出现" + hit[i] + "次");
            }
        }
        if (ok) {
            System.out.println("10折覆盖0-149，不重不漏");
        }
        int[] hitBoth=new int[m];
        int[] test5=kf.testIndex(5);
        int[] train5=kf.trainIndex(5);
        for (int p = 0; p < test5.length; p++) {
            hitBoth[test5[p]]++;                      // 测试集的编号各记一笔
        }
        for (int p = 0; p < train5.length; p++) {
            hitBoth[train5[p]]++;                     // 训练集的编号也各记一笔
        }

        boolean ok2 = true;
        for (int i = 0; i < hitBoth.length; i++) {
            if (hitBoth[i] != 1) {
                ok2 = false;
                System.out.println("训练/测试异常编号:" + i + " 出现 " + hitBoth[i] + " 次");
            }
        }
        if (ok2) {
            System.out.println("第 5 折训练集(" + train5.length + "个) + 测试集(" + test5.length + "个) = 全体，不重不漏");
        }
    }
}
