package AdaBoost;

import java.util.ArrayList;

public class BinaryAdaBoost {
    //保存adaboost训练结果
    private ArrayList<Double> treeWeight;
    private ArrayList<TreeStumpNode> treelist;

    public BinaryAdaBoost(ArrayList<TreeStumpNode> treelist,ArrayList<Double> treeWeight) {
        this.treeWeight = treeWeight;
        this.treelist = treelist;
    }

    public ArrayList<Double> getTreeWeight() {
        return treeWeight;
    }

    public void setTreeWeight(ArrayList<Double> treeWeight) {
        this.treeWeight = treeWeight;
    }

    public ArrayList<TreeStumpNode> getTreelist() {
        return treelist;
    }

    public void setTreelist(ArrayList<TreeStumpNode> treelist) {
        this.treelist = treelist;
    }
}
