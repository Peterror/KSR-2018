import java.util.ArrayList;
import java.util.List;

public abstract class Set {
    String name;
    List<Entity> entities;
    BelongFunction function;

    public Set(List<Entity> set, String name, BelongFunction function) {
        this.entities = set;
        this.name=name;
        this.function=function;
    }

    abstract public Set Complement(Set uberSet);

    abstract public Set Sum(Set otherSet);
    /*{
        List<Entity> sumList = entities;
        for (Entity o:otherSet.entities)
        {
            if(!sumList.contains(o))
                sumList.add(o);
        }
        return new Set(sumList,"Sum of " + this.name + "and ");
    }*/

    abstract public Set Intersection(Set otherSet);
    /*{
        List<Entity> inter = new ArrayList<Entity>();
        for (Entity o:otherSet.entities)
        {
            if(entities.contains(o))
                inter.add(o);
        }
        return new Set(inter,"Intersection of " + this.name + otherSet.name);
    }*/

}
