import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        PrintWriter saveData = new PrintWriter("statistics.txt");
        Parser parser = new Parser();
        List<Entity> universe = parser.parser();
        double[] highestTemperatureDomain = new double[universe.size()];
        double[] lowestTemperatureDomain = new double[universe.size()];
        double[] outsideHumidityDomain = new double[universe.size()];
        double[] insideHumidityDomain = new double[universe.size()];
        double[] windSpeedDomain = new double[universe.size()];
        double[] pressureDomain = new double[universe.size()];
        double[] rainHeightDomain = new double[universe.size()];
        double[] insideTemperatureDomain = new double[universe.size()];
        double[] outsideTemperatureDomain = new double[universe.size()];
        double[] rainRateDomain = new double[universe.size()];

        // summaryzator
        Summarizer summarizer = new Summarizer();

        /*
         * Quantifier dla calego programu(uniwersum)
         */
        List<MembershipFunction> absQuantifierMembershipFunctionList = new ArrayList<>();
        for(int i = 0; i < universe.size()/1000 + 1; i++){
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
                            String.format("%.2f\\%%", temp_value*100),
                            temp_value - 0.075,
                            temp_value + 0,
                            temp_value + 0.075
                    )
            );
        }
        Quantifier relQuantifier = new RelativeQuantifier("About", relQuantifierMembershipFunctionList);
        /*
         * Deklaracja funkcji klasyfikujacych dane zjawisko
         */
        // Funkcje trapezoidalne dla temperatury
        List<MembershipFunction> temperatureMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("high",25,30,100,100));
            add(new TrapezoidalFunction("medium",7,15,20,30));
            add(new TrapezoidalFunction("low",-100,-100,0,15));
        }};
        // Funkcje trapezoidalne dla wilgotnosci
        List<MembershipFunction> humidityMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("high",70,85,100,100));
            add(new TrapezoidalFunction("medium",35,60,70,85));
            add(new TrapezoidalFunction("low",0,0,35,60));
        }};
        // Funkcje trapezoidalne dla predkosci wiatru
        List<MembershipFunction> windSpeedMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("high",38,49,200,200));
            add(new TrapezoidalFunction("medium",11,19,38,49));
            add(new TrapezoidalFunction("low",0,0,11,19));
        }};

        // Funkcje trapezoidalne dla cisnienia
        List<MembershipFunction> pressureMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("high",1020,1040,1200,1200));
            add(new TrapezoidalFunction("medium",980,1000,1020,1040));
            add(new TrapezoidalFunction("low",0,0,980,1000));
        }};

        // Funkcje trapezoidalne dla deszczu
        List<MembershipFunction> rainMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("huge",3,6,100,100));
            add(new TrapezoidalFunction("average",0.5,1,3,4));
            add(new TrapezoidalFunction("minimal",0,0,0.5,1));
        }};

        // Funkcje trapezoidalne dla czestotliwosci opadow
        List<MembershipFunction> rainRateMembershipFunctionList = new ArrayList<MembershipFunction>(){{
            add(new TrapezoidalFunction("high",3,6,100,100));
            add(new TrapezoidalFunction("average",2,4,3,4));
            add(new TrapezoidalFunction("small",0,0,2,4));
        }};

        /*
         * Deklaracja zmiennych lingwistycznych i przypisanie im funkcji klasyfikujacych dane zjawisko
         */
        // Zmienna lingwistyczna dla najwyzszej temperatury
        LinguisticVariable highestTemperature = new LinguisticVariable(
                "highest temperature",
                temperatureMembershipFunctionList
        );
        // Zmienna lingwistyczna dla najwyzszej temperatury
        LinguisticVariable lowestTemperature = new LinguisticVariable(
                "lowest temperature",
                temperatureMembershipFunctionList
        );
        // Zmienna lingwistyczna dla wilgotnosci
        LinguisticVariable insideHumidity = new LinguisticVariable(
                "inside humidity",
                humidityMembershipFunctionList
        );
        // Zmienna lingwistyczna dla wilgotnosci
        LinguisticVariable outsideHumidity = new LinguisticVariable(
                "outside humidity",
                humidityMembershipFunctionList
        );
        // Zmienna lingwistyczna dla cisnienia
        LinguisticVariable pressure = new LinguisticVariable(
                "pressure",
                pressureMembershipFunctionList
        );
        // Zmienna lingwistyczna dla wysokosci opadow
        LinguisticVariable rainHeight = new LinguisticVariable(
                "rain height",
                rainMembershipFunctionList
        );
        // Zmienna lingwistyczna dla czestotliwosci opadow
        LinguisticVariable rainRate = new LinguisticVariable(
                "rain rate",
                rainRateMembershipFunctionList
        );
        // Zmienna lingwistyczna dla predkosci wiatru
        LinguisticVariable windSpeed = new LinguisticVariable(
                "wind speed",
                windSpeedMembershipFunctionList
        );
        // Zmienna lingwistyczna dla predkosci wiatru
        LinguisticVariable insideTemperature = new LinguisticVariable(
                "inside temperature",
                temperatureMembershipFunctionList
        );
        // Zmienna lingwistyczna dla predkosci wiatru
        LinguisticVariable outsideTemperature = new LinguisticVariable(
                "outside temperature",
                temperatureMembershipFunctionList
        );

        /*
         * Inicjalizacja domen zjawisk
         */
        // domena dla najwyzszej temperatury na zewnatrz
        for(int i = 0; i < universe.size(); i++){
            highestTemperatureDomain[i] = universe.get(i).getHiTemp();
        }
        // domena dla najnizszej temperatury na zewnatrz
        for(int i = 0; i < universe.size(); i++){
            lowestTemperatureDomain[i] = universe.get(i).getLowTemp();
        }
        // domena dla wewnetrznej wilgotnosci
        for(int i = 0; i < universe.size(); i++){
            insideHumidityDomain[i] = universe.get(i).getInHum();
        }
        // domena dla zewnetrznej wilgotnosci
        for(int i = 0; i < universe.size(); i++){
            outsideHumidityDomain[i] = universe.get(i).getOutHum();
        }
        // domena dla cisnienia
        for(int i = 0; i < universe.size(); i++){
            pressureDomain[i] = universe.get(i).getBar();
        }
        // domena dla wysokosci opadow
        for(int i = 0; i < universe.size(); i++){
            rainHeightDomain[i] = universe.get(i).getRain();
        }
        // domena dla czestotliwosci opadow
        for(int i = 0; i < universe.size(); i++){
            rainRateDomain[i] = universe.get(i).getRainRate();
        }
        // domena dla predkosci wiatru
        for(int i = 0; i < universe.size(); i++){
            windSpeedDomain[i] = universe.get(i).getWindSpeed();
        }
        // domena dla temperatury wewnetrzej
        for(int i = 0; i < universe.size(); i++){
            insideTemperatureDomain[i] = universe.get(i).getInTemp();
        }
        // domena dla temperatury zewnetrznej
        for(int i = 0; i < universe.size(); i++){
            outsideTemperatureDomain[i] = universe.get(i).getTempOut();
        }

        /*  Podsumowania
        */
