import java.util.ArrayList;
import java.util.List;

public class ClassicSet extends Set
{
    public ClassicSet(List<Entity> set, String name, BelongFunction function) {
        super(set, name, function);
    }

    public Set Complement(Set uberSet)
    {
        List<Entity> compl = new ArrayList<Entity>();
        for (Entity o:uberSet.entities)
        {
            if (!entities.contains(o))
                compl.add(o);
        }
        return new ClassicSet(compl,"Complement of " + this.name,this.function);
    }

    public Set Sum(Set otherSet)
    {
        List<Entity> sumList = entities;
        for (Entity o:otherSet.entities)
        {
            if(!sumList.contains(o))
                sumList.add(o);
        }
        return new ClassicSet(sumList,"Sum of " + this.name + "and " + otherSet.name,this.function);
    }

    public Set Intersection(Set otherSet)
    {
        List<Entity> inter = new ArrayList<Entity>();
        for (Entity o:otherSet.entities)
        {
            if(entities.contains(o))
                inter.add(o);
        }
        return new ClassicSet(inter,"Intersection of " + this.name + otherSet.name,this.function);
    }
}
