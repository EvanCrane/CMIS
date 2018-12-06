package Models;

public class Environment {

    private int collID;
    private String subType;
    private String backUp;
    private Boolean monitored;
    private String respGroup;

    public Environment(int aCollID, String aSubType, String aBackUp, Boolean aMonitored, String aRespGroup)
    {
        setCollID(aCollID);
        setSubType(aSubType);
        setBackUp(aBackUp);
        setMonitored(aMonitored);
        setRespGroup(aRespGroup);
    }

    public String getRespGroup() {
        return respGroup;
    }

    public void setRespGroup(String respGroup) {
        this.respGroup = respGroup;
    }

    public Boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(Boolean monitored) {
        this.monitored = monitored;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getCollID() {
        return collID;
    }

    public void setCollID(int collID) {
        this.collID = collID;
    }
}
