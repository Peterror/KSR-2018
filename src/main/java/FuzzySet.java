import java.util.List;

public class FuzzySet extends Set

{
    Integer alphaSection=0;
    public FuzzySet(List<Entity> set, String name, BelongFunction function) {
        super(set, name, function);
        countLevelOfBelong();
    }

    public void countLevelOfBelong()
    {
        if (name=="tempOut")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getTempOut()));
            }
            return;
        }
        if (name=="outHum")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getOutHum()));
            }
            return;
        }
        if (name=="windSpeed")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getWindSpeed()));
            }
            return;
        }
        if (name=="bar")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getBar()));
            }
            return;
        }
        if (name=="rain")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getRain()));
            }
            return;
        }
        if (name=="rainRate")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getRainRate()));
            }
            return;
        }
        if (name=="inTemp")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getInTemp()));
            }
            return;
        }
        if (name=="inHum")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getInHum()));
            }
            return;
        }
        if (name=="hiTemp")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getHiTemp()));
            }
            return;
        }
        if (name=="loTemp")
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getLowTemp()));
            }
        }
    }

    public Set Complement() {
        FuzzySet complementSet = new FuzzySet(this.entities,
                "Complement of "+this.name,this.function);
        for (Entity e:complementSet.entities)
        {
            e.setLevelOfBelong(1-e.getLevelOfBelong());
        }
        return complementSet;

    }

    public Set Sum(Set otherSet) {
        FuzzySet sumSet = new FuzzySet(this.entities,
                "Sum of "+this.name+"and "+otherSet.name,this.function);
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

    public Set Intersection(Set otherSet) {
        FuzzySet interSet = new FuzzySet(this.entities,
                "Intersection of "+this.name+"and "+otherSet.name,this.function);
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
