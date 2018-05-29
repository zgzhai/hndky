package edu.xjtu.ee.dwfxpg.io;

import edu.xjtu.ee.dwfxpg.CBus;
import edu.xjtu.ee.dwfxpg.CLine;
import java.util.ArrayList;

public class IDwfxpgPQ {
    private ArrayList<CBus> Bus;
    private ArrayList<CLine> Line;
    private double e = 0.00001;     //精度

    public IDwfxpgPQ() {
    }

    public IDwfxpgPQ(ArrayList<CBus> bus, ArrayList<CLine> line) {
        Bus = bus;
        Line = line;
    }

    public ArrayList<CBus> getBus() {
        return Bus;
    }

    public void setBus(ArrayList<CBus> bus) {
        Bus = bus;
    }

    public ArrayList<CLine> getLine() {
        return Line;
    }

    public void setLine(ArrayList<CLine> line) {
        Line = line;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }
}
