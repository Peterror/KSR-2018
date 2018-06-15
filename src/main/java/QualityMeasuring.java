public class QualityMeasuring {
    private LinguisticVariable linVar;
    private boolean isAbsolute;

    public QualityMeasuring(LinguisticVariable linVar, boolean absolut) {
        this.linVar = linVar;
        isAbsolute=absolut;
    }
/*
    public double T1()
    {
        double card=0;
        for (Entity e:linVar.getUniverse()
             )
        {
            card +=e.getLevelOfBelong();
        }
        return card/linVar.getUniverse().size();
    }

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
