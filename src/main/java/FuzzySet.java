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
        if (name.equals("tempOut"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getTempOut()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("outHum"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getOutHum()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("windSpeed"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getWindSpeed()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("bar"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getBar()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("rain"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getRain()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("rainRate"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getRainRate()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("inTemp"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getInTemp()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("inHum"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getInHum()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("hiTemp"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getHiTemp()));
                if (e.getLevelOfBelong()>0)
                    support++;
            }
            return;
        }
        if (name.equals("loTemp"))
        {
            for (Entity e:entities
                 )
            {
                e.setLevelOfBelong(function.classify(e.getLowTemp()));
                if (e.getLevelOfBelong()>0)
                    support++;
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
