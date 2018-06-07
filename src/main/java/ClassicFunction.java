public class ClassicFunction extends BelongFunction {
    String name;
    double a;

    public ClassicFunction(String name, double a) {
        this.name = name;
        this.a = a;
    }


    public String getName() {
        return name;
    }

    public double classify(double value) {
        if (value>=a)
            return 1;
        else
            return 0;
    }
}
