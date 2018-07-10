package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.dwfxpg.io.OFHXJ;
import edu.xjtu.ee.dwfxpg.io.OPQ;
import edu.xjtu.ee.dwfxpg.io.OZLCL;
import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.unisolver.FXPG.OFXPG;

public class UniResult {
    public int errcode = 0;
    public String errmsg;
    public String mode;
    public OCool oCool;
    public OHst oHst;
    public OZcfhnlpg oZcfhnlpg;
    public OCqfhnlpg oCqfhnlpg;
    public ODqfhnlpg oDqfhnlpg;
    public OFhzt oFhzt;
    public OZLCL oZlcl;
    public OFHXJ oFhxj;
    public OPQ oPq;
    public OFXPG oFxpg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public OCool getoCool() {
        return oCool;
    }

    public void setoCool(OCool oCool) {
        this.oCool = oCool;
    }

    public OHst getoHst() {
        return oHst;
    }

    public void setoHst(OHst oHst) {
        this.oHst = oHst;
    }

    public OZcfhnlpg getoZcfhnlpg() {
        return oZcfhnlpg;
    }

    public void setoZcfhnlpg(OZcfhnlpg oZcfhnlpg) {
        this.oZcfhnlpg = oZcfhnlpg;
    }

    public OCqfhnlpg getoCqfhnlpg() {
        return oCqfhnlpg;
    }

    public void setoCqfhnlpg(OCqfhnlpg oCqfhnlpg) {
        this.oCqfhnlpg = oCqfhnlpg;
    }

    public ODqfhnlpg getoDqfhnlpg() {
        return oDqfhnlpg;
    }

    public void setoDqfhnlpg(ODqfhnlpg oDqfhnlpg) {
        this.oDqfhnlpg = oDqfhnlpg;
    }

    public OFhzt getoFhzt() {
        return oFhzt;
    }

    public void setoFhzt(OFhzt oFhzt) {
        this.oFhzt = oFhzt;
    }

    public OZLCL getoZlcl() {
        return oZlcl;
    }

    public void setoZlcl(OZLCL oZlcl) {
        this.oZlcl = oZlcl;
    }

    public OFHXJ getoFhxj() {
        return oFhxj;
    }

    public void setoFhxj(OFHXJ oFhxj) {
        this.oFhxj = oFhxj;
    }

    public OPQ getoPq() {
        return oPq;
    }

    public void setoPq(OPQ oPq) {
        this.oPq = oPq;
    }

    public OFXPG getoFxpg() {
        return oFxpg;
    }

    public void setoFxpg(OFXPG oFxpg) {
        this.oFxpg = oFxpg;
    }
}
