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
}
