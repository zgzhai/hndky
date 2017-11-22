package edu.xjtu.ee.dwfxpg;

public class CLine {
    public int sid;
    public int eid;
    public int sn;
    public double resistance;
    public double capacity;

    /**
     * 直流潮流算法时使用
     * @param sid
     * @param eid
     * @param sn
     * @param resistance
     */
    public CLine(int sid, int eid, int sn, double resistance) {
        this.sid = sid;
        this.eid = eid;
        this.sn = sn;
        this.resistance = resistance;
    }

    /**
     * 负荷消减算法时使用
     * @param sid
     * @param eid
     * @param sn
     * @param resistance
     * @param capacity
     */
    public CLine(int sid, int eid, int sn, double resistance, double capacity) {
        this.sid = sid;
        this.eid = eid;
        this.sn = sn;
        this.resistance = resistance;
        this.capacity = capacity;
    }
}
