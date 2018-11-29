package Models;

public class Contacts {

    private int colID;
    private int userID;
    private String contName;
    private String contType;
    private String phoneNum;
    private String location;

    public Contacts(int aColID, int aUserID, String aContName, String aContType, String aPhoneNum, String aLocation)
    {
        setColID(aColID);
        setUserID(aUserID);
        setContName(aContName);
        setContType(aContType);
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

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getContType() {
        return contType;
    }

    public void setContType(String contType) {
        this.contType = contType;
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
}
