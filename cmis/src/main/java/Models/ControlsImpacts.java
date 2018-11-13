package Models;

public class ControlsImpacts {

    private int collID;
    private String functionClass;
    private String dataClass;
    private String missEssRating;

    public ControlsImpacts(int aCollID, String aFunctionClass, String aDataClass, String aMissEssRating)
    {
        setCollID(aCollID);
        setFunctionClass(aFunctionClass);
        setDataClass(aDataClass);
        setMissEssRating(aMissEssRating);
    }

    public String getMissEssRating() {
        return missEssRating;
    }

    public void setMissEssRating(String missEssRating) {
        this.missEssRating = missEssRating;
    }

    public String getDataClass() {
        return dataClass;
    }

    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    public String getFunctionClass() {
        return functionClass;
    }

    public void setFunctionClass(String functionClass) {
        this.functionClass = functionClass;
    }

    public int getCollID() {
        return collID;
    }

    public void setCollID(int collID) {
        this.collID = collID;
    }
}
