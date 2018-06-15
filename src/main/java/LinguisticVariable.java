import java.util.List;

class LinguisticVariable extends FuzzySet{

    public LinguisticVariable(String variableName, List<MembershipFunction> membershipFunctions) {
        super(variableName, membershipFunctions);
        this.label = variableName;
        this.membershipFunctions = membershipFunctions;
    }

    public Classification measureCompatibilityLevel(MembershipFunction membershipFunction, double value){
        return new Classification(
                membershipFunction.getLabel(),
                membershipFunction.classify(value)
        );
    }

    public Classification measureCompatibilityLevelAND(MembershipFunction membershipFunction1,
                                                       MembershipFunction membershipFunction2,
                                                       double value){
        return new Classification(
                membershipFunction1.getLabel() + " AND " + membershipFunction2.getLabel(),
                membershipFunction1.classifyIntersection(membershipFunction2, value)
        );
    }

    public Classification measureCompatibilityLevelOR(MembershipFunction membershipFunction1,
                                                       MembershipFunction membershipFunction2,
                                                       double value){
        return new Classification(
                membershipFunction1.getLabel() + " OR " + membershipFunction2.getLabel(),
                membershipFunction1.classifyUnion(membershipFunction2, value)
        );
    }

    public Classification measureCompatibilityLevelNOT(MembershipFunction membershipFunction,
                                                       double value){
        return new Classification(
                "NOT " + membershipFunction.getLabel(),
                membershipFunction.classifyCompliment(value)
        );
    }
}
