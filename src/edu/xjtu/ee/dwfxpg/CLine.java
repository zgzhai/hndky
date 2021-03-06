package edu.xjtu.ee.dwfxpg;

public class CLine {
    public int sid;
    public int eid;
    public int sn;
    public double resistance;
    public double capacity;
    public double Rij;        //线路电阻
    public double Xij;        //线路电抗
    public double B0;         //对地导纳
    public double RT;         //变压器电阻
    public double XT;         //变压器电抗
    public double KT;         //标准变比
    public double W;          //这算标志

    /**
     * 直流潮流算法时使用
     *
     * @param sid
     * @param eid
     * @param sn
     * @param resistance
     */
    /*
    public CLine(int sid, int eid, int sn, double resistance) {
        this.sid = sid;
        this.eid = eid;
        this.sn = sn;
        this.resistance = resistance;
    }
    */
    /**
     * 直流潮流法和负荷消减算法时使用
     *
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

    /**
     * PQ法分支输入
     */
    public CLine(int sid, int eid, double Rij, double Xij, double B0, double RT, double XT, double KT, double W, double capacity) {
        this.sid = sid;
        this.eid = eid;
        this.Rij = Rij;
        this.Xij = Xij;
        this.B0 = B0;
        this.RT = RT;
        this.XT = XT;
        this.KT = KT;
        this.W = W;
        this.capacity = capacity;
    }
}
