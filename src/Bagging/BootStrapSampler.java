package Bagging;

import java.util.ArrayList;
import java.util.Random;

public class BootStrapSampler {
    //自主采样方法的工具类
    private BootStrapSampler(){};
    public static ArrayList<BaggingSample> bootstrapsampling(ArrayList<BaggingSample> samplelist, Random random){
        ArrayList<BaggingSample> bootstrapsamplelist=new ArrayList<>();
        int times=samplelist.size();
        for(int i=0;i<times;i++){
            int randomindex=random.nextInt(times);
            BaggingSample randomsample=samplelist.get(randomindex);
            bootstrapsamplelist.add(randomsample);
        }
        return bootstrapsamplelist;
    }
}
