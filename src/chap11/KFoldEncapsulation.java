package chap11;

import java.util.Random;

public class KFoldEncapsulation {
    private int k;//折数
    private int m;//样本总数
    private int[] indices;//存储打乱后的编号
    public KFoldEncapsulation(int m,int k) {
        if (k >= 2 && k <= m) {
            this.k = k;
        } else {
            throw new IllegalArgumentException("折数不对");
        }
        this.m=m;
        this.indices=new int[m];
        for (int i = 0; i < indices.length; i++) {
            indices[i]=i;
        }
        Random r=new Random();
        for (int i = 0; i < indices.length; i++) {
            int randomindex=i+r.nextInt(indices.length-i);
            int tempt=indices[i];
            indices[i]=indices[randomindex];
            indices[randomindex]=tempt;
        }
    }
    public int getK(){
        return k;
    }
    public int getM(){
        return m;
    }
    public int foldSize(int fold) {
        int start = fold * m / k;
        int end = (fold + 1) * m / k;
        int foldsize = end - start;
        return foldsize;
    }
    public int[] testIndex(int fold){
        int start=fold*m/k;
        int end=(fold+1)*m/k;
        int[] arrtestIndex=new int[end-start];
        for(int i=0;i<(end-start);i++){
            arrtestIndex[i]=indices[start+i];
        }
        return arrtestIndex;
    }
    public int[] trainIndex(int fold){
        int start=fold*m/k;
        int end=(fold+1)*m/k;
        int[] trainIndex=new int[m-foldSize(fold)];
        int w=0;//存入trainIndex数组的开始指针
        for(int i=0;i<indices.length;i++){
            if(i<start||i>=end){
                trainIndex[w]=indices[i];
                w++;
            }
        }
        return trainIndex;
    }
}
