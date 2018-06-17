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

    public double T2()
    {
        if(labelAssociatedToUniverse2_S2==null)
        {
            return 1.0 - labelAssociatedToUniverse1_S1.sumSupport(universe1)/universe1.length;
        }
        return 0;
    }

    public double T2AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            return 1.0 - Math.sqrt((labelAssociatedToUniverse1_S1.sumSupport(universe1)/universe1.length)*
                    (labelAssociatedToUniverse2_S2.sumSupport(universe2)/universe2.length));
        }
        return 0;
    }

    public double T3()
    {
        return labelAssociatedToUniverse1_S1.sumSupport(universe1)/universe1.length;
    }

    public double T3u2()
    {
        return labelAssociatedToUniverse2_S2.sumSupport(universe2)/universe2.length;
    }
    public double T3AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            return labelAssociatedToUniverse1_S1.sumSupportOfIntersectionInDifferentDomains(
                    labelAssociatedToUniverse2_S2,
                    universe1,
                    universe2
            ) / labelAssociatedToUniverse2_S2.sumSupport(universe2);
        }
        return 0;
    }

    public double T4()
    {
        if(labelAssociatedToUniverse2_S2==null)
        {
            double r1=0;
            for (double value:universe1)
            {
                if(labelAssociatedToUniverse1_S1.classify(value)>0)
                    r1++;
            }
            r1 /= universe1.length;
            return Math.abs(r1-T3());
        }
        return 0;
    }

    public double T4AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            double r1=0;
            double r2=0;
            for (double value:universe1)
            {
                if (labelAssociatedToUniverse1_S1.classify(value)>0)
                    r1++;
            }
            for (double value:universe2)
            {
                if (labelAssociatedToUniverse2_S2.classify(value)>0)
                    r2++;
            }
            r1 /= universe1.length;
            r2 /= universe2.length;
            return Math.abs(r1*r2-T3AND());
        }
        return 0;
    }

    public double T5()
    {
        if(labelAssociatedToUniverse2_S2==null)
            return 2*0.5;
        else
            return 2*0.5*0.5;
    }

    public double T6()
    {
        return 1.0-quantifierFunction_Q.sumSupport(universe1)/universe1.length;
    }

    public double T7()
    {
        return 1.0 - quantifierFunction_Q.cardinal(universe1)/universe1.length;
    }

    public double T8()
    {
        if(labelAssociatedToUniverse2_S2==null)
        {
            return 1.0 - labelAssociatedToUniverse1_S1.cardinal(universe1)/universe1.length;
        }
        return 0;
    }

    public double T8AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            return 1.0 - Math.sqrt((labelAssociatedToUniverse1_S1.cardinal(universe1)/universe1.length)*
                    (labelAssociatedToUniverse2_S2.cardinal(universe2)/universe2.length));
        }
        return 0;
    }

    public double T9()
    {
        if(labelAssociatedToUniverse2_S2==null)
        {
            return 1.0-T3();
        }
        return 0;
    }

    public double T9AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            return 1.0 - Math.sqrt(T3()*T3u2());
        }
        return 0;
    }

    public double T10()
    {
        if(labelAssociatedToUniverse2_S2==null)
        {
            return 1.0 - labelAssociatedToUniverse1_S1.cardinal(universe1)/universe1.length;
        }
        return 0;
    }

    public double T10AND()
    {
        if(labelAssociatedToUniverse2_S2 != null)
        {
            return 1.0 - Math.sqrt((labelAssociatedToUniverse1_S1.cardinal(universe1)/universe1.length)*
                    (labelAssociatedToUniverse2_S2.cardinal(universe2)/universe2.length));
        }
        return 0;
    }

    public double T11()
    {
        return 2*0.5;
    }

    public double T()
    {
        if(labelAssociatedToUniverse2_S2==null)
            return (T1()+T2()+T3()+T4()+T5()+T6()+T7()+T8()+T9()+T10()+T11())/11.0;
        else
            return (T1AND()+T2AND()+T3AND()+T4AND()+T5()+T6()+T7()+T8AND()+T9AND()+T10AND()+T11())/11.0;
    }

}
