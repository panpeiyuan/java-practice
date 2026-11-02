package ClassificationAlgorithm.Data;

public class DataSchema {
    //表示列数和每列的数据类型
    private final FeatureType[] featureTypes;

    public DataSchema(FeatureType[] featureType){
        if(featureType==null){
            throw new IllegalArgumentException("属性类别数组不能为空");
        }
        if(featureType.length==0){
            throw new IllegalArgumentException("属性数量不能为0");
        }
        FeatureType[] newFeatureTypes=new FeatureType[featureType.length];
        for (int i = 0; i < newFeatureTypes.length; i++) {
            if (featureType[i] == null) {
                throw new IllegalArgumentException("第"+(i+1)+"个属性类型不能为空");
            }
            newFeatureTypes[i] = featureType[i];
        }
        this.featureTypes = newFeatureTypes;
    }
    //得到属性数量
    public int getFeatureCount(){
        return featureTypes.length;
    }
    //得到第i个位置的属性的类型
    public FeatureType getFeatureType(int index){
        return featureTypes[index];
    }
    //判断该位置的属性类型是否为Numeric
    public boolean isNumeric(int index){
        return featureTypes[index]==FeatureType.NUMERIC;
    }
    //判断该位置的属性类型是否为Categorical
    public boolean isCategorical(int index){
        return featureTypes[index]==FeatureType.CATEGORICAL;
    }
}
