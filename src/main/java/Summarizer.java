import java.math.BigDecimal;
import java.math.RoundingMode;

public class Summarizer {

    private LinguisticVariable lingVar;
    private String fullName;

    public Summarizer(String name, String[] setOfVariables, FuzzySet universe, String fullName) {
        lingVar = new LinguisticVariable(name,setOfVariables,universe.entities);
        this.fullName=fullName;
    }

    public String Summarize(Integer index,boolean isQuantifierAbsolute)
    {
        if(isQuantifierAbsolute)
        {
            QualityMeasuring quality = new QualityMeasuring(lingVar,isQuantifierAbsolute);
            return lingVar.AbsoluteQuantificator(index) + "of days had " + lingVar.getSetOfVariables()[index] + " " + fullName + "    "+
                    "[T1=" + BigDecimal.valueOf(quality.T1()).setScale(3, RoundingMode.HALF_UP)
                    + " |T2=" + BigDecimal.valueOf(quality.T2()).setScale(3, RoundingMode.HALF_UP) +
                    " |T5=" + BigDecimal.valueOf(quality.T5()).setScale(3, RoundingMode.HALF_UP) +
                    " |Tavg=" + BigDecimal.valueOf(quality.T()).setScale(3, RoundingMode.HALF_UP)+"]";
        }
        else
        {
            QualityMeasuring quality = new QualityMeasuring(lingVar,isQuantifierAbsolute);
            return lingVar.RelativeQuantificator(index) + "of days had " + lingVar.getSetOfVariables()[index] + " " + fullName+ "    "+
                    "[T1=" + BigDecimal.valueOf(quality.T1()).setScale(3, RoundingMode.HALF_UP)
                    + " |T2=" + BigDecimal.valueOf(quality.T2()).setScale(3, RoundingMode.HALF_UP) +
                    " |T5=" + BigDecimal.valueOf(quality.T5()).setScale(3, RoundingMode.HALF_UP) +
                    " |Tavg=" + BigDecimal.valueOf(quality.T()).setScale(3, RoundingMode.HALF_UP)+"]";
        }

    }

}
