import java.util.List;

public class FuzzySet extends Set

{
    Integer alphaSection=0;
    public FuzzySet(List<Entity> set, String name, BelongFunction function) {
        super(set, name, function);
    }


    public Set Complement(Set uberSet) {
        return null;
    }

    public Set Sum(Set otherSet) {
        return null;
    }

    public Set Intersection(Set otherSet) {
        return null;
    }

    public void countAlphaSection(double alpha)
    {
        for (Entity o:entities
             )
        {
            if (o.getLevelOfBelong()>alpha)
                alphaSection++;
        }
    }
}
