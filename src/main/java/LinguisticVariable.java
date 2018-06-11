import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class LinguisticVariable{
	private String name;
	private String setOfVariables[];
	private List<Entity> universe;
	private Integer classifications[]={0,0,0};

    public String[] getSetOfVariables() {
        return setOfVariables;
    }

    public Integer[] getClassifications() {
        return classifications;
    }

    public List<Entity> getUniverse() {
        return universe;
    }

    public LinguisticVariable(String name, String[] setOfVariables, List<Entity> universe) {
        this.name = name;
        this.setOfVariables = setOfVariables;
        this.universe = universe;
        classify();
    }

    public String getName(){
	    return name;
    }
	public void classify()
    {
        for (Entity o:universe
             ) {
            if (o.getLevelOfBelong()<=0.4)
            {
                classifications[0]++;
                System.out.println(o.getLevelOfBelong() + " " + o.getTempOut());
            }
            else
            {
                if(o.getLevelOfBelong()>0.4 && o.getLevelOfBelong()<=0.85)
                {
                    classifications[1]++;
                    System.out.println(o.getLevelOfBelong() + " " + o.getTempOut());
                }
                else
                {
                    classifications[2]++;
                    System.out.println(o.getLevelOfBelong() + " " + o.getTempOut());
                }

            }

        }
    }

    public String AbsoluteQuantificator(Integer index)
    {
        return "About " + BigDecimal.valueOf(((double)classifications[index])/universe.size()*100.0).setScale
                (1, RoundingMode.HALF_UP).doubleValue() + "% ";
    }

    public String RelativeQuantificator(Integer index)
    {
        long round = Math.round((double)classifications[index]/1000);
        return "About "+round*1000+" ";
    }
}