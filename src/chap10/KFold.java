package chap10;

import java.util.Arrays;
import java.util.Random;

public class KFold {
    public static void main(String[] args) {
        //10折划分器搭建
        int m = 150;
        int k = 10;
        //造一个0-149的数组
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = i;
        }
        //打乱数组
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomindex = i + r.nextInt(arr.length - i);//保证每个位置都被交换一次，但是也有可能换完还在原位（即r.nextInt(arr.length-i)=0)
            int tempt = arr[i];
            arr[i] = arr[randomindex];
            arr[randomindex] = tempt;
        }
        //划十折
        int[] hit = new int[m];//用于检验150个元素是否都被覆盖了
        for (int fold = 0; fold < k; fold++) {//fold表示当前的折数（这是10折fold）
            int[] test = TestIndex(arr, k, fold);
            System.out.println("第" + fold + "折（" + test.length + "个):" + Arrays.toString(test));
            for (int i = 0; i < test.length; i++) {
                hit[test[i]]++;
            }
        }
        //检查不漏元素
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
    }
    public static int[] TestIndex(int[] arr,int k,int fold){//对单个fold内的操作
        int start=fold*arr.length/k;
        int end=(fold+1)*arr.length/k;
        int[] idx=new int[end-start];
        for(int i=start;i<end;i++){//这里上一个fold的end取不到，作为下一个fold的start
            idx[i-start]=arr[i];
        }
        return idx;
    }
}
