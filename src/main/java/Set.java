import java.util.List;

public abstract class Set {
    String name;
    List<Entity> entities;
    BelongFunction function;
    Integer support = 0;

    public Set(List<Entity> set, String name, BelongFunction function) {
        this.entities = set;
        this.name=name;
        this.function=function;

    }

    abstract public Set Complement(Set uberSet);

    abstract public Set Sum(Set otherSet);

    abstract public Set Intersection(Set otherSet);

    public void countBelong(double value)
    {
        for (Entity o:entities
             )
        {
            o.setLevelOfBelong(function.classify(value));
            if (o.getLevelOfBelong()>0)
                support++;
        }
    }

}
