package AdaBoost;

import java.util.Map;

public class OvrAdaboostSample {
    //存三分类模型
    private Map<Integer,BinaryAdaBoostSample> OVRadaboostmodel;

    public OvrAdaboostSample(Map<Integer, BinaryAdaBoostSample> OVRadaboostmodel) {
        this.OVRadaboostmodel = OVRadaboostmodel;
    }

    public Map<Integer, BinaryAdaBoostSample> getOVRadaboostmodel() {
        return OVRadaboostmodel;
    }

    public void setOVRadaboostmodel(Map<Integer, BinaryAdaBoostSample> OVRadaboostmodel) {
        this.OVRadaboostmodel = OVRadaboostmodel;
    }
}
