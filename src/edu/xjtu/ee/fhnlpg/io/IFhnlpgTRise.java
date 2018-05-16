package edu.xjtu.ee.fhnlpg.io;

public class IFhnlpgTRise {
    private TRiseV rise100;
    private TRiseV rise70;

    public IFhnlpgTRise() {
    }

    public IFhnlpgTRise(TRiseV rise100, TRiseV rise70) {
        this.rise100 = rise100;
        this.rise70 = rise70;
    }

    public TRiseV getRise70() {
        return rise70;
    }

    public void setRise70(TRiseV rise70) {
        this.rise70 = rise70;
    }

    public TRiseV getRise100() {
        return rise100;
    }

    public void setRise100(TRiseV rise100) {
        this.rise100 = rise100;
    }
}
