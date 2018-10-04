package Models;

import java.util.List;

public class Collection {

    // In collection table
    private int collecIid;
    private String collecType;
    private String fullName;
    private String accronym;
    private String status;
    private String backUP;
    private String serviceType;

    private List<String> organizations;
    private List<String> desOrganizations;

    public Collection(int aColelctionId, String aCollecType, String aFullName, String aAccronym, String aStatus, String aBackupName, List<String> aOrganizations, String aServiceType, List<String> aDesOrganizations){

        setCollecIid(aColelctionId);
        setCollecType(aCollecType);
        setFullName(aFullName);
        setAccronym(aAccronym);
        setStatus(aStatus);
        setBackUP(aBackupName);
        setOrganizations(aOrganizations);
        setServiceType(aServiceType);
        setDesOrganizations(aDesOrganizations);
    }


    public int getCollecIid() {
        return collecIid;
    }

    public void setCollecIid(int collecIid) {
        this.collecIid = collecIid;
    }

    public String getCollecType() {
        return collecType;
    }

    public void setCollecType(String collecType) {
        this.collecType = collecType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccronym() {
        return accronym;
    }

    public void setAccronym(String accronym) {
        this.accronym = accronym;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackUP() {
        return backUP;
    }

    public void setBackUP(String backUP) {
        this.backUP = backUP;
    }

    public List<String> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<String> organizations) {
        this.organizations = organizations;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<String> getDesOrganizations() {
        return desOrganizations;
    }

    public void setDesOrganizations(List<String> desOrganizations) {
        this.desOrganizations = desOrganizations;
    }
}
