package edu.xjtu.ee.dwfxpg.io;

/**
 * Created by Administrator on 2018/6/4.
 */
public class BusMsg {
    public int id;
    public String msg1;
    public int status;

    public BusMsg(int id, String msg1, int status) {
        this.id = id;
        this.msg1 = msg1;
        this.status = status;
    }
}
