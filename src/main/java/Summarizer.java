import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Summarizer {

    public List<String> summarize(LinguisticVariable linguisticVariable, Quantifier quantifier, double[] universe)
    {

        String summarizedString = "";
        List<String> strings = new ArrayList<>();

        List<MembershipFunction> linguisticMembershipFunctions = linguisticVariable.getMembershipFunctions();
        List<MembershipFunction> quantifierMembershipFunctions = quantifier.getMembershipFunctions();
        for(MembershipFunction linguisticMembershipFunction: linguisticMembershipFunctions){
            for(MembershipFunction quantifierMembershipFunction: quantifierMembershipFunctions){
                // creating summarization String
                summarizedString =
                        quantifier.getLabel() + " " // ABOUT
                        + quantifierMembershipFunction.getLabel() + " objects have " // 1000 objects have
                        + linguisticVariable.getLabel() + " " // temperatures
                        + linguisticMembershipFunction.getLabel(); // high

                // preparing quality measuring
                QualityMeasuring qm = new QualityMeasuring(
                        quantifier,
                        quantifierMembershipFunction,
                        linguisticMembershipFunction,
                        universe);

                summarizedString +=
                        " [T1=" +
                        BigDecimal.valueOf(qm.T1())
                                .setScale(3, RoundingMode.HALF_UP)
                        + "]";
                strings.add(summarizedString);
            }
        }
        return strings;
    }

    public List<String> summarizeAND(LinguisticVariable linguisticVariable1,
                                     LinguisticVariable linguisticVariable2,
                                     Quantifier quantifier,
                                     double[] universe1,
                                     double[] universe2)
    {

        String summarizedString = "";
        List<String> strings = new ArrayList<>();
        List<MembershipFunction> linguisticMembershipFunctions1 = linguisticVariable1.getMembershipFunctions();
        List<MembershipFunction> linguisticMembershipFunctions2 = linguisticVariable2.getMembershipFunctions();
        List<MembershipFunction> quantifierMembershipFunctions = quantifier.getMembershipFunctions();

        for(MembershipFunction linguisticMembershipFunction1: linguisticMembershipFunctions1) {
            for (MembershipFunction linguisticMembershipFunction2 : linguisticMembershipFunctions2) {
                for (MembershipFunction quantifierMembershipFunction : quantifierMembershipFunctions) {
                    // creating summarization String
                    summarizedString =
                            quantifier.getLabel() + " " // ABOUT
                                    + quantifierMembershipFunction.getLabel() + " objects have " // 1000 objects have
                                    + linguisticVariable1.getLabel() + " " // temperature
                                    + linguisticMembershipFunction1.getLabel() + " and " // high and
                                    + linguisticVariable2.getLabel() + " " // humidity
                                    + linguisticMembershipFunction2.getLabel(); // medium

                    // preparing quality measuring
                    QualityMeasuring qm = new QualityMeasuring(
                            quantifier,
                            quantifierMembershipFunction,
                            linguisticMembershipFunction1,
                            linguisticMembershipFunction2,
                            universe1,
                            universe2);

                    summarizedString +=
                            " [T1=" +
                                    BigDecimal.valueOf(qm.T1AND())
                                            .setScale(3, RoundingMode.HALF_UP)
                                    + "]";
                    strings.add(summarizedString);
                }
            }
        }
        return strings;
    }

}
