package Bagging;

import java.util.ArrayList;

public class TreeNode {
    private boolean isleaf;
    private int label;
    private int attributeindex;//属性下标
    private double threshold;//二分类阈值
    private TreeNode leftTree;//左子节点
    private TreeNode rightTree;
    private int fallback;//多数类
    private ArrayList<BaggingSample> samplelist;//存该节点下的所有样本

    //叶节点构造方法
    public TreeNode(int label,ArrayList<BaggingSample> samplelist){
        isleaf=true;
        this.label=label;
        this.samplelist=samplelist;
    }

    //中间节点构造方法
    public TreeNode(int attributeindex,double threshold,int fallback,ArrayList<BaggingSample> samplelist){
        isleaf=false;
        this.attributeindex=attributeindex;
        this.threshold=threshold;
        this.fallback=fallback;
        this.samplelist=samplelist;
    }

    //所有get和set方法

    public boolean isIsleaf() {
        return isleaf;
    }

    public void setIsleaf(boolean isleaf) {
        this.isleaf = isleaf;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getAttributeindex() {
        return attributeindex;
    }

    public void setAttributeindex(int attributeindex) {
        this.attributeindex = attributeindex;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public TreeNode getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(TreeNode leftTree) {
        this.leftTree = leftTree;
    }

    public TreeNode getRightTree() {
        return rightTree;
    }

    public void setRightTree(TreeNode rightTree) {
        this.rightTree = rightTree;
    }

    public int getFallback() {
        return fallback;
    }

    public void setFallback(int fallback) {
        this.fallback = fallback;
    }

    public ArrayList<BaggingSample> getSamplelist() {
        return samplelist;
    }

    public void setSamplelist(ArrayList<BaggingSample> samplelist) {
        this.samplelist = samplelist;
    }
}

