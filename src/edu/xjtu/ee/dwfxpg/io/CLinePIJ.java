package edu.xjtu.ee.dwfxpg.io;

public class CLinePIJ {
    public int sid;
    public int eid;
    public double pij;

    public CLinePIJ(int sid, int eid, double pij) {
        this.sid = sid;
        this.eid = eid;
        this.pij = pij;
    }

    public int getSid() {
        return sid;
    }

    public int getEid() {
        return eid;
    }

    public double getPij() {
        return pij;
    }
}
