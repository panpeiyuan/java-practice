package DBSCAN;

import java.util.ArrayList;

public class DBSCANAlgorithm {
    private DataSet dataSet;
    private double eps;//表示邻域半径
    private int minPts;//表示判定核心样本时，邻域内包含的最小样本数
    private boolean[] visted;//存储第i个样本有没有被处理过
    private int[] clusterAssignments;//存储每个元素分配到的簇标签
    private int UnAssigned;//表示未被分配的样本
    private int Noise;//表示噪声

    public DBSCANAlgorithm(DataSet dataSet,double eps, int minPts) {
        this.dataSet=dataSet;
        this.eps = eps;
        this.minPts = minPts;
        this.visted=new boolean[dataSet.getSamplelist().size()];
        //初始化visted为false
        for (int i = 0; i < visted.length; i++) {
            visted[i]=false;
        }
        //初始化clusterAssignmennts为-1
        this.clusterAssignments=new int[dataSet.getSamplelist().size()];
        for (int i = 0; i < clusterAssignments.length; i++) {
            clusterAssignments[i]=-1;
        }
        this.UnAssigned=-1;
        this.Noise=-2;
    }

    //找出与下标为i的样本距离不大于eps的所有样本的下标
    public ArrayList<Integer> findNeighbors(int sampleindex){
        ArrayList<Integer> neighborsamplesindex=new ArrayList<>();
        ArrayList<DBSCANSample> samplelist=dataSet.getSamplelist();
        double[] sampleindexvalue=dataSet.getSamplelist().get(sampleindex).getAttribute();
        for (int i = 0; i < samplelist.size(); i++) {
            double[] samplevalue=samplelist.get(i).getAttribute();
            double distance=Distance.euclidean(samplevalue,sampleindexvalue);
            if(distance<=eps){
                neighborsamplesindex.add(i);
            }
        }
        return neighborsamplesindex;
    }

    //从一个核心点出发，不断把“密度可达”的样本加入当前簇，直到没有新的候选点(clusterid表示规定的以sampleindex为中心的簇编号）
    public void expandCluster(int sampleindex,ArrayList<Integer> neighbors,int clusterid){
        //处理中心点位置
        visted[sampleindex]=true;//标记已访问
        clusterAssignments[sampleindex]=clusterid;//分配簇编号
        //复制neighbors数组
        ArrayList<Integer> candidates=new ArrayList<>();
        for (int i = 0; i < neighbors.size(); i++) {
            int tempt=neighbors.get(i);
            candidates.add(tempt);
        }
        for(int position=0;position<candidates.size();position++){
            //q是方法中sampleindex的所有邻居
            int q=candidates.get(position);
            //若q点未被访问，标记访问并查询q的邻域
            if(visted[q]==false){
                visted[q]=true;
                ArrayList<Integer> qneighbors=findNeighbors(q);
                //如果q的核心点，就把他的每个邻居下标index加入candidates，加入前检查是否已经有了
                if(qneighbors.size()>=minPts){
                    for (int i = 0; i < qneighbors.size(); i++) {
                        int qneighborindex=qneighbors.get(i);
                        if(!candidates.contains(qneighborindex)){
                            candidates.add(qneighborindex);
                        }
                    }
                }
            }
            //若q点未被分配，则分配为clusterid
            if(clusterAssignments[q]==UnAssigned){
                clusterAssignments[q]=clusterid;
            }
        }
    }

    //DBSCAN簇划分方法
    public void fit(){
        //初始化簇类别编码;
        int clusterID=0;
        //未访问的点才有可能作为新簇起点
        for (int i = 0; i < visted.length; i++) {
            //访问过的点不再访问
            if(visted[i]==true){
                continue;
            }
            visted[i]=true;
            ArrayList<Integer> neighbors=findNeighbors(i);
            if(neighbors.size()<minPts){
                clusterAssignments[i]=UnAssigned;
            }else{
                //若该点为核心点，则对中心点进行扩展，并划分簇类
                expandCluster(i,neighbors,clusterID);
                clusterID++;//更新簇类别
            }
        }
        //遍历完一遍样本并进行划分后，还未有簇类别的样本标记为noise
        for (int i = 0; i < clusterAssignments.length; i++) {
            if(clusterAssignments[i]== UnAssigned){
                clusterAssignments[i]=Noise;
            }
        }
    }

    //读取所有样本簇类别的方法
    public int[] getClusterAssignments() {
        return clusterAssignments;
    }
}
