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
    public double sumSupport(double[] universe){
	    double supp = 0;
	    for (double value:universe){
	        if (classify(value)>0)
            {
                supp++;
            }
        }
        return supp;
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

    public double classifyIntersectionInDifferentDomains(
            MembershipFunction differentDomainMembershipFunction,
            double thisDomainValue,
            double differentDomainValue
    ){
        return Math.min(classify(thisDomainValue), differentDomainMembershipFunction.classify(differentDomainValue));
    }

    public double classifyUnionInDifferentDomains(
            MembershipFunction differentDomainMembershipFunction,
            double thisDomainValue,
            double differentDomainValue
    ){
        return Math.max(classify(thisDomainValue), differentDomainMembershipFunction.classify(differentDomainValue));
    }

    public double sumSupportOfIntersectionInDifferentDomains(
            MembershipFunction membershipFunction2,
            double[] universe1,
            double[] universe2
    ){
        double supp = 0;
        for (int i = 0; i < universe1.length; i++ ){
            if (classifyIntersectionInDifferentDomains(membershipFunction2, universe1[i], universe2[i]) > 0)
            {
                supp++;
            }
        }
        return supp;
    }

    public double cardinalOfIntersectionInDifferentDomains(
            MembershipFunction differentDomainMembershipFunction,
            double[] thisUniverse,
            double[] differentUniverse
    ){
        double sum = 0;
        for(int i = 0; i < thisUniverse.length; i++){
            sum += classifyIntersectionInDifferentDomains(
                    differentDomainMembershipFunction,
                    thisUniverse[i],
                    differentUniverse[i]);
        }
        return sum;
    }
    public double cardinalOfUnionInDifferentDomains(
            MembershipFunction differentDomainMembershipFunction,
            double[] thisUniverse,
            double[] differentUniverse
    ){
        double sum = 0;
        for(int i = 0; i < thisUniverse.length; i++){
            sum += classifyIntersectionInDifferentDomains(
                    differentDomainMembershipFunction,
                    thisUniverse[i],
                    differentUniverse[i]);
        }
        return sum;
    }
}