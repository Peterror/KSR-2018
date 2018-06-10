import java.util.List;

class LinguisticVariable{
	private String name;
	private String setOfVariables[];
	private List<Entity> universe;
	private Integer classifications[]={0,0,0};


	public String getName(){
	    return name;
    }
	public void classify(double value)
    {
        for (Entity o:universe
             ) {
            if (o.getLevelOfBelong()<=0.3)
                classifications[0]++;
            else
            {
                if(o.getLevelOfBelong()>0.3 && o.getLevelOfBelong()<=0.6)
                    classifications[1]++;
                else
                    classifications[2]++;
            }

        }
    }

    public String AbsoluteQuantificator(Integer index)
    {
        return (double)classifications[index]/universe.size()*100.0 + "% of days had" + setOfVariables[index] + name + ".";
    }
}