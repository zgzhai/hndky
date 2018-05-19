package edu.xjtu.ee.dwfxpg.io;

import edu.xjtu.ee.dwfxpg.CBus;
import edu.xjtu.ee.dwfxpg.CGenerator;
import edu.xjtu.ee.dwfxpg.CLine;
import edu.xjtu.ee.dwfxpg.CLoad;

import java.util.ArrayList;

/**
 * ZLCL和FHXJ输入参数一致
 */
public class IDwfxpgDW {
    private ArrayList<CBus> Bus;
    private ArrayList<CLoad> Load;
    private ArrayList<CGenerator> Generator;
    private ArrayList<CLine> Line;

    public IDwfxpgDW() {
    }

    public IDwfxpgDW(ArrayList<CBus> bus, ArrayList<CLoad> load, ArrayList<CGenerator> generator, ArrayList<CLine> line) {
        Bus = bus;
        Load = load;
        Generator = generator;
        Line = line;
    }

    public ArrayList<CBus> getBus() {
        return Bus;
    }

    public void setBus(ArrayList<CBus> bus) {
        Bus = bus;
    }

    public ArrayList<CLoad> getLoad() {
        return Load;
    }

    public void setLoad(ArrayList<CLoad> load) {
        Load = load;
    }

    public ArrayList<CGenerator> getGenerator() {
        return Generator;
    }

    public void setGenerator(ArrayList<CGenerator> generator) {
        Generator = generator;
    }

    public ArrayList<CLine> getLine() {
        return Line;
    }

    public void setLine(ArrayList<CLine> line) {
        Line = line;
    }
}
