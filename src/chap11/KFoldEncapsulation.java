package chap11;

import java.util.Random;

public class KFoldEncapsulation {
    //封装KFold类
    private int k;//折数
    private int m;//样本总数
    private int[] indices;//存储打乱后的编号
    public KFoldEncapsulation(int m,int k) {//构造方法
        if (k >= 2 && k <= m) {//构造K折
            this.k = k;
        } else {
            throw new IllegalArgumentException("折数不对");
        }
        this.m=m;//构造样本数
        this.indices=new int[m];
        for (int i = 0; i < indices.length; i++) {//构造一个元素为0-m-1的初始数组
            indices[i]=i;
        }
        Random r=new Random();
        for (int i = 0; i < indices.length; i++) {//将刚构造的数组随机打乱
            int randomindex=i+r.nextInt(indices.length-i);
            int tempt=indices[i];
            indices[i]=indices[randomindex];
            indices[randomindex]=tempt;
        }
    }
    public int getK(){
        return k;
    }//get到折数
    public int getM(){
        return m;
    }//get到样本数
    public int foldSize(int fold) {//算第fold折有多少个元素的方法
        int start = fold * m / k;
        int end = (fold + 1) * m / k;
        int foldsize = end - start;
        return foldsize;
    }
    public int[] testIndex(int fold){//创建测试数组
        int start=fold*m/k;
        int end=(fold+1)*m/k;
        int[] arrtestIndex=new int[end-start];
        for(int i=0;i<(end-start);i++){
            arrtestIndex[i]=indices[start+i];
        }
        return arrtestIndex;
    }
    public int[] trainIndex(int fold){//创建训练数组
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