/*
        // Zapisanie wartosci z sumaryzatora relatywnego(najwyzsze temperatury)
        for(String line: summarizer.summarize(highestTemperature, relQuantifier, highestTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(najwyzsze temperatury)
        for(String line: summarizer.summarize(highestTemperature, absQuantifier, highestTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(najnizsze temperatury)
        for(String line: summarizer.summarize(lowestTemperature, absQuantifier, lowestTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(wewnetrzna wilgotnosc)
        for(String line: summarizer.summarize(insideHumidity, absQuantifier, insideHumidityDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(zewnetrzna wilgotnosc)
        for(String line: summarizer.summarize(outsideHumidity, absQuantifier, outsideHumidityDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(cisnienie)
        for(String line: summarizer.summarize(pressure, absQuantifier, pressureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(wielkosc opadow)
        for(String line: summarizer.summarize(rainHeight, absQuantifier, rainHeightDomain)){
            saveData.println(line);
        }
        saveData.println();
*/
        // Zapisanie wartosci z sumaryzatora absolutnego(czestotliwosc opadow)
        for(String line: summarizer.summarize(rainRate, absQuantifier, rainRateDomain)){
            saveData.println(line);
        }
        saveData.println();
/*
        // Zapisanie wartosci z sumaryzatora absolutnego(predkosc wiatru)
        for(String line: summarizer.summarize(windSpeed, absQuantifier, windSpeedDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(wewnetrzna temperatura)
        for(String line: summarizer.summarize(insideTemperature, absQuantifier, insideTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego(zewnetrzna temperatura)
        for(String line: summarizer.summarize(outsideTemperature, absQuantifier, outsideTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();
*/
        /* Podsumowania zlozone
         */
        //        highestTemperature, lowestTemperature, insideHumidity, outsideHumidity, pressure,
        //        rainHeight, rainRate, windSpeed, insideTemperature, outsideTemperature
/*
        // Zapisanie wartosci z sumaryzatora absolutnego (powiazanie miedzy czestotliwoscia opadow i wielkoscia opadow)
        for(String line: summarizer.summarizeAND(rainHeight, highestTemperature, absQuantifier, rainHeightDomain, highestTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();
*/
        // Zapisanie wartosci z sumaryzatora relatywnego (powiazanie miedzy czestotliwoscia opadow i wielkoscia opadow)
        for(String line: summarizer.summarizeAND(rainHeight, rainRate, relQuantifier, rainHeightDomain, rainRateDomain)){
            saveData.println(line);
        }
        saveData.println();
/*
        // Zapisanie wartosci z sumaryzatora absolutnego (powiazanie miedzy predkoscia wiatru a minimalna temperatura)
        for(String line: summarizer.summarizeAND(windSpeed, lowestTemperature, absQuantifier, windSpeedDomain, lowestTemperatureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego (powiazanie miedzy predkoscia wiatru a cisnieniem)
        for(String line: summarizer.summarizeAND(windSpeed, pressure, absQuantifier, windSpeedDomain, pressureDomain)){
            saveData.println(line);
        }
        saveData.println();

        // Zapisanie wartosci z sumaryzatora absolutnego (powiazanie miedzy predkoscia wiatru a wielkoscia opadow)
        for(String line: summarizer.summarizeAND(windSpeed, rainHeight, absQuantifier, windSpeedDomain, rainHeightDomain)){
            saveData.println(line);
        }
        saveData.println();
*/

        saveData.close();

    }
}
