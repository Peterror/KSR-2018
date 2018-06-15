public class TriangleFunction extends MembershipFunction {
	String label;
	double a, b, c;
	
	public TriangleFunction(String label, double a, double b, double c){
		this.label = label;
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public double classify(double value){
		if(value > a && value <= b){
			return (value - a) / (b - a);
		}else if(value > b && value <= c){
			return (c - value) / (c - b);
		}else{
			return 0.0;
		}
	}
		
	public String getLabel(){
		return this.label;
	}
}
