package DecesionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    //对于决策树节点的要求和构造方法
    //决策树的一个节点，内部节点存放划分属性和字数，叶节点存类别
    private boolean isLeaf;//判断是否为叶节点
    private int label;//标签
    private int attribute;//属性
    private double gain;//信息增益
    private int fallbacklabel;//数量最多的样本的所属类别
    private Map<Integer,TreeNode> children;
    private ArrayList<Integer> nodelist;//存节点t下的所有样本下标

    public TreeNode(int label,ArrayList<Integer> nodelist) {//叶节点的构造方法
        //造叶节点
        isLeaf=true;
        this.label = label;
        this.nodelist=nodelist;
    }

    public TreeNode(int attribute, double gain, int fallbacklabel, ArrayList<Integer> nodelist) {//内部节点的构造方法
        //造内部节点
        //！！注意：fallbacklabel的作用是在预测时，若遇到了从没见过的类别，返回的就是fallbacklabel
        isLeaf=false;
        this.attribute = attribute;
        this.gain = gain;
        this.fallbacklabel = fallbacklabel;
        children=new HashMap<>();
        this.nodelist=nodelist;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public int getFallbacklabel() {
        return fallbacklabel;
    }

    public void setFallbacklabel(int fallbacklabel) {
        this.fallbacklabel = fallbacklabel;
    }

    public Map<Integer, TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Integer, TreeNode> children) {
        this.children = children;
    }

    public ArrayList<Integer> getNodelist() {
        return nodelist;
    }

    public void setNodelist(ArrayList<Integer> nodelist) {
        this.nodelist = nodelist;
    }
}
