import java.util.List;

public class RelativeQuantifier extends Quantifier{
    public RelativeQuantifier(String label, List<MembershipFunction> membershipFunctions) {
        super(label, membershipFunctions);
    }

    public Classification classify(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse_S1,
            double[] universe
    ){
        double relativeS1CorrectnessCount = labelAssociatedToUniverse_S1.cardinal(universe)/(double)universe.length;

        // long roundQuantification = Math.round(classification.getMembership()/1000.0)*1000;
        return new Classification(
                getLabel() + " " + quantifierFunction_Q.getLabel(),
                quantifierFunction_Q.classify(relativeS1CorrectnessCount)  // T_1
        );
    }
}
