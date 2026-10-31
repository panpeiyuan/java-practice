package Kmeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CsvReader {
    public static ArrayList<KmeansSample> csvreader(File file) throws Exception {
        Scanner sc = new Scanner(file);
        ArrayList<KmeansSample> samplelist = new ArrayList<>();
        int attributenumber = 0;
        while (sc.hasNextLine()) {//每循环依次就向samplelist集合中添加一个Sample对象
            String line = sc.nextLine();
            String[] parts = line.split(",");
            attributenumber = parts.length - 1;//减去最后一列的label
            double[] attribute=new double[attributenumber];
            for (int i = 0; i < attributenumber; i++) {
                attribute[i]=Double.parseDouble(parts[i]);
            }
            int samplelabel= parseInt(parts[parts.length - 1]);
            KmeansSample sample=new KmeansSample(attribute,samplelabel);
            samplelist.add(sample);
        }
        sc.close();
        return samplelist;//返回一个ArrayList数组，这个数组添加的是Adaboost对象
    }
}
