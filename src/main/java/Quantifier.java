import java.util.List;

public abstract class Quantifier extends FuzzySet {
    public Quantifier(String setLabelName, List<MembershipFunction> membershipFunctions) {
        super(setLabelName, membershipFunctions);
    }
    public abstract Classification classify(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse_S1,
            double[] universe
    );

    public abstract Classification classifyAND(
            MembershipFunction quantifierFunction_Q,
            MembershipFunction labelAssociatedToUniverse1_S1,
            MembershipFunction labelAssociatedToUniverse2_S2,
            double[] universe1,
            double[] universe2
    );
}
