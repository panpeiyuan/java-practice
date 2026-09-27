package chap12ObjectOrientedGeneralPractice;

public class Goods {
    private String goodid;
    private String goodname;
    private double goodprice;
    private int goodinventory;
    public Goods(String goodid, String goodname, double goodprice, int goodinventory) {
        this.goodid = goodid;
        this.goodname = goodname;
        this.goodprice = goodprice;
        this.goodinventory = goodinventory;
    }

    public Goods() {
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public void setGoodprice(double goodprice) {
        this.goodprice = goodprice;
    }

    public void setGoodinventory(int goodinventory) {
        this.goodinventory = goodinventory;
    }

    public String getGoodid() {
        return goodid;
    }

    public String getGoodname() {
        return goodname;
    }

    public double getGoodprice() {
        return goodprice;
    }

    public int getGoodinventory() {
        return goodinventory;
    }
}
