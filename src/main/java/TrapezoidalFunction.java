public class TrapezoidalFunction extends MembershipFunction {
	String label;
	double a, b, c, d;
	
	public TrapezoidalFunction(String label, double a, double b, double c, double d){
		this.label = label;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public double classify(double value){
		if(value > a && value <= b){
			return (value - a) / (b - a);
		}else if(value > b && value <= c){
			return 1.0;
		}else if(value > c && value <= d){
			return (d - value) / (d - c);
		}else{
			return 0.0;
		}
	}
	
	public String getLabel(){
		return this.label;
	}
}
