package chap11;

import java.io.File;
import java.util.Scanner;

public class TenByTenTest {
        public static void main(String[] args) throws Exception {
            File file = new File("D:/算法学习测试数据集/CSV/iri.csv");
            System.out.println(file.exists());
            Scanner sc = new Scanner(file);
            int count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (count == 0) {
                    System.out.println(parts.length);
                }
                count++;
            }
            System.out.print("行数" + count);
            sc.close();
            double[][] data = new double[count][4];
            int[] labels = new int[count];
            Scanner sc2 = new Scanner(file);
            int row = 0;
            while (sc2.hasNextLine()) {
                String line2 = sc2.nextLine();
                String[] parts2 = line2.split(",");
                for (int j = 0; j < 4; j++) {
                    data[row][j] = Double.parseDouble(parts2[j]);
                }
                labels[row] = Integer.parseInt(parts2[4]);
                row++;
            }
            System.out.println("行数为:" + row);
            System.out.println("列数为:" + data[0].length);
            int m=data.length;
            int k=10;
            int times=10;
            double[] accs=new double[times*k];
            int accIndex=0;
            for(int t=0;t<times;t++){//一次十折验证，生成一次随机数组，而不是一次随机数组进行十次十折验证
                KFoldEncapsulation kf=new KFoldEncapsulation(m,k);
                for(int fold=0;fold<k;fold++){
                    int[] test=kf.testIndex(fold);
                    int[] train=kf.trainIndex(fold);

                    int correct=0;
                    for(int p=0;p<test.length;p++){
                        int s=test[p];
                        int best = -1;
                        double bestD = Double.MAX_VALUE;
                        for (int j = 0; j < train.length; j++) {    // 在训练集里找最近的
                            double d = 0;
                            for (int f = 0; f < 4; f++) {           // 4 个特征逐个算差
                                double diff = data[s][f] - data[train[j]][f];
                                d += diff * diff;
                            }
                            if (d < bestD) {          // 比当前最近还近
                                bestD = d;
                                best = train[j];      // ★ 注意是 train[j]，不是 j
                            }
                        }
                            if (labels[best] == labels[s]) {            // 预测对了吗
                                correct++;
                            }
                    }
                    accs[accIndex] = (double) correct / test.length;  // 存进结果数组
                    accIndex++;
                    }
                }
            // 临时诊断：确认循环真的跑了
            System.out.println("收集到的准确率个数：" + accIndex);   // 应该是 100
            System.out.println("第一个准确率：" + accs[0]);          // 应该 0.9 上下

// 均值
            double sum = 0;
            for (int i = 0; i < accs.length; i++) {
                sum += accs[i];
            }
            double mean = sum / accs.length;

// 标准差
            double sum2 = 0;
            for (int i = 0; i < accs.length; i++) {
                sum2 += (accs[i] - mean) * (accs[i] - mean);
            }
            double sd = Math.sqrt(sum2 / accs.length);

            System.out.println("10×10 准确率均值：" + mean);
            System.out.println("标准差：" + sd);

            }


        }