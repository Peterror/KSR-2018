import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        List<Entity> Entities = parser.parser();
        BelongFunction temperatureFunction = new TrapezoidalFunction("temperature",-10,20,50,100);
        BelongFunction humidityFunction = new TrapezoidalFunction("humidity",0,70,100,120);
        BelongFunction windFunction = new TrapezoidalFunction("wind speed",0,100,200,300);
        BelongFunction pressureFunction = new TriangleFunction("pressure",970,1040,1110);
        BelongFunction rainFunction = new TriangleFunction("rain",0,0.5,1);
        BelongFunction rainRateFunction = new TrapezoidalFunction("rain rate",0,4,1000000,2000000);
        Set tempOutSet = new FuzzySet(Entities,"tempOut",temperatureFunction);
        LinguisticVariable tempOutLinguisticVariable =
                new LinguisticVariable("outside temperature",new String[]{"low","medium","high"},tempOutSet.entities);
        Set outHumSet = new FuzzySet(Entities,"outHum",humidityFunction);
        LinguisticVariable outHumLinguisticVariable =
                new LinguisticVariable("outside humidity",new String[]{"low","medium","high"},tempOutSet.entities);
        Set windSpeedSet = new FuzzySet(Entities,"windSpeed",windFunction);
        Set barSet = new FuzzySet(Entities,"bar",pressureFunction);
        LinguisticVariable barLinguisticVariable =
                new LinguisticVariable("pressure",new String[]{"low","medium","high"},tempOutSet.entities);
        Set rainSet = new FuzzySet(Entities,"rain",rainFunction);
        Set rainRateSet = new FuzzySet(Entities,"rainRate",rainRateFunction);
        Set inTempSet = new FuzzySet(Entities,"inTemp",temperatureFunction);
        Set inHumSet = new FuzzySet(Entities,"inHum",humidityFunction);
        Set hiTempSet = new FuzzySet(Entities,"hiTemp",temperatureFunction);
        Set loTempSet = new FuzzySet(Entities,"loTemp",temperatureFunction);
        System.out.println(tempOutLinguisticVariable.AbsoluteQuantificator(0));
        System.out.println(tempOutLinguisticVariable.AbsoluteQuantificator(1));
        System.out.println(tempOutLinguisticVariable.AbsoluteQuantificator(2));
        System.out.println(tempOutLinguisticVariable.RelativeQuantificator(0));
        System.out.println(tempOutLinguisticVariable.RelativeQuantificator(1));
        System.out.println(tempOutLinguisticVariable.RelativeQuantificator(2));
        System.out.println(outHumLinguisticVariable.AbsoluteQuantificator(0));
        System.out.println(outHumLinguisticVariable.AbsoluteQuantificator(1));
        System.out.println(outHumLinguisticVariable.AbsoluteQuantificator(2));
        System.out.println(outHumLinguisticVariable.RelativeQuantificator(0));
        System.out.println(outHumLinguisticVariable.RelativeQuantificator(1));
        System.out.println(outHumLinguisticVariable.RelativeQuantificator(2));
        System.out.println(barLinguisticVariable.AbsoluteQuantificator(0));
        System.out.println(barLinguisticVariable.AbsoluteQuantificator(1));
        System.out.println(barLinguisticVariable.AbsoluteQuantificator(2));
        System.out.println(barLinguisticVariable.RelativeQuantificator(0));
        System.out.println(barLinguisticVariable.RelativeQuantificator(1));
        System.out.println(barLinguisticVariable.RelativeQuantificator(2));


    }
}
