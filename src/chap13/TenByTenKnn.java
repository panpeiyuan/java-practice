package chap13;

import chap11.KFoldEncapsulation;

import java.io.File;
import java.util.Scanner;

public class TenByTenKnn {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/iri.csv");
        System.out.println(file.exists());
        Scanner sc = new Scanner(file);//从file里读数据，一行一行读，
        int count = 0;
        while (sc.hasNextLine()) {//判断该行是否有数据
            String line = sc.nextLine();//csv读数据，只能读为字符串
            String[] parts = line.split(",");
            if (count == 0) {
                System.out.println(parts.length);
            }
            count++;//数行数
        }
        sc.close();
        double[][] data = new double[count][4];//存数据的数组，只存前四列
        int[] labels = new int[count];//存标签的数组
        Scanner sc2 = new Scanner(file);//重新读一遍数据
        int row = 0;
        while (sc2.hasNextLine()) {
            String line2 = sc2.nextLine();//定义一个字符串存储Y一行数据,nextLine（）方法读完一行之后自动换行
            String[] parts2 = line2.split(",");//把数据按“，”分割
            for (int j = 0; j < parts2.length-1; j++) {
                data[row][j] = Double.parseDouble(parts2[j]);//把每一行的前四个数据存入data中
            }
            labels[row] = Integer.parseInt(parts2[4]);//把每一行最后一列的标签存入labels中
            row++;//往下换行
        }
        System.out.println("行数为:" + row);
        System.out.println("列数为:" + data[0].length);
        int m=data.length;//样本数
        int foldcount=10;//折数
        int times=10;//实验次数
        double[] accs=new double[times*foldcount];//装每次实验的准确率，一共一百次
        int accIndex=0;
        for(int t=0;t<times;t++) {//一次十折验证，生成一次随机数组，而不是一次随机数组进行十次十折验证
            KFoldEncapsulation kf = new KFoldEncapsulation(m, foldcount);//生成一组随机数组
            for (int fold = 0; fold < foldcount; fold++) {
                int[] test = kf.testIndex(fold);//生成一个测试集
                int[] train = kf.trainIndex(fold);//生成一个训练集
                double[][] knntrain=new double[train.length][];
                for (int i = 0; i < knntrain.length; i++) {
                    knntrain[i]=data[train[i]];
                }
                int[] trainlabel=new int[train.length];
                for (int i = 0; i < trainlabel.length; i++) {
                    trainlabel[i]=labels[train[i]];
                }
                KnnAlgorithm ka=new KnnAlgorithm(knntrain,trainlabel,1);
                double correct=0;
                int min_label=0;
                for (int i = 0; i < test.length; i++) {
                    min_label=ka.predict(data[test[i]]);
                    if(min_label==labels[test[i]]){
                        correct++;
                    }
                }
                accs[accIndex]=correct/(test.length);
                accIndex++;
            }
        }
        for (int i = 0; i < accs.length; i++) {
            System.out.println("第"+(i+1)+"次实验的正确率为"+accs[i]);
        }
        double sum_correct=0;
        for (int i = 0; i < accs.length; i++) {//算正确率均值
            sum_correct+=accs[i];
        }
        System.out.println("正确率均值为"+sum_correct/(accs.length));
    }
}
