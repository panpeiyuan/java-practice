package Bagging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BaggingClassifier {
    private int treecount;
    private ArrayList<TreeNode> trees;//存每棵树的根节点
    private C45DecisionTree treebuilder;//用来建树的对象
    private ArrayList<Integer> avaliabelattribute;//可用的建树属性
    private long seed;//随机种子，保证准确率结果能复现

    public BaggingClassifier(int treecount, C45DecisionTree treebuilder, ArrayList<Integer> avaliabelattribute, long seed) {
        this.treecount = treecount;
        this.trees=new ArrayList<>();
        this.treebuilder = treebuilder;
        this.avaliabelattribute = avaliabelattribute;
        this.seed = seed;
    }

    public ArrayList<TreeNode> train(ArrayList<BaggingSample> samplelist){
        //每次训练时都从新建树
        trees=new ArrayList<>();
        //固定化一个random随机对象，使结果可以复现
        Random random=new Random(seed);
        //循环treecount次，每次都使用bootstrap和seed随机生成一个训练数组，根据训练数组训练树，存入trees中
        for(int i=0;i<treecount;i++){
            ArrayList<BaggingSample> randomsample=BootStrapSampler.bootstrapsampling(samplelist,random);
            TreeNode tree=treebuilder.buildtree(randomsample,avaliabelattribute);
            trees.add(tree);
        }
        return trees;
    }

    //bagging的预测方法
    public int predict(BaggingSample sample){
        //创建一个map存储所有预测标签的票数
        Map<Integer,Integer> labelvote=new HashMap<>();
        for (TreeNode tree : trees) {
            int prelabel=treebuilder.predict(tree,sample);
            if(labelvote.containsKey(prelabel)){
                int votes=labelvote.get(prelabel);
                votes++;
                labelvote.put(prelabel,votes);
            }else{
                labelvote.put(prelabel,1);
            }
        }
        int maxvote=0;
        int mostlabel=-1;
        for (Integer label : labelvote.keySet()) {
            if(labelvote.get(label)>maxvote){
                maxvote=labelvote.get(label);
                mostlabel=label;
            }else if(labelvote.get(label)==maxvote&&label<mostlabel){//平票时选择类别编号更小的那个
                mostlabel=label;
            }
        }
        return mostlabel;
    }
}
