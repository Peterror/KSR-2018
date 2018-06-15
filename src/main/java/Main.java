import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        PrintWriter saveData = new PrintWriter("statistics.txt");
        Parser parser = new Parser();
        List<Entity> Entities = parser.parser();
        double[] universe = new double[Entities.size()];
        // summaryzator
        Summarizer summarizer = new Summarizer();
        // Quantifier dla calego programu
        List<MembershipFunction> absQuantifierMembershipFunctionList = new ArrayList<>();
        for(int i = 0; i < Entities.size()/1000 + 1; i++){
            int temp_value = i * 1000;
            absQuantifierMembershipFunctionList.add(
                    new TriangleFunction(
                            String.valueOf(temp_value),
                            temp_value - 750,
                            temp_value + 0,
                            temp_value + 750
                    )
            );
        }
        Quantifier absQuantifier = new AbsoluteQuantifier("About", absQuantifierMembershipFunctionList);

        List<MembershipFunction> relQuantifierMembershipFunctionList = new ArrayList<>();
        for(int i = 0; i < 11; i++){
            double temp_value = i * 0.1;
            relQuantifierMembershipFunctionList.add(
                    new TriangleFunction(
                            String.format("%.2f%%", temp_value*100),
                            temp_value - 0.075,
                            temp_value + 0,
                            temp_value + 0.075
                    )
            );
        }
        Quantifier relQuantifier = new RelativeQuantifier("About", relQuantifierMembershipFunctionList);

        // Funkcje trapezoidalne dla temperatury
        MembershipFunction highTemperature = new TrapezoidalFunction("high",25,30,100,100);
        MembershipFunction mediumTemperature = new TrapezoidalFunction("medium",7,15,20,30);
        MembershipFunction lowTemperature = new TrapezoidalFunction("low",-100,-100,0,15);
        // Zmienna lingwistyczna dla temperatury
        List<MembershipFunction> linguisticMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(highTemperature);
            add(mediumTemperature);
            add(lowTemperature);
        }};
        LinguisticVariable temperature = new LinguisticVariable(
                "temperatures",
                linguisticMembershipFunctionList
        );

        // Step1 uniwersum dla temperatury na zewnatrz
        for(int i = 0; i < Entities.size(); i++){
            universe[i] = Entities.get(i).getTempOut();
        }
        // Step2a Zapisanie wartosci z sumaryzatora relatywnego
        for(String line: summarizer.summarize(temperature, relQuantifier, universe)){
            saveData.println(line);
        }
        // Step2b Zapisanie wartosci z sumaryzatora absolutnego
        for(String line: summarizer.summarize(temperature, absQuantifier, universe)){
            saveData.println(line);
        }

        /*
        // Funkcje trapezoidalne dla wilgotnosci
        MembershipFunction humidityFunction = new TrapezoidalFunction("humidity",0,70,100,120);

        // Funkcje trapezoidalne dla predkosci wiatru
        MembershipFunction windFunction = new TrapezoidalFunction("wind speed",0,80,180,300);

        // Funkcje trapezoidalne dla cisnienia
        MembershipFunction pressureFunction = new TriangleFunction("pressure",970,1040,1110);

        // Funkcje trapezoidalne dla deszczu
        MembershipFunction rainFunction = new TriangleFunction("rain",0,0.5,1);

        // Funkcje trapezoidalne dla czestotliwosci opadow
        MembershipFunction rainRateFunction = new TrapezoidalFunction("rain rate",0,4,1000000,2000000);*/


        saveData.close();

    }
}
