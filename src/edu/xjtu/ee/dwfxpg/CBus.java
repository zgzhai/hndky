package edu.xjtu.ee.dwfxpg;

/**
 * 定义母线节点
 */
public class CBus {
    public  static final int TYPE1 = 1;
    public  static final int TYPE2 = 2;
    public  static final int TYPE3 = 3;

    public int id;
    public double capacity;
    public int type;

    public CBus(int id, double capacity, int type) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
    }
}
