package DecesionTree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReader {
    public static ArrayList<Sample> csvreader(File file) throws Exception {
        Scanner sc = new Scanner(file);
        ArrayList<Sample> samplelist = new ArrayList<>();
        int attributenumber = 0;
        while (sc.hasNextLine()) {//每循环依次就向samplelist集合中添加一个Sample对象
            String line = sc.nextLine();
            String[] parts = line.split(",");
            attributenumber = parts.length - 1;//减去最后一列的label
            int[] attvalue = new int[attributenumber];
            for (int i = 0; i < attvalue.length; i++) {
                attvalue[i] = Integer.parseInt(parts[i]);
            }
            int samplelabel=Integer.parseInt(parts[parts.length - 1]);
            Sample s=new Sample(attvalue,samplelabel);
            samplelist.add(s);
        }
        sc.close();
        return samplelist;
    }
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/hay.csv");
        ArrayList<Sample> sample = CsvReader.csvreader(file);
        System.out.println("样本数有" + sample.size() + "个");
        int attnumber = sample.get(1).getAttributevalue().length;//从s中选取一个对象，调用第一个对象的属性值数组，然后统计属性值数组的长度
        System.out.println("属性值有" + attnumber + "个");
    }
}
