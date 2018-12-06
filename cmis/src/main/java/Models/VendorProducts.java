package Models;

public class VendorProducts {

    private int colID;
    private String prodName;
    private String version;
    private String description;
    private Boolean classified;

    public VendorProducts(int aColID, String aProdName, String aVersion, String aDescription, Boolean aClassified)
    {
        setColID(aColID);
        setProdName(aProdName);
        setVersion(aVersion);
        setDescription(aDescription);
        setClassified(aClassified);
    }

    public int getColID() {
        return colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isClassified() {
        return classified;
    }

    public void setClassified(Boolean classified) {
        this.classified = classified;
    }
}
