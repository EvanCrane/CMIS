package Models;

public class Relationships {

    private int colID;
    private String type;
    private String value;

    public Relationships(int aColID, String aType, String aValue)
    {
        setColID(aColID);
        setType(aType);
        setValue(aValue);
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
