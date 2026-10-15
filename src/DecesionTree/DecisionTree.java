package DecesionTree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DecisionTree {
    private DataSet dataset;

    public DecisionTree(DataSet dataset) {
        this.dataset = dataset;
    }

    public Map<Integer,Integer> countLabels(ArrayList<Integer> list){
        //输出每个类别有多少个样本的方法
        Map<Integer,Integer> hm=new HashMap<>();
        for (Integer i : list) {
            int label=dataset.get(i).getLabel();
            if(hm.containsKey(label)){
                int count=hm.get(label);
                count++;
                hm.put(label,count);
            }else{
                hm.put(label,1);
            }
        }
        return hm;
    }
    public double entropy(ArrayList<Integer> list){
        //给定一组下标（因为十折交叉给的就是数据的下表在数组中的下表），根据下表找到对应的sample，然后调用countlabels方法将所有数据按类别分类，然后算熵
        Map<Integer,Integer> hm=countLabels(list);
        int ASN=list.size();
        double entropy=0;
        Set<Integer> key=hm.keySet();
        for (Integer i : key) {
            double Pi=(double)hm.get(i)/ASN;
            entropy+=Pi*Math.log(Pi)/Math.log(2);
        }
        return -entropy;
    }
    public ArrayList<Integer> splitbyvalue(ArrayList<Integer> indexlist,int a,int v){
        //挑出样本序列中属性a的取值是v的下标编号
        ArrayList<Integer> list=new ArrayList<>();
        for (Integer i : indexlist) {
            Sample s=dataset.get(i);//s表示一个对象
            int tempvalue=s.getvalue(a);//找到该样本的第a个属性值
            if(tempvalue==v){
                list.add(i);//返回一个下标编号
            }
        }
        return list;
    }
    public double gain(ArrayList<Integer> list,int a){
        //给定一组样本下标，然后给定一组属性，计算这组样本在这个属性下的信息增益
        double ent=entropy(list);
        Set<Integer> s=dataset.getDomain(a);
        double cond=0;
        for (Integer i : s) {
            ArrayList<Integer> Dv=splitbyvalue(list,a,i);
            double w=(double)Dv.size()/list.size();
            cond+=w*entropy(Dv);
        }
        return ent-cond;
    }
    public int chooseBestAttribute(ArrayList<Integer> indexlist,ArrayList<Integer> attlist){
        //根据信息增益找最优分类属性
        int bestatt=-1;
        double maxgain=-1;
        for (Integer i : attlist) {
            double tempgain=gain(indexlist,i);
            if(tempgain>maxgain){
                maxgain=tempgain;
                bestatt=i;
            }
        }
        return bestatt;
    }
    public int majorlabel(ArrayList<Integer> list){
        //给定一组样本下标，返回出现次数最多的类别
        Map<Integer,Integer> map=countLabels(list);
        int mostvote=0;
        int mostvoteindex=-1;
        Set<Integer> s=map.keySet();
        for (Integer i : s) {
            if(map.get(i)>mostvote){
                mostvote=map.get(i);
                mostvoteindex=i;
            }
        }
        return mostvoteindex;
    }
    public TreeNode buildtree(ArrayList<Integer> list,ArrayList<Integer> remainattribute){
        //创建叶节点（递归结束）的三个情况
        if(countLabels(list).size()==1){//样本输进去，只有一个类别
            return new TreeNode(majorlabel(list),list);
        }else if(remainattribute.size()==0){//已经没有可用的分类属性了（即存在所有属性都相同但类别不同的样本，此时叶节点取占比多的）
            return new TreeNode(majorlabel(list),list);
        }
        boolean allsame=true;
        for (Integer i : remainattribute) {//判断剩下的样本是不是都属于同一类别
            int first=dataset.get(list.get(0)).getvalue(i);
            for (Integer a : list) {
                if(dataset.get(a).getvalue(i)!=first){
                    allsame=false;
                    break;
                }
            }
            if(!allsame){
                break;
            }
        }
        if(allsame){//例如：一共三个属性，但是用完前两个属性分类的所有样本都属于同一类型了，就不需要再用第三个属性进行筛选了，直接创建叶节点即可
            return new TreeNode(majorlabel(list),list);
        }
        //创建内部节点的方法
        int bestatt=chooseBestAttribute(list,remainattribute);//根据信息增益找最优划分属性
        TreeNode node=new TreeNode(bestatt,gain(list,bestatt),majorlabel(list),list);//构造内部节点
        ArrayList<Integer> rest=new ArrayList<>(remainattribute);//把remainattribute集合里的元素拷贝到rest集合里
        rest.remove(Integer.valueOf(bestatt));//把bestatt变成Integer对象类型，使用remove按值删（若不转为Integer，使用bastatt的int类型的话，remove是按位置删的）
        for (Integer v : dataset.getDomain(bestatt)) {//getdomain(a)方法返回一个Set<>集合,并遍历这个集合
            ArrayList<Integer> Dv=splitbyvalue(list,bestatt,v);//属性bestatt取值是v的所有样本下标
            if(Dv.size()==0){//若该属性取值下没有样本
                continue;//跳过此次for循环，不执行下一步的建节点
            }
            node.getChildren().put(v,buildtree(Dv,rest));//调取一个Map集合，循环建树
        }
        return node;
    }
    public void printtree(TreeNode node,int level){
        //无用，就是看看树的最终结构
        String indent=" ";
        for(int i=0;i<level;i++){
            indent+=" ";
        }
        if(node.isLeaf()){
            System.out.println(indent+"类别为:"+node.getLabel());
            return;
        }
        System.out.println(indent+"属性为:"+node.getAttribute()+"信息增益为:"+node.getGain());
        for (Map.Entry<Integer, TreeNode> entry : node.getChildren().entrySet()) {
            System.out.println(indent+"取值"+entry.getKey()+"->");
            printtree(entry.getValue(),level+1);
        }
    }
    public int predict(TreeNode node,int[] arr){
        //拿一个建好的树，喂进去一个样本，输出这个样本该判为哪一类
        while(!node.isLeaf()){
            TreeNode next=node.getChildren().get(arr[node.getAttribute()]);
            if(next==null){//因为上面buildtree方法里写了，若该属性取值下没有样本，则不建树
                return node.getFallbacklabel();
            }
            node=next;
        }
        return node.getLabel();
    }
    public double emrntoy(TreeNode node){//输入某一个节点，输出该节点的经验熵
        //后剪枝,只要知道节点t下各类别样本各多少条即可
        ArrayList<Integer> arr=node.getNodelist();
        double entoy=entropy(arr);//该节点的经验熵
        return entoy;
    }
    public int leafnum(TreeNode tree){//求这一颗树的叶节点个数
        if(tree.isLeaf()){
            return 1;
        }
        int total =0;
        for (Map.Entry<Integer, TreeNode> integerTreeNodeEntry : tree.getChildren().entrySet()) {
                total+=leafnum(integerTreeNodeEntry.getValue());
        }
        return total;
    }
    public double losingfunction(TreeNode tree){//输入一个树,递归计算每个节点的熵,最后求得整个树的损失函数
        if(tree.isLeaf()){
            return tree.getNodelist().size()*entropy(tree.getNodelist());
        }
        double sum=0;
        for (Map.Entry<Integer, TreeNode> integerTreeNodeEntry : tree.getChildren().entrySet()) {
            sum+=losingfunction(integerTreeNodeEntry.getValue());
        }
        return sum;
    }
    public TreeNode postpruning(TreeNode tree,double a){
        //决策树剪枝方法
         if(tree.isLeaf()){
             return tree;
         }
        for (Map.Entry<Integer, TreeNode> integerTreeNodeEntry : tree.getChildren().entrySet()) {
            integerTreeNodeEntry.setValue(postpruning(integerTreeNodeEntry.getValue(),a));
        }
        double lostcut=tree.getNodelist().size()*emrntoy(tree)+a;//单独算该内部节点的损失函数
        double lostkeep=losingfunction(tree)+a*leafnum(tree);//算该内部节点下面叶节点的损失函数
        if(lostcut<=lostkeep){
            TreeNode newtreenode=new TreeNode(tree.getFallbacklabel(),tree.getNodelist());//把该点存为叶节点
            return newtreenode;
        }
        return tree;
    }
}
