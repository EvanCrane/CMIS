package Models;

public class Application {

    private int collID;
    private String subType;
    private String userInterface;
    private String size;
    private String totalUsers;
    private String concUsers;
    private String priority;

    public Application(int aCollId, String aSubType, String aUserInteface, String aSize, String aTotalUsers, String aConcUsers, String aPriority)
    {
        setCollID(aCollId);
        setSubType(aSubType);
        setUserInterface(aUserInteface);
        setSize(aSize);
        setTotalUsers(aTotalUsers);
        setConcUsers(aConcUsers);
        setPriority(aPriority);
    }

    public int getCollID() {
        return collID;
    }

    public void setCollID(int collID) {
        this.collID = collID;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(String userInterface) {
        this.userInterface = userInterface;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(String totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getConcUsers() {
        return concUsers;
    }

    public void setConcUsers(String concUsers) {
        this.concUsers = concUsers;
    }
}
