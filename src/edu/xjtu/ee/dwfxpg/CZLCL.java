package edu.xjtu.ee.dwfxpg;

import Jama.Matrix;
import edu.xjtu.ee.dwfxpg.io.CLinePIJ;
import edu.xjtu.ee.dwfxpg.io.IDwfxpgDW;
import edu.xjtu.ee.dwfxpg.io.OZLCL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 直流潮流计算类
 */
public class CZLCL {
    protected ArrayList<CBus> Bus;
    private HashMap<Integer, CBus> hashMapBus;
    protected ArrayList<CLoad> Load;
    private HashMap<Integer, Double> hashMapLoad;
    protected ArrayList<CGenerator> Generator;
    private HashMap<Integer, Double> hashMapGenerator;
    protected ArrayList<CLine> Line;
    private Matrix P;
    protected Matrix Y;
    public Matrix Delta;
    public Matrix PIJ;

    public static void main(String[] args) {
        // write your code here
        CZLCL c = new CZLCL();
        c.init();
        c.solve();
        c.print();
        //c.OutToFile();
    }

    public CZLCL() {
        Bus = new ArrayList<CBus>();
        hashMapBus = new HashMap<Integer, CBus>();
        Load = new ArrayList<CLoad>();
        hashMapLoad = new HashMap<Integer, Double>();
        Generator = new ArrayList<CGenerator>();
        hashMapGenerator = new HashMap<Integer, Double>();
        Line = new ArrayList<CLine>();
    }

    public CZLCL(IDwfxpgDW dw) {
        Bus = dw.getBus();
        Load = dw.getLoad();
        Generator = dw.getGenerator();
        Line = dw.getLine();
        hashMapBus = new HashMap<Integer, CBus>();
        hashMapLoad = new HashMap<Integer, Double>();
        hashMapGenerator = new HashMap<Integer, Double>();
    }

    public void init() {

        Bus.add(new CBus(1, 1));
        Bus.add(new CBus(2, 2));
        Bus.add(new CBus(3, 1));
        Bus.add(new CBus(4, 1));
        Bus.add(new CBus(5, 2));
        Bus.add(new CBus(6, 1));
        Bus.add(new CBus(7, 1));
        Bus.add(new CBus(8, 1));
        Bus.add(new CBus(9, 1));
        Bus.add(new CBus(10, 2));
        Bus.add(new CBus(11, 1));
        Bus.add(new CBus(12, 1));
        Bus.add(new CBus(13, 1));
        Bus.add(new CBus(14, 2));
        Bus.add(new CBus(15, 1));
        Bus.add(new CBus(16, 2));
        Bus.add(new CBus(17, 1));
        Bus.add(new CBus(18, 1));
        Bus.add(new CBus(19, 1));
        Bus.add(new CBus(20, 1));
        Bus.add(new CBus(21, 1));
        Bus.add(new CBus(22, 3));

        Load.add(new CLoad(1, 20));
        Load.add(new CLoad(3, 220));
        Load.add(new CLoad(5, 258.5));
        Load.add(new CLoad(6, 125));
        Load.add(new CLoad(8, 54));
        Load.add(new CLoad(10, 11.2));
        Load.add(new CLoad(11, 215));
        Load.add(new CLoad(13, 298.1));
        Load.add(new CLoad(15, 60));
        Load.add(new CLoad(18, 135));
        Load.add(new CLoad(20, 41));
        Load.add(new CLoad(21, 58.6));
        Load.add(new CLoad(22, 29.2));

        Generator.add(new CGenerator(1, 260));
        Generator.add(new CGenerator(8, 658));
        Generator.add(new CGenerator(9, 152.1));
        Generator.add(new CGenerator(16, 260.2));
        Generator.add(new CGenerator(19, 138.7));
        Generator.add(new CGenerator(22, 70));

    }

    public void solve() {
        PrepareData();
        FormP();
        FormY();
        CalDelta();
        CalPIJ();
    }

    private void PrepareData() {
        for (CLoad load : Load) {
            hashMapLoad.put(load.id, load.pl);
        }
        for (CGenerator generator : Generator) {
            hashMapGenerator.put(generator.id, generator.pg);
        }
        for (CBus bus : Bus) {
            hashMapBus.put(bus.id, bus);
        }
    }

    private void FormP() {
        P = new Matrix(Bus.size() - 1, 1, 0.0d);
        int i = 0;
        for (CBus bus : Bus) {
            if (bus.type != CBus.TYPE3) {
                double pl = hashMapLoad.containsKey(bus.id) ? hashMapLoad.get(bus.id) : 0.0;
                double pg = hashMapGenerator.containsKey(bus.id) ? hashMapGenerator.get(bus.id) : 0.0;
                P.set(i, 0, pg - pl);
                i++;
            }
        }
    }

