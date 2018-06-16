import java.util.List;

public class AbsoluteQuantifier extends Quantifier{
    public AbsoluteQuantifier(String label, List<MembershipFunction> membershipFunctions) {
        super(label, membershipFunctions);
    }

    @Override
    public Classification classify(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse_S1,
            double[] universe
    ){
        double absoluteS1CorrectnessCount = labelAssociatedToUniverse_S1.cardinal(universe);

        return new Classification(
                getLabel() + " " + quantifierFunction_Q.getLabel(),
                quantifierFunction_Q.classify(absoluteS1CorrectnessCount)  // T_1
        );
    }

    @Override
    public Classification classifyAND(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse1_S1,
            MembershipFunction labelAssociatedToUniverse2_S2,
            double[] universe1,
            double[] universe2
    ){
        //TODO
        double absoluteS1CorrectnessCount = labelAssociatedToUniverse1_S1.cardinal(universe1);

        return new Classification(
                getLabel() + " " + quantifierFunction_Q.getLabel(),
                quantifierFunction_Q.classify(absoluteS1CorrectnessCount)  // T_1
        );
    }
}
