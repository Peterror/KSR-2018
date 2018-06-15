import java.util.List;

public class AbsoluteQuantifier extends Quantifier{
    public AbsoluteQuantifier(String label, List<MembershipFunction> membershipFunctions) {
        super(label, membershipFunctions);
    }

    public Classification classify(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse_S1,
            double[] universe
    ){
        double absoluteS1CorrectnessCount = labelAssociatedToUniverse_S1.cardinal(universe);

        // long roundQuantification = Math.round(classification.getMembership()/1000.0)*1000;
        return new Classification(
                getLabel() + " " + quantifierFunction_Q.getLabel(),
                quantifierFunction_Q.classify(absoluteS1CorrectnessCount)  // T_1
        );
    }
}
