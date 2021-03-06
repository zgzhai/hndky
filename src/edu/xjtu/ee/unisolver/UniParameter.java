package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.dwfxpg.io.IDwfxpgDW;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgPQ;
import edu.xjtu.ee.fhnlpg.io.*;
import edu.xjtu.ee.unisolver.FXPG.IFXPG;

public class UniParameter {
    private IFhnlpgBase iFhnlpgBase;
    private IFhnlpgInitial iFhnlpgInitial;
    private IFhnlpgOnLoad iFhnlpgOnLoad;
    private IFhnlpgTRise iFhnlpgTRise;
    private IFhnlpgResistance iFhnlpgResistance;
    private IFhnlpgRatio iFhnlpgRatio;
    private IFhnlpgLimit iFhnlpgLimit;
    private IFhnlpgCool iFhnlpgCool;
    private IFhnlpgTStatus iFhnlpgTStatus;
    private IDwfxpgDW iDwfxpgDW;
    private IDwfxpgPQ iDwfxpgPQ;
    private IFXPG iFxpg;

    public UniParameter() {
    }

    public IFhnlpgBase getiFhnlpgBase() {
        return iFhnlpgBase;
    }

    public void setiFhnlpgBase(IFhnlpgBase iFhnlpgBase) {
        this.iFhnlpgBase = iFhnlpgBase;
    }

    public IFhnlpgInitial getiFhnlpgInitial() {
        return iFhnlpgInitial;
    }

    public void setiFhnlpgInitial(IFhnlpgInitial iFhnlpgInitial) {
        this.iFhnlpgInitial = iFhnlpgInitial;
    }

    public IFhnlpgOnLoad getiFhnlpgOnLoad() {
        return iFhnlpgOnLoad;
    }

    public void setiFhnlpgOnLoad(IFhnlpgOnLoad iFhnlpgOnLoad) {
        this.iFhnlpgOnLoad = iFhnlpgOnLoad;
    }

    public IFhnlpgTRise getiFhnlpgTRise() {
        return iFhnlpgTRise;
    }

    public void setiFhnlpgTRise(IFhnlpgTRise iFhnlpgTRise) {
        this.iFhnlpgTRise = iFhnlpgTRise;
    }

    public IFhnlpgResistance getiFhnlpgResistance() {
        return iFhnlpgResistance;
    }

    public void setiFhnlpgResistance(IFhnlpgResistance iFhnlpgResistance) {
        this.iFhnlpgResistance = iFhnlpgResistance;
    }

    public IFhnlpgRatio getiFhnlpgRatio() {
        return iFhnlpgRatio;
    }

    public void setiFhnlpgRatio(IFhnlpgRatio iFhnlpgRatio) {
        this.iFhnlpgRatio = iFhnlpgRatio;
    }

    public IFhnlpgLimit getiFhnlpgLimit() {
        return iFhnlpgLimit;
    }

    public void setiFhnlpgLimit(IFhnlpgLimit iFhnlpgLimit) {
        this.iFhnlpgLimit = iFhnlpgLimit;
    }

    public IFhnlpgCool getiFhnlpgCool() {
        return iFhnlpgCool;
    }

    public void setiFhnlpgCool(IFhnlpgCool iFhnlpgCool) {
        this.iFhnlpgCool = iFhnlpgCool;
    }

    public IFhnlpgTStatus getiFhnlpgTStatus() {
        return iFhnlpgTStatus;
    }

    public void setiFhnlpgTStatus(IFhnlpgTStatus iFhnlpgTStatus) {
        this.iFhnlpgTStatus = iFhnlpgTStatus;
    }

    public IDwfxpgDW getiDwfxpgDW() {
        return iDwfxpgDW;
    }

    public void setiDwfxpgDW(IDwfxpgDW iDwfxpgDW) {
        this.iDwfxpgDW = iDwfxpgDW;
    }

    public IDwfxpgPQ getiDwfxpgPQ() {
        return iDwfxpgPQ;
    }

    public void setiDwfxpgPQ(IDwfxpgPQ iDwfxpgPQ) {
        this.iDwfxpgPQ = iDwfxpgPQ;
    }

    public IFXPG getiFxpg() {
        return iFxpg;
    }

    public void setiFxpg(IFXPG iFxpg) {
        this.iFxpg = iFxpg;
    }
}
