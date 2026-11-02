package ClassificationAlgorithm.Data;

public class Sample {
    //描述单个样本
    private final DataSchema schema;
    private final double[] values;//属性取值
    private final int label;//标签

    public Sample(DataSchema schema, double[] values, int label) {
        if(schema==null){
            throw new IllegalArgumentException("数据模式不能为空");
        }
        if(values==null){
            throw new IllegalArgumentException("样本属性值不能为空");
        }
        if(values.length!=schema.getFeatureCount()){
            throw new IllegalArgumentException("该样本属性个数与数据集属性个数不符");
        }
        this.schema = schema;
        this.label = label;
        double[] newValues=new double[values.length];
        for (int i = 0; i < newValues.length; i++) {
            double value=values[i];
            if(Double.isNaN(value)||Double.isInfinite(value)){
                throw new IllegalArgumentException("第"+(i+1)+"个属性值必须是有意义的数");
            }
            if(schema.isCategorical(i)){
                if(value!=Math.rint(value)){
                    throw new IllegalArgumentException("第"+(i+1)+"个离散属性值必须是整数");
                }
                if(value<Integer.MIN_VALUE||value>Integer.MAX_VALUE){
                    throw new IllegalArgumentException("第"+(i+1)+"个离散属性值超出int范围");
                }
            }
            newValues[i]=value;
        }
        this.values=newValues;
    }
    //读取连续属性值的方法
    public double getNumericValue(int index){
        if(!schema.isNumeric(index)){
            throw new IllegalArgumentException("第"+(index+1)+"个属性是离散属性，不能按连续属性读取");
        }
        return values[index];
    }
    //读取离散属性值的方法
    public int getCategoricalValue(int index){
        if(!schema.isCategorical(index)){
            throw new IllegalArgumentException("第"+(index+1)+"个属性是连续属性，不能按离散属性读取");
        }
        return (int)values[index];
    }
    //得到该样本的属性数量
    public int getFeatureCount(){
        return values.length;
    }
    //读取标签的方法
    public int getLabel(){
        return label;
    }
    //读取当前样本使用的数据类型
    public DataSchema getSchema(){
        return schema;
    }

    public double[] getValues() {
        return values;
    }
}
