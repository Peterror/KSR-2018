import java.util.List;

public class RelativeQuantifier extends Quantifier{
    public RelativeQuantifier(String label, List<MembershipFunction> membershipFunctions) {
        super(label, membershipFunctions);
    }

    @Override
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

    @Override
    public Classification classifyAND(MembershipFunction quantifierFunction_Q,
                                      MembershipFunction labelAssociatedToUniverse1_S1,
                                      MembershipFunction labelAssociatedToUniverse2_S2,
                                      double[] universe1,
                                      double[] universe2
    ) {
        double relativeS1S2CorrectnessCount = labelAssociatedToUniverse1_S1.cardinalOfIntersectionInDifferentDomains(
                labelAssociatedToUniverse2_S2,
                universe1,
                universe2);
        relativeS1S2CorrectnessCount /= (double)universe1.length;

        return new Classification(
                getLabel() + " " + quantifierFunction_Q.getLabel(),
                quantifierFunction_Q.classify(relativeS1S2CorrectnessCount)  // T_1
        );
    }
}
