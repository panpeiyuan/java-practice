package ClassificationAlgorithm.PreProcessing;

import ClassificationAlgorithm.Data.DataSchema;
import ClassificationAlgorithm.Data.DataSet;
import ClassificationAlgorithm.Data.Sample;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Standardize {
    private DataSchema schema;
    private double[] means;//每个连续属性的训练集均值
    private double[] standardDeviations;//每个连续属性的训练集标准差
    private ArrayList<Map<Integer,Integer>> categoryIndexMaps;//每个离散属性的类别值到one-hot相对位置的映射
    private int[] outputoffsets;//每个原始属性编码后的起始位置
    private int outputFeaturecount;//编码后属性的double[]长度
    public void fit(DataSet trainSet){
        schema=trainSet.getSchema();
        int attnum=trainSet.getAttnumber();
        this.means=new double[attnum];
        this.standardDeviations=new double[attnum];
        this.categoryIndexMaps=new ArrayList<>();
        this.outputFeaturecount=0;
        this.outputoffsets=new int[attnum];
        ArrayList<Sample> trainsamplelist=trainSet.getSamplelist();
        this.categoryIndexMaps=new ArrayList<>();
        for(int i=0;i<attnum;i++){
            categoryIndexMaps.add(null);
        }

        for(int i=0;i<attnum;i++){
            outputoffsets[i]=outputFeaturecount;
            //如果该属性为连续值
            if(schema.isNumeric(i)){
                double sum=0;
                for (Sample sample : trainsamplelist) {
                    sum+=sample.getNumericValue(i);
                }
                means[i]=sum/trainsamplelist.size();
                double standersum=0;
                for (Sample sample : trainsamplelist) {
                    standersum+=(sample.getNumericValue(i)-means[i])*(sample.getNumericValue(i)-means[i]);
                }
                standardDeviations[i]=Math.sqrt(standersum/(trainsamplelist.size()-1));
                outputFeaturecount++;
            }
            //如果该属性为离散值
            if(schema.isCategorical(i)){
                Map<Integer,Integer> categoryindexmap=new TreeMap<>();
                for (Sample sample : trainsamplelist) {
                    int label=sample.getCategoricalValue(i);
                    categoryindexmap.put(label,0);
                }
                //把离散的类别按顺序排列。eg：1,3,7->0,1,2
                int j=0;
                for (Integer label : categoryindexmap.keySet()) {
                    categoryindexmap.put(label,j);
                    j++;
                }
                categoryIndexMaps.set(i,categoryindexmap);
                outputFeaturecount+=categoryindexmap.size();
            }
        }
    }

    //得到编码后的样本属性列表长度
    public int getOutputFeaturecount() {
        return outputFeaturecount;
    }

    //对一个样本，生成他编码后的一维属性数组，并填入编码后的数据
    public double[] transform(Sample sample){
        double[] ended=new double[outputFeaturecount];
        for (int i = 0; i < sample.getValues().length; i++) {
            if(schema.isNumeric(i)){
                double sampleattvalue=sample.getNumericValue(i);
                ended[outputoffsets[i]]=(sampleattvalue-means[i])/standardDeviations[i];
            }
            if(schema.isCategorical(i)){
                int sampleattvalue= sample.getCategoricalValue(i);
                Map<Integer,Integer> labelmapping =categoryIndexMaps.get(i);
                Integer relativelabel=labelmapping.get(sampleattvalue);
                if(relativelabel!=null){
                    ended[outputoffsets[i]+relativelabel]=1;
                }
            }
        }
        return ended;
    }
}
