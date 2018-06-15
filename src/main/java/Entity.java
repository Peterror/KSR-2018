import org.apache.commons.lang3.builder.ToStringBuilder;

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
    private double heatDD;
    private double coolDD;
    private double inTemp;
    private double inHum;
    private double inDew;
    private double inHeat;
    private double winSamp;
    private Integer windTx;
    private double issRecept;
    private Integer arcInt;
    private double levelOfBelong = 0;  // Co to robi?

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
        this.heatDD = heatDD;
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
                .append("HeatDD", heatDD)
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

    public double getParameterByName(String name)
    {
        if ("tempOut".equals(name)) {
            return this.tempOut;
        } else if ("hiTemp".equals(name)) {
            return this.hiTemp;
        } else if ("lowTemp".equals(name)) {
            return this.lowTemp;
        } else if ("outHum".equals(name)) {
            return this.outHum;
        } else if ("dewPt".equals(name)) {
            return this.dewPt;
        } else if ("windSpeed".equals(name)) {
            return this.windSpeed;
        } else if ("windRun".equals(name)) {
            return this.windRun;
        } else if ("hiSpeed".equals(name)) {
            return this.hiSpeed;
        } else if ("windChill".equals(name)) {
            return this.windChill;
        } else if ("bar".equals(name)) {
            return this.bar;
        } else if ("rain".equals(name)) {
            return this.bar;
        } else if ("rainRate".equals(name)) {
            return this.rainRate;
        }
        return 0;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public double getTempOut() {
        return tempOut;
    }

    public void setTempOut(double tempOut) {
        this.tempOut = tempOut;
    }

    public double getHiTemp() {
        return hiTemp;
    }

    public void setHiTemp(double hiTemp) {
        this.hiTemp = hiTemp;
    }

    public double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(double lowTemp) {
        this.lowTemp = lowTemp;
    }

    public double getOutHum() {
        return outHum;
    }

    public void setOutHum(double outHum) {
        this.outHum = outHum;
    }

    public double getDewPt() {
        return dewPt;
    }

    public void setDewPt(double dewPt) {
        this.dewPt = dewPt;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public double getWindRun() {
        return windRun;
    }

    public void setWindRun(double windRun) {
        this.windRun = windRun;
    }

    public double getHiSpeed() {
        return hiSpeed;
    }

    public void setHiSpeed(double hiSpeed) {
        this.hiSpeed = hiSpeed;
    }

    public String getHiDir() {
        return hiDir;
    }

    public void setHiDir(String hiDir) {
        this.hiDir = hiDir;
    }

    public double getWindChill() {
        return windChill;
    }

    public void setWindChill(double windChill) {
        this.windChill = windChill;
    }

    public double getHeadIdx() {
        return headIdx;
    }

    public void setHeadIdx(double headIdx) {
        this.headIdx = headIdx;
    }

    public double getThwIdx() {
        return thwIdx;
    }

    public void setThwIdx(double thwIdx) {
        this.thwIdx = thwIdx;
    }

    public double getBar() {
        return bar;
    }

    public void setBar(double bar) {
        this.bar = bar;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getRainRate() {
        return rainRate;
    }

    public void setRainRate(double rainRate) {
        this.rainRate = rainRate;
    }

    public double getHeatDD() {
        return heatDD;
    }

    public void setHeatDD(double heatDD) {
        heatDD = heatDD;
    }

    public double getCoolDD() {
        return coolDD;
    }

    public void setCoolDD(double coolDD) {
        this.coolDD = coolDD;
    }

    public double getInTemp() {
        return inTemp;
    }

    public void setInTemp(double inTemp) {
        this.inTemp = inTemp;
    }

    public double getInHum() {
        return inHum;
    }

    public void setInHum(double inHum) {
        this.inHum = inHum;
    }

    public double getInDew() {
        return inDew;
    }

    public void setInDew(double inDew) {
        this.inDew = inDew;
    }

    public double getInHeat() {
        return inHeat;
    }

    public void setInHeat(double inHeat) {
        this.inHeat = inHeat;
    }

    public double getWinSamp() {
        return winSamp;
    }

    public void setWinSamp(double winSamp) {
        this.winSamp = winSamp;
    }

    public Integer getWindTx() {
        return windTx;
    }

    public void setWindTx(Integer windTx) {
        this.windTx = windTx;
    }

    public double getIssRecept() {
        return issRecept;
    }

    public void setIssRecept(double issRecept) {
        this.issRecept = issRecept;
    }

    public Integer getArcInt() {
        return arcInt;
    }

    public void setArcInt(Integer arcInt) {
        this.arcInt = arcInt;
    }

    public double getLevelOfBelong() {
        return levelOfBelong;
    }

    public void setLevelOfBelong(double levelOfBelong) {
        this.levelOfBelong = levelOfBelong;
    }
}
