package Models;

public class Editors {

    private int colID;
    private int userID;
    private String userName;
    private String accessLvl;
    private String phoneNum;
    private String location;

    public Editors(int aColID, int aUserID, String aUserName, String aAccessLvl, String aPhoneNum, String aLocation)
    {
        setColID(aColID);
        setUserID(aUserID);
        setUserName(aUserName);
        setAccessLvl(aAccessLvl);
        setPhoneNum(aPhoneNum);
        setLocation(aLocation);

    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccessLvl() {
        return accessLvl;
    }

    public void setAccessLvl(String accessLvl) {
        this.accessLvl = accessLvl;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
