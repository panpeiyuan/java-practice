package chap9;

public class SampleKnnAlgorithm2 {
        public static void main(String[] args) {
            // 六个样本点：前三个属于类别 1，后三个属于类别 2
            double[][] arr = {{5.1, 3.5}, {4.9, 3.0}, {4.7, 3.2}, {7.0, 3.2}, {6.4, 3.2}, {6.9, 3.1}};
            int[] labels = {1, 1, 1, 2, 2, 2};   // 每个点的标签，下标和 arr 一一对应
            double[] arrtest = {5.0, 3.4};       // 待预测点
            // 第一步：算出测试点到六个样本点的距离，存进数组
            double[] dist = new double[arr.length];
            for (int i = 0; i < arr.length; i++) {
                dist[i] = distance(arrtest[0], arrtest[1], arr[i][0], arr[i][1]);
                System.out.println("第" + (i + 1) + "个点到测试点的距离为:" + dist[i] + ",标签是:" + labels[i]);
            }
            // 第二步：找出距离最小的三个点的下标——选一个划掉一个，选三次
            int k = 3;
            int[] idx = new int[k];                    // 装选出来的三个下标
            boolean[] used = new boolean[arr.length];  // 记录哪些点已经被选走，初始全是 false

            for (int round = 0; round < k; round++) {  // 一共选 k 次
                double best = Double.MAX_VALUE;        // 当前最小距离，先设成"无穷大"
                int bestIndex = -1;                    // 当前最小距离对应的下标
                for (int i = 0; i < dist.length; i++) {
                    if (!used[i] && dist[i] < best) {  // 没被选过，而且比当前最小还小
                        best = dist[i];
                        bestIndex = i;
                    }
                }
                idx[round] = bestIndex;
                used[bestIndex] = true;                // 这一轮选中的下标，下一轮不再参与比较
            }
            // 第三步：看三个距离最近点的标签，投票决定预测结果
            int[] vote = new int[3];                   // 下标 1 和 2 各占一格
            for (int r = 0; r < k; r++) {
                vote[labels[idx[r]]]++;
            }

            System.out.println("最近三个点的下标:" + idx[0] + "、" + idx[1] + "、" + idx[2]);
            System.out.println("它们对应的标签:" + labels[idx[0]] + "、" + labels[idx[1]] + "、" + labels[idx[2]]);
            System.out.println("类别1得" + vote[1] + "票，类别2得" + vote[2] + "票");

            if (vote[1] > vote[2]) {
                System.out.println("预测类别:1");
            } else if (vote[2] > vote[1]) {
                System.out.println("预测类别:2");
            } else {
                System.out.println("票数相同，无法判断");
            }
        }
        // 两点间欧氏距离计算公式
        public static double distance(double x1, double y1, double x2, double y2) {
            double sum = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
            return Math.sqrt(sum);
        }
}

