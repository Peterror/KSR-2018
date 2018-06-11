public class CompoundSummarizer
{
    private LinguisticVariable lingVar;
    private LinguisticVariable lingVar2;
    private LinguisticVariable LingVar3;
    private String connect;
    private FuzzySet u1;
    private FuzzySet u2;

    public CompoundSummarizer(String name, String[] setOfVariables, FuzzySet universe,
                              String name2, String[] setOfVariables2, FuzzySet universe2, String conn)
    {
        lingVar = new LinguisticVariable(name,setOfVariables,universe.entities);
        lingVar2 = new LinguisticVariable(name2,setOfVariables2,universe2.entities);
        u1=universe;
        u2=universe2;
        connect=conn;
    }

    public String Summarize(Integer index,Integer index2,boolean isQuantifierAbsolute)
    {
        if (connect=="and")
        {
            Set inter = u1.Intersection(u2);
            LingVar3 = new LinguisticVariable("intersection",lingVar.getSetOfVariables(),inter.entities);
        }
        else
        {
            Set add = u1.Sum(u2);
            LingVar3 = new LinguisticVariable("addition",lingVar.getSetOfVariables(),add.entities);
        }
        if(isQuantifierAbsolute)
            return LingVar3.AbsoluteQuantificator(index) + "of days had " + lingVar.getSetOfVariables()[index] + " " + lingVar.getName()
                    + connect + lingVar2.getSetOfVariables()[index2] + " " + lingVar2.getName();
        else
            return LingVar3.RelativeQuantificator(index) + "of days had " + lingVar.getSetOfVariables()[index] + " " + lingVar.getName()
                    + connect + lingVar2.getSetOfVariables()[index2] + " " + lingVar2.getName();
    }
}
