package edu.xjtu.ee.unisolver;

public class UniParameter {
    public IFhnlpgBase iFhnlpgBase;
    public IFhnlpgInitial iFhnlpgInitial;
    public IFhnlpgOnLoad iFhnlpgOnLoad;
    public IFhnlpgTRise iFhnlpgTRise;
    public IFhnlpgExtra iFhnlpgExtra;

    public UniParameter() {
    }

    public UniParameter(IFhnlpgBase iFhnlpgBase, IFhnlpgInitial iFhnlpgInitial, IFhnlpgOnLoad iFhnlpgOnLoad, IFhnlpgTRise iFhnlpgTRise) {
        this.iFhnlpgBase = iFhnlpgBase;
        this.iFhnlpgInitial = iFhnlpgInitial;
        this.iFhnlpgOnLoad = iFhnlpgOnLoad;
        this.iFhnlpgTRise = iFhnlpgTRise;
    }

    public UniParameter(IFhnlpgBase iFhnlpgBase, IFhnlpgInitial iFhnlpgInitial, IFhnlpgOnLoad iFhnlpgOnLoad, IFhnlpgTRise iFhnlpgTRise, IFhnlpgExtra iFhnlpgExtra) {
        this.iFhnlpgBase = iFhnlpgBase;
        this.iFhnlpgInitial = iFhnlpgInitial;
        this.iFhnlpgOnLoad = iFhnlpgOnLoad;
        this.iFhnlpgTRise = iFhnlpgTRise;
        this.iFhnlpgExtra = iFhnlpgExtra;
    }


}
