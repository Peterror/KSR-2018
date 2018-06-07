import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Time;

public class Entity {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private double tempOut;
    private double hiTemp;
    private double lowTemp;
    private double outHum;
    private double dewPt;
    private double windSpeed;
    private String windDir;
    private double windRun;
    private double hiSpeed;
    private String hiDir;
    private double windChill;
    private double headIdx;
    private double thwIdx;
    private double bar;
    private double rain;
    private double rainRate;
    private double HeatDD;
    private double coolDD;
    private double inTemp;
    private double inHum;
    private double inDew;
    private double inHeat;
    private double winSamp;
    private Integer windTx;
    private double issRecept;
    private Integer arcInt;

    public Entity(Integer year, Integer month, Integer day, Integer hour, Integer minute,
                  double tempOut, double hiTemp, double lowTemp, double outHum, double dewPt,
                  double windSpeed, String windDir, double windRun, double hiSpeed, String hiDir,
                  double windChill, double headIdx, double thwIdx, double bar, double rain,
                  double rainRate, double heatDD, double coolDD, double inTemp, double inHum,
                  double inDew, double inHeat, double winSamp, Integer windTx, double issRecept,
                  Integer arcInt) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.tempOut = tempOut;
        this.hiTemp = hiTemp;
        this.lowTemp = lowTemp;
        this.outHum = outHum;
        this.dewPt = dewPt;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.windRun = windRun;
        this.hiSpeed = hiSpeed;
        this.hiDir = hiDir;
        this.windChill = windChill;
        this.headIdx = headIdx;
        this.thwIdx = thwIdx;
        this.bar = bar;
        this.rain = rain;
        this.rainRate = rainRate;
        HeatDD = heatDD;
        this.coolDD = coolDD;
        this.inTemp = inTemp;
        this.inHum = inHum;
        this.inDew = inDew;
        this.inHeat = inHeat;
        this.winSamp = winSamp;
        this.windTx = windTx;
        this.issRecept = issRecept;
        this.arcInt = arcInt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("year", year)
                .append("month", month)
                .append("day", day)
                .append("hour", hour)
                .append("minute", minute)
                .append("tempOut", tempOut)
                .append("hiTemp", hiTemp)
                .append("lowTemp", lowTemp)
                .append("outHum", outHum)
                .append("dewPt", dewPt)
                .append("windSpeed", windSpeed)
                .append("windDir", windDir)
                .append("windRun", windRun)
                .append("hiSpeed", hiSpeed)
                .append("hiDir", hiDir)
                .append("windChill", windChill)
                .append("headIdx", headIdx)
                .append("thwIdx", thwIdx)
                .append("bar", bar)
                .append("rain", rain)
                .append("rainRate", rainRate)
                .append("HeatDD", HeatDD)
                .append("coolDD", coolDD)
                .append("inTemp", inTemp)
                .append("inHum", inHum)
                .append("inDew", inDew)
                .append("inHeat", inHeat)
                .append("winSamp", winSamp)
                .append("windTx", windTx)
                .append("issRecept", issRecept)
                .append("arcInt", arcInt)
                .toString();
    }
}
