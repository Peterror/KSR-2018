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
                        + quantifierMembershipFunction.getLabel() + " " // 1000
                        + linguisticVariable.getLabel() + " are " // temperatures are
                        + linguisticMembershipFunction.getLabel(); // high

                // measuring string correctness
                Classification quantifierClassification = quantifier.classify(
                        quantifierMembershipFunction,
                        linguisticMembershipFunction,
                        universe);

                summarizedString +=
                        " [T1=" +
                        BigDecimal.valueOf(quantifierClassification.getMembership())
                                .setScale(3, RoundingMode.HALF_UP)
                        + "]";
                strings.add(summarizedString);
            }
        }
        return strings;
    }

}
