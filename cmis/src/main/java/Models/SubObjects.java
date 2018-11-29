package Models;

public class SubObjects {

    private int colID;
    private String subType;
    private String subName;
    private String version;
    private boolean classified;

    public SubObjects(int aColID, String aSubType, String aSubName, String aVersion, boolean aClassified)
    {
        setColID(aColID);
        setSubType(aSubType);
        setSubName(aSubName);
        setVersion(aVersion);
        setClassified(aClassified);
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isClassified() {
        return classified;
    }

    public void setClassified(boolean classified) {
        this.classified = classified;
    }
}
