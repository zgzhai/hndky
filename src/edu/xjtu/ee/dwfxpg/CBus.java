package edu.xjtu.ee.dwfxpg;

/**
 * 定义母线节点
 */
public class CBus {
    public  static final int TYPE1 = 1;
    public  static final int TYPE2 = 2;
    public  static final int TYPE3 = 3;

    public int id;
    //public double capacity;
    public int type;
    public double U;   //节点电压
    public double a;   //节点相角
    public double P;   //节点有功
    public double Q;   //节点无功

    /*
    public CBus(int id, double capacity, int type) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
    }
    */

    public CBus(int id, int type) {
        this.id = id;
        this.type = type;
    }

    /**
     * PQ法节点输入
     * @param id
     * @param type
     */
    public CBus(int id, int type, double U, double a, double P, double Q) {
        this.id = id;
        this.type = type;
        this.U = U;
        this.a = a;
        this.P = P;
        this.Q = Q;
    }
}
