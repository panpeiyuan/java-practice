package NeuralNetWork;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CsvReader {
    public static ArrayList<NeuralnetSample> csvreader(File file) throws Exception {
        Scanner sc = new Scanner(file);
        ArrayList<NeuralnetSample> samplelist = new ArrayList<>();
        int attributenumber = 0;
        while (sc.hasNextLine()) {//每循环依次就向samplelist集合中添加一个Sample对象
            String line = sc.nextLine();
            if(line.trim().isEmpty()){//读取的时候跳过空行（在当前数据集下没什么用，因为没有空行）
                continue;
            }
            String[] parts = line.split(",");
            attributenumber = parts.length - 1;//减去最后一列的label
            double[] sampleattvalue=new double[attributenumber];
            for (int i = 0; i < attributenumber; i++) {
                sampleattvalue[i]=Double.parseDouble(parts[i].trim());
            }
            int samplelabel= parseInt(parts[parts.length - 1]);
            //做二分类，只保留1，2类别,标签1编码为1，标签2编码为0
            if(samplelabel==1){
                NeuralnetSample n=new NeuralnetSample(sampleattvalue,samplelabel);
                samplelist.add(n);
            }
            if(samplelabel==2){
                NeuralnetSample n=new NeuralnetSample(sampleattvalue,0);
                samplelist.add(n);
            }
        }
        sc.close();
        return samplelist;//返回一个ArrayList数组，这个数组添加的是Neuralnetsample对象
    }
}
