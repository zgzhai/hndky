package edu.xjtu.ee.unisolver;

import edu.xjtu.ee.fhnlpg.HS;
import edu.xjtu.ee.fhnlpg.HSLoad;
import edu.xjtu.ee.fhnlpg.Load3;
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
                break;
            case "HST":
                uniResult = processHST(uniParameter);
                break;
            case "ZCFHNLPG":
                uniResult = processZCFHNLPG(uniParameter);
                break;
            case "CQFHNLPG":
                uniResult = processZCFHNLPG(uniParameter);
                break;
            case "DQFHNLPG":
                uniResult = processDQFHNLPG(uniParameter);
                break;
            case "FHZT":
                uniResult = processDQFHNLPG(uniParameter);
                break;
        }
        return uniResult;
    }

    private UniResult processCOOL(UniParameter uniParameter) {
        UniResult uniResult = new UniResult();

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
