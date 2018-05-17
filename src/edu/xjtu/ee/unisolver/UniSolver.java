package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.fhnlpg.*;
import edu.xjtu.ee.fhnlpg.io.*;

import java.util.ArrayList;

public class UniSolver {

    public UniSolver() {
    }

    public UniResult solve(UniParameter uniParameter, String mode) {
        UniResult uniResult = null;
        switch (mode.toUpperCase()) {
            case "COOL":
                uniResult = processCOOL(uniParameter);
                uniResult.mode = "COOL";
                break;
            case "HST":
                uniResult = processHST(uniParameter);
                uniResult.mode = "HST";
                break;
            case "ZCFHNLPG":
                uniResult = processZCFHNLPG(uniParameter);
                uniResult.mode = "ZCFHNLPG";
                break;
            case "CQFHNLPG":
                uniResult = processZCFHNLPG(uniParameter);
                uniResult.mode = "CQFHNLPG";
                break;
            case "DQFHNLPG":
                uniResult = processDQFHNLPG(uniParameter);
                uniResult.mode = "DQFHNLPG";
                break;
            case "FHZT":
                uniResult = processFHZT(uniParameter);
                uniResult.mode = "FHZT";
                break;
        }
        return uniResult;
    }

    private UniResult processCOOL(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();
        if (uniParameter == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "uniParameter为空";
            return uniResult;
        }

        if (uniParameter.getiFhnlpgCool() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgCool为空";
            return uniResult;
        }

        COOL cool = new COOL();
        cool.init(uniParameter.getiFhnlpgCool().getA1(),
                uniParameter.getiFhnlpgCool().getB1(),
                uniParameter.getiFhnlpgCool().getA2(),
                uniParameter.getiFhnlpgCool().getB2(),
                uniParameter.getiFhnlpgCool().getA3(),
                uniParameter.getiFhnlpgCool().getB3(),
                uniParameter.getiFhnlpgCool().getT1(),
                uniParameter.getiFhnlpgCool().getTH1(),
                uniParameter.getiFhnlpgCool().getK1());
        cool.solve(uniParameter.getiFhnlpgCool().getT2(),
                uniParameter.getiFhnlpgCool().getTH2(),
                uniParameter.getiFhnlpgCool().getK2());
        uniResult.oCool = cool.output();
        return uniResult;
    }

    private UniResult processHST(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();
        checkParameterHS(uniParameter, uniResult);
        if (uniResult.errcode == 0) {
            HS hs = new HS();
            hs.init(uniParameter.getiFhnlpgBase(),
                    uniParameter.getiFhnlpgInitial(),
                    uniParameter.getiFhnlpgResistance(),
                    uniParameter.getiFhnlpgTRise(),
                    uniParameter.getiFhnlpgRatio(),
                    uniParameter.getiFhnlpgOnLoad());
            hs.solve();
            uniResult.oHst = hs.output();
        }
        return uniResult;
    }

    private UniResult processZCFHNLPG(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();
        checkParameterHS(uniParameter, uniResult);
        if (uniParameter.getiFhnlpgLimit() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgLimit为空";
        }

        if (uniResult.errcode == 0) {
            HSLoad hs = new HSLoad();
            hs.init(uniParameter.getiFhnlpgBase(),
                    uniParameter.getiFhnlpgInitial(),
                    uniParameter.getiFhnlpgResistance(),
                    uniParameter.getiFhnlpgTRise(),
                    uniParameter.getiFhnlpgRatio(),
                    uniParameter.getiFhnlpgOnLoad(),
                    uniParameter.getiFhnlpgLimit());
            hs.solve();
            uniResult.oZcfhnlpg = hs.output();
        }
        return uniResult;
    }

    private UniResult processDQFHNLPG(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();
        checkParameterHS(uniParameter, uniResult);
        if (uniParameter.getiFhnlpgLimit() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgLimit为空";
        }

        if (uniResult.errcode == 0) {
            Load3 hs = new Load3();
            hs.init(uniParameter.getiFhnlpgBase(),
                    uniParameter.getiFhnlpgInitial(),
                    uniParameter.getiFhnlpgResistance(),
                    uniParameter.getiFhnlpgTRise(),
                    uniParameter.getiFhnlpgRatio(),
                    uniParameter.getiFhnlpgOnLoad(),
                    uniParameter.getiFhnlpgLimit());
            hs.solve();
            uniResult.oDqfhnlpg = hs.output();
        }
        return uniResult;
    }

    private UniResult processFHZT(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();
        if (uniParameter == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "uniParameter为空";
            return uniResult;
        }

        if (uniParameter.getiFhnlpgTStatus() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgTStatus为空";
            return uniResult;
        }

        TStatus tStatus = new TStatus(uniParameter.getiFhnlpgTStatus().getTlimit_top(),
                uniParameter.getiFhnlpgTStatus().getTlimit_hs(),
                uniParameter.getiFhnlpgTStatus().getNameplate(),
                uniParameter.getiFhnlpgTStatus().getOperation());
        tStatus.solve();
        uniResult.oFhzt = tStatus.output();
        return uniResult;
    }

    private void checkParameterHS(UniParameter uniParameter, UniResult uniResult) {
        if (uniParameter == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "uniParameter为空";
        }

        if (uniParameter.getiFhnlpgBase() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgBase为空";
        }

        if (uniParameter.getiFhnlpgInitial() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgInitial为空";
        }

        if (uniParameter.getiFhnlpgResistance() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgResistance为空";
        } else {
            if (uniParameter.getiFhnlpgResistance().getD_H().size() < uniParameter.getiFhnlpgResistance().getTap()) {
                uniResult.errcode = -1;
                uniResult.errmsg = "D_H数组元素个数小于Tap";
            }
        }

        if (uniParameter.getiFhnlpgTRise() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgTRise为空";
        }

        if (uniParameter.getiFhnlpgRatio() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgRatio为空";
        } else {
            if (uniParameter.getiFhnlpgRatio().getRatio().size() < uniParameter.getiFhnlpgResistance().getTap()) {
                uniResult.errcode = -1;
                uniResult.errmsg = "Ratio数组元素个数小于Tap";
            }
        }

        if (uniParameter.getiFhnlpgOnLoad() == null) {
            uniResult.errcode = -1;
            uniResult.errmsg = "iFhnlpgOnload为空";
        } else {
            if (uniParameter.getiFhnlpgOnLoad().getT_amb().size() != 9) {
                uniResult.errcode = -1;
                uniResult.errmsg = "onload中load数组大小必须为9";
            }
        }
    }
}
