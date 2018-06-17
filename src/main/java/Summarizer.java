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
                        " [T1=" + String.format("%.3f",qm.T1()) + " ; "
                                + "T2=" + String.format("%.3f",qm.T2()) + " ; "
                                + "T3=" + String.format("%.3f",qm.T3()) + " ; "
                                + "T4=" + String.format("%.3f",qm.T4()) + " ; "
                                + "T5=" + String.format("%.3f",qm.T5()) + " ; "
                                + "T6=" + String.format("%.3f",qm.T6()) + " ; "
                                + "T7=" + String.format("%.3f",qm.T7()) + " ; "
                                + "T8=" + String.format("%.3f",qm.T8()) + " ; "
                                + "T9=" + String.format("%.3f",qm.T9()) + " ; "
                                + "T10=" + String.format("%.3f",qm.T10()) + " ; "
                                + "T11=" + String.format("%.3f",qm.T11()) + " ; "
                                + "T=" + String.format("%.3f",qm.T())
                                +"]\\\\";
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
                            " [T1=" + String.format("%.3f",qm.T1AND()) + " ; "
                                    + "T2=" + String.format("%.3f",qm.T2AND()) + " ; "
                                    + "T3=" + String.format("%.3f",qm.T3AND()) + " ; "
                                    + "T4=" + String.format("%.3f",qm.T4AND()) + " ; "
                                    + "T5=" + String.format("%.3f",qm.T5()) + " ; "
                                    + "T6=" + String.format("%.3f",qm.T6()) + " ; "
                                    + "T7=" + String.format("%.3f",qm.T7()) + " ; "
                                    + "T8=" + String.format("%.3f",qm.T8AND()) + " ; "
                                    + "T9=" + String.format("%.3f",qm.T9AND()) + " ; "
                                    + "T10=" + String.format("%.3f",qm.T10AND()) + " ; "
                                    + "T11=" + String.format("%.3f",qm.T11()) + " ; "
                                    + "T=" + String.format("%.3f",qm.T())
                                    +"]\\\\";
                    strings.add(summarizedString);
                }
            }
        }
        return strings;
    }

}
