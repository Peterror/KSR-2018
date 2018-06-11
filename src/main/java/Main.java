import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        PrintWriter saveData = new PrintWriter("statistics.txt");
        Parser parser = new Parser();
        List<Entity> Entities = parser.parser();
        BelongFunction temperatureFunction = new TrapezoidalFunction("temperature",-10,20,50,100);
        BelongFunction humidityFunction = new TrapezoidalFunction("humidity",0,70,100,120);
        BelongFunction windFunction = new TrapezoidalFunction("wind speed",0,80,180,300);
        BelongFunction pressureFunction = new TriangleFunction("pressure",970,1040,1110);
        BelongFunction rainFunction = new TriangleFunction("rain",0,0.5,1);
        BelongFunction rainRateFunction = new TrapezoidalFunction("rain rate",0,4,1000000,2000000);
        FuzzySet tempOutSet = new FuzzySet(Entities,"tempOut",temperatureFunction);
        Summarizer tempOutSummarizer = new Summarizer("tempOut",new String[]{"colder","medium","warmer"},
                tempOutSet,"outside temperature");
        FuzzySet outHumSet = new FuzzySet(Entities,"outHum",humidityFunction);
        Summarizer outHumSummarizer = new Summarizer("outHum",new String[]{"low","medium","high"},
                outHumSet,"outside humidity");
        FuzzySet windSpeedSet = new FuzzySet(Entities,"windSpeed",windFunction);
        Summarizer windSpeedSummarizer = new Summarizer("windSpeed",new String[]{"slower","average","faster"},
                windSpeedSet,"wind speed");
        //CompoundSummarizer tempHum = new CompoundSummarizer("tempOut",new String[]{"colder","medium","warmer"},
          //      tempOutSet,"outHum",new String[]{"low","medium","high"},outHumSet,"and");
        FuzzySet barSet = new FuzzySet(Entities,"bar",pressureFunction);
        Summarizer barSummarizer = new Summarizer("bar",new String[]{"lower","average","higher"},
                barSet,"atmospherical pressure");
        FuzzySet rainSet = new FuzzySet(Entities,"rain",rainFunction);
        Summarizer rainSetSummarizer = new Summarizer("rain",new String[]{"lower","average","higher"},
                rainSet,"time of rain");
        FuzzySet rainRateSet = new FuzzySet(Entities,"rainRate",rainRateFunction);
        Summarizer rainRateSummarizer = new Summarizer("rainRate",new String[]{"lower","average","higher"},
                rainRateSet,"rain rate");
        FuzzySet inTempSet = new FuzzySet(Entities,"inTemp",temperatureFunction);
        Summarizer tempinSummarizer = new Summarizer("inTemp",new String[]{"colder","medium","warmer"},
                inTempSet,"inside temperature");
        FuzzySet inHumSet = new FuzzySet(Entities,"inHum",humidityFunction);
        Summarizer inHumSummarizer = new Summarizer("inHum",new String[]{"low","medium","high"},
                inHumSet,"inside humidity");
        FuzzySet hiTempSet = new FuzzySet(Entities,"hiTemp",temperatureFunction);
        Summarizer hiTempSummarizer = new Summarizer("hiTemp",new String[]{"colder","medium","warmer"},
                hiTempSet,"highest temperature");
        FuzzySet loTempSet = new FuzzySet(Entities,"loTemp",temperatureFunction);
        Summarizer loTempSummarizer = new Summarizer("loTemp",new String[]{"colder","medium","warmer"},
                loTempSet,"lowest temperature");


        for (int i=0;i<3;i++)
        {
            saveData.println(tempOutSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(tempOutSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(outHumSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(outHumSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(windSpeedSummarizer.Summarize(i, true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(windSpeedSummarizer.Summarize(i, false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(barSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(barSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(rainSetSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(rainSetSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(rainRateSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(rainRateSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(tempinSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(tempinSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(inHumSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(inHumSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(hiTempSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(hiTempSummarizer.Summarize(i,false));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(loTempSummarizer.Summarize(i,true));
        }
        for (int i=0;i<3;i++)
        {
            saveData.println(loTempSummarizer.Summarize(i,false));
        }
        saveData.close();

    }
}
