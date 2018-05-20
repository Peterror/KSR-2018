public class Entity {

    private Integer age;
    private String workclass;
    private Integer fnlwgt;
    private String education;
    private Integer educationNum;
    private String maritalStatus;
    private String occupation;
    private String relationship;
    private String race;
    private String sex;
    private Integer capitalGain;
    private Integer capitalLoss;
    private Integer hoursPerWeek;
    private String nativeCountry;
    private String probability;

    public Entity(Integer age, String workclass,
                  Integer fnlwgt, String education,
                  Integer educationNum, String maritalStatus,
                  String occupation,
                  String relationship, String race, String sex,
                  Integer capitalGain, Integer capitalLoss, Integer hoursPerWeek,
                  String nativeCountry, String probability)
    {
        this.age = age;
        this.workclass = workclass;
        this.fnlwgt = fnlwgt;
        this.education = education;
        this.educationNum = educationNum;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.relationship = relationship;
        this.race = race;
        this.sex = sex;
        this.capitalGain = capitalGain;
        this.capitalLoss = capitalLoss;
        this.hoursPerWeek = hoursPerWeek;
        this.nativeCountry = nativeCountry;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("age", age)
                .append("workclass", workclass)
                .append("fnlwgt", fnlwgt)
                .append("education", education)
                .append("educationNum", educationNum)
                .append("maritalStatus", maritalStatus)
                .append("occupation", occupation)
                .append("relationship", relationship)
                .append("race", race)
                .append("sex", sex)
                .append("capitalGain", capitalGain)
                .append("capitalLoss", capitalLoss)
                .append("hoursPerWeek", hoursPerWeek)
                .append("nativeCountry", nativeCountry)
                .append("probability", probability)
                .toString();
    }
}
