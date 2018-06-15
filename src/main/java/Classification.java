public class Classification {
    private String label;
    private double membership;

    public Classification(String label, double membership){
        this.label = label;
        this.membership = membership;
    }

    public double getMembership() {
        return membership;
    }

    public String getLabel() {
        return label;
    }
}
