package edu.xjtu.ee.fhnlpg.io;

/**
 * Created by Administrator on 2018/5/16.
 */
/**
 * ratio
 */
public class RatioHML {
    private double HM;
    private double ML;
    private double HL;

    public RatioHML() {
    }

    public RatioHML(double HM, double ML, double HL) {
        this.HM = HM;
        this.ML = ML;
        this.HL = HL;
    }

    public double getHM() {
        return HM;
    }

    public void setHM(double HM) {
        this.HM = HM;
    }

    public double getML() {
        return ML;
    }

    public void setML(double ML) {
        this.ML = ML;
    }

    public double getHL() {
        return HL;
    }

    public void setHL(double HL) {
        this.HL = HL;
    }
}