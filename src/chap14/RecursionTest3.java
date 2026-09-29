package chap14;

public class RecursionTest3 {
    public static void main(String[] args){
        //迷宫问题
        //1.创建迷宫，用二维数组表示
        //2.先规定数组的元素值，0表示可以走，1表示不能走
        int[][] map=new int[8][7];
        //3.将最上面一行和最下面一行,最左面一列和最右面一列，全部设置为1
        for(int i=0;i<map[0].length;i++){
            map[0][i]=1;
        }
        for(int i=0;i<map[7].length;i++){
            map[7][i]=1;
        }
        for(int i=0;i<map.length;i++){
            map[i][0]=1;
        }
        for(int i=0;i<map.length;i++){
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
        map[2][2]=1;//测试回溯
        System.out.println("======当前地图情况======");
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        //使用find给老鼠找路
        T t=new T();
        t.findway(map,1,1);
        System.out.println("======找路的情况如下======");
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }


    }
}
class T {
    //使用递归回溯的思维来解决老鼠出迷宫的路径
    //如果找到，返回true；未找到，返回false
    //老鼠初始位置为数组（1，1），（6，5）为终点
    //先规定map数组的各个值的含义：0表示可以走，1表示障碍物，2表示可以走，3表示走过，但是走不通是死路
    //当map【6】【5】==2时，说明找到通路，否则继续找。
    //先确定老鼠找路的策略：下->右->上->左
    public boolean findway(int[][] map,int i,int j){//i,j为老鼠的初始位置
        if(map[6][5]==2){
            return true;
        }else if(map[i][j]==0){//表示当前位置为0，说明可以走
            //假定可以走通
            map[i][j]=2;
            //使用找路策略，来确定该位置是否真的可以走通
            if(findway(map,i+1,j)){
                return true;
            }else if(findway(map,i,j+1)){
                return true;
            }else if(findway(map,i-1,j)){
                return true;
            }else if(findway(map,i,j-1)){
                return true;
            }else{
                map[i][j]=3;
                return false;
            }
        }else{
            return false;
        }
    }
}
