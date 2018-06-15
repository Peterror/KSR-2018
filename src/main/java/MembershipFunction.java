abstract class MembershipFunction {
	public abstract String getLabel();
	public abstract double classify(double value);
	public double classifyCompliment(double value){
	    return 1.0 - classify(value);
    }
	public double classifyIntersection(MembershipFunction membershipFunction, double value){
	    return Math.min(classify(value), membershipFunction.classify(value));
    }
	public double classifyUnion(MembershipFunction membershipFunction, double value){
        return Math.max(classify(value), membershipFunction.classify(value));
    }
	public double cardinal(double[] universe){
        double sum = 0;
        for(double value: universe){
            sum += classify(value);
        }
	    return sum;
    }
	public double cardinalOfIntersection(MembershipFunction membershipFunction, double[] universe){
        double sum = 0;
        for(double value: universe){
            sum += classifyIntersection(membershipFunction, value);
        }
	    return sum;
    }
	public double cardinalOfUnion(MembershipFunction membershipFunction, double[] universe){
        double sum = 0;
        for(double value: universe){
            sum += classifyUnion(membershipFunction, value);
        }
	    return sum;
    }
}