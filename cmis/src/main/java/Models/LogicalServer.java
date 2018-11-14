package Models;

public class LogicalServer {

    private int colID;
    private String backUp;
    private boolean monitored;
    private String respGroup;

    public LogicalServer(int acolId, String abackUp, boolean  aMonitored, String aRespGroup)
    {
        setColID(acolId);
        setBackUp(abackUp);
        setMonitored(aMonitored);
        setRespGroup(aRespGroup);
    }

    public String getRespGroup() {
        return respGroup;
    }

    public void setRespGroup(String respGroup) {
        this.respGroup = respGroup;
    }

    public boolean isMonitored() {
        return monitored;
    }

    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp;
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }
}
