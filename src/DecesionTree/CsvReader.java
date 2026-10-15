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
        return samplelist;//返回一个ArrayList数组，这个数组添加的是sample对象
    }
}
