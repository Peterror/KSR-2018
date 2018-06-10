import java.util.List;

public class ClassicSet extends Set
{
    public ClassicSet(List<Entity> set, String name, BelongFunction function) {
        super(set, name, function);
    }

    public Set Complement()
    {
        ClassicSet complementSet = new ClassicSet(this.entities,"Complement of "+this.name,this.function);
        for (Entity o:complementSet.entities)
        {
            o.setLevelOfBelong(1-o.getLevelOfBelong());
        }
        return complementSet;
    }

    public Set Sum(Set otherSet)
    {
        ClassicSet sumSet = new ClassicSet(this.entities,"Complement of "+this.name,this.function);
        for (Entity e:sumSet.entities
                ) {
            double temp = 0;
            for (Entity o : entities
                    ) {
                if (o.getYear() == e.getYear() && o.getMonth() == e.getMonth() &&
                        o.getDay() == e.getDay() && o.getHour() == e.getHour() &&
                        o.getMinute() == e.getMinute())
                    temp = o.getLevelOfBelong();
            }
            for (Entity o : otherSet.entities
                    ) {
                if (o.getYear() == e.getYear() && o.getMonth() == e.getMonth() &&
                        o.getDay() == e.getDay() && o.getHour() == e.getHour() &&
                        o.getMinute() == e.getMinute()) {
                    if (o.getLevelOfBelong() > temp)
                        temp = o.getLevelOfBelong();
                }

            }
            e.setLevelOfBelong(temp);
        }
        return sumSet;
    }

    public Set Intersection(Set otherSet)
    {
        ClassicSet interSet = new ClassicSet(this.entities,"Complement of "+this.name,this.function);
        for (Entity e:interSet.entities
                ) {
            double temp = 0;
            for (Entity o : entities
                    ) {
                if (o.getYear() == e.getYear() && o.getMonth() == e.getMonth() &&
                        o.getDay() == e.getDay() && o.getHour() == e.getHour() &&
                        o.getMinute() == e.getMinute())
                    temp = o.getLevelOfBelong();
            }
            for (Entity o : otherSet.entities
                    ) {
                if (o.getYear() == e.getYear() && o.getMonth() == e.getMonth() &&
                        o.getDay() == e.getDay() && o.getHour() == e.getHour() &&
                        o.getMinute() == e.getMinute()) {
                    if (o.getLevelOfBelong() < temp)
                        temp = o.getLevelOfBelong();
                }

            }
            e.setLevelOfBelong(temp);
        }
        return interSet;
    }
}
