package edu.xjtu.ee.dwfxpg.io;

/**
 * Created by Administrator on 2018/6/4.
 */
public class LineMsg {
    public int sid;
    public int eid;
    public String msg1;
    public int status;

    public LineMsg(int sid, int eid, String msg1, int status) {
        this.sid = sid;
        this.eid = eid;
        this.msg1 = msg1;
        this.status = status;
    }

    public int getSid() {
        return sid;
    }

    public int getEid() {
        return eid;
    }

    public String getMsg1() {
        return msg1;
    }

    public int getStatus() {
        return status;
    }
}
