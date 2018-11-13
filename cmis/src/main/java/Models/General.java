package Models;

public class General {

    private int collecID;

    private String userPop;

    private String primaryCustomer;

    private String subcontMaintainer;

    private String description;

    public General(int aCollecID, String aUserPop, String aPrimaryCustomer, String aSubcontMaintainer, String aDescription)
    {
        setCollecID(aCollecID);
        setUserPop(aUserPop);
        setPrimaryCustomer(aPrimaryCustomer);
        setSubcontMaintainer(aSubcontMaintainer);
        setDescription(aDescription);
    }

    public int getCollecID() {
        return collecID;
    }

    public void setCollecID(int collecID) {
        this.collecID = collecID;
    }

    public String getUserPop() {
        return userPop;
    }

    public void setUserPop(String userPop) {
        this.userPop = userPop;
    }

    public String getPrimaryCustomer() {
        return primaryCustomer;
    }

    public void setPrimaryCustomer(String primaryCustomer) {
        this.primaryCustomer = primaryCustomer;
    }

    public String getSubcontMaintainer() {
        return subcontMaintainer;
    }

    public void setSubcontMaintainer(String subcontMaintainer) {
        this.subcontMaintainer = subcontMaintainer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
