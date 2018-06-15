import java.util.List;

public class FuzzySet{
    String label;  // np. Wysokosc Temperatury; Ilosc rekordow
    List<MembershipFunction> membershipFunctions;

    public FuzzySet(String setLabelName, List<MembershipFunction> membershipFunctions) {
        this.label = setLabelName;
        this.membershipFunctions = membershipFunctions;
    }

    public List<MembershipFunction> getMembershipFunctions() {
        return membershipFunctions;
    }

    public MembershipFunction getMembershipFunction(String label) throws IndexOutOfBoundsException{
        for(MembershipFunction func: membershipFunctions){
            if(func.getLabel().equals(label)){
                return func;
            }
        }
        throw new IndexOutOfBoundsException("Cannot find MembershipFunction labeled " + label);
    }

    public String getLabel() {
        return label;
    }
}
