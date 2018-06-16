public class QualityMeasuring {
    private Quantifier quantifier;
    private MembershipFunction quantifierFunction_Q, labelAssociatedToUniverse1_S1, labelAssociatedToUniverse2_S2;
    double[] universe1, universe2;

    public QualityMeasuring(Quantifier quantifier,
                            MembershipFunction quantifierFunction_Q,
                            MembershipFunction labelAssociatedToUniverse_S1,
                            double[] universe
    ) {
        this.quantifier = quantifier;
        this.quantifierFunction_Q = quantifierFunction_Q;
        this.labelAssociatedToUniverse1_S1 = labelAssociatedToUniverse_S1;
        this.labelAssociatedToUniverse2_S2 = null;
        this.universe1 = universe;
        this.universe2 = null;
    }

    public QualityMeasuring(Quantifier quantifier,
                            MembershipFunction quantifierFunction_Q,
                            MembershipFunction labelAssociatedToUniverse1_S1,
                            MembershipFunction labelAssociatedToUniverse2_S2,
                            double[] universe1,
                            double[] universe2
    ) {
        this.quantifier = quantifier;
        this.quantifierFunction_Q = quantifierFunction_Q;
        this.labelAssociatedToUniverse1_S1 = labelAssociatedToUniverse1_S1;
        this.labelAssociatedToUniverse2_S2 = labelAssociatedToUniverse2_S2;
        this.universe1 = universe1;
        this.universe2 = universe2;
    }

    public double T1()
    {
        if(labelAssociatedToUniverse2_S2 == null){
            return quantifier.classify(quantifierFunction_Q, labelAssociatedToUniverse1_S1, universe1).getMembership();
        }
        return 0;
    }
    public double T1AND()
    {
        if(labelAssociatedToUniverse2_S2 != null){
            return quantifier.classifyAND(
                    quantifierFunction_Q,
                    labelAssociatedToUniverse1_S1,
                    labelAssociatedToUniverse2_S2,
                    universe1,
                    universe2).getMembership();
        }
        return 0;
    }
/*
    public double T2()
    {
        double supp=0;
        for (Entity e:linVar.getUniverse()
             )
        {
            if (e.getLevelOfBelong()>0)
                supp++;
        }
        supp /= linVar.getUniverse().size();
        return 1-supp;
    }

    public double T5()
    {
        return 2*0.5;
    }

    public double T()
    {
        return T1()*0.4+T2()*0.4+T5()*0.2;
    }
*/

}