    private void FormY() {
        Y = new Matrix(Bus.size() - 1, Bus.size() - 1, 0.0d);
        for (CLine line : Line) {
            int I = line.sid;
            int J = line.eid;
            double X = 0.0d;
            double B = 0.0d;
            if (hashMapBus.get(I).type < CBus.TYPE3 && hashMapBus.get(J).type < CBus.TYPE3) {
                X = line.resistance;
                B = 1.0 / X;
                Y.set(I - 1, I - 1, Y.get(I - 1, I - 1) + B);
                Y.set(J - 1, J - 1, Y.get(J - 1, J - 1) + B);
                Y.set(I - 1, J - 1, Y.get(I - 1, J - 1) - B);
                Y.set(J - 1, I - 1, Y.get(I - 1, J - 1));

            } else if (hashMapBus.get(I).type == CBus.TYPE3 && hashMapBus.get(J).type < CBus.TYPE3) {
                X = line.resistance;
                B = 1.0 / X;
                Y.set(J - 1, J - 1, Y.get(J - 1, J - 1) + B);
            } else if (hashMapBus.get(I).type < CBus.TYPE3 && hashMapBus.get(J).type == CBus.TYPE3) {
                X = line.resistance;
                B = 1.0 / X;
                Y.set(I - 1, I - 1, Y.get(I - 1, I - 1) + B);
            }
        }
    }

    private void CalDelta() {
        Matrix d = Y.inverse().times(P);
        Delta = new Matrix(Bus.size(), 1, 0.0d);
        for (int i = 0; i < d.getRowDimension(); i++) {
            Delta.set(i, 0, d.get(i, 0));
        }
        Delta.set(Delta.getRowDimension() - 1, 0, 0.0d);
    }

    private void CalPIJ() {
        PIJ = new Matrix(Line.size(), 1, 0.0d);
        int i = 0;
        for (CLine line : Line) {
            int I = line.sid;
            int J = line.eid;
            double X = line.resistance;
            if (hashMapBus.get(I).type == CBus.TYPE3) {
                Delta.set(I - 1, 0, 0.0d);
            } else if (hashMapBus.get(J).type == CBus.TYPE3) {
                Delta.set(J - 1, 0, 0.0d);
            }

            PIJ.set(i, 0, (Delta.get(I - 1, 0) - Delta.get(J - 1, 0)) / X);
            i++;
        }
    }

    public void print() {
        System.out.println("Y=\r\n");
        Y.print(10, 2);

        System.out.println("Delta=\r\n");
        for (int i = 0; i < Delta.getRowDimension(); i++) {
            double v = Delta.get(i, 0);
            while ((v * 180) / Math.PI <= -180) {
                v = v + 2 * Math.PI;
            }
            while ((v * 180) / Math.PI >= 180) {
                v = v - 2 * Math.PI;
            }

            System.out.println(i + "," + v * 180 / Math.PI + "\r\n");
        }

        System.out.println("PIJ=\r\n");
        int i = 0;
        for (CLine line : Line) {
            System.out.println(line.sid + "-" + line.eid + ":" + PIJ.get(i, 0) + "\r\n");
            i++;
        }
    }

    public void OutToFile() {
        try {
            File file = new File("output.txt");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Y=\r\n");
            for (int i = 0; i < Y.getRowDimension(); i++) {
                for (int j = 0; j < Y.getColumnDimension(); j++) {
                    //-左对齐,10固定个字符宽度,.2两位小数,f浮点数
                    bw.write(String.format("%-10.2f", Y.get(i, j)));
                }
                bw.write("\r\n");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OZLCL output() {
        OZLCL ozlcl = new OZLCL();
        ozlcl.Y = Y;
        ArrayList<Double> d = new ArrayList<Double>();
        for (int i = 0; i < Delta.getRowDimension(); i++) {
            double v = Delta.get(i, 0);
            while ((v * 180) / Math.PI <= -180) {
                v = v + 2 * Math.PI;
            }
            while ((v * 180) / Math.PI >= 180) {
                v = v - 2 * Math.PI;
            }
            d.add(v * 180 / Math.PI);
        }
        ozlcl.Delta = d;

        ArrayList<CLinePIJ> p = new ArrayList<CLinePIJ>();
        int i = 0;
        for (CLine line : Line) {
            p.add(new CLinePIJ(line.sid, line.eid, PIJ.get(i, 0)));
            i++;
        }

        ozlcl.PIJ = p;
        return ozlcl;
    }
}
