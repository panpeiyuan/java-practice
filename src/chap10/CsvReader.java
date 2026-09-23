package chap10;

import java.io.File;
import java.util.Scanner;

public class CsvReader {
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
        int[] vote = new int[4];
        for (int i = 0; i < labels.length; i++) {
            vote[labels[i]]++;
        }
        System.out.println("行数为:" + row);
        System.out.println("列数为:" + data[0].length);
        System.out.println("类别1得" + vote[1] + "票，类别2得" + vote[2] + "票" + "，类别3得" + vote[3] + "票");
    }
}

