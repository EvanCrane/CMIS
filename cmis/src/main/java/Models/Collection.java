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

    private String organizations;
    private String desOrganizations;

    public Collection(String aCollecType, String aFullName, String aAccronym, String aStatus, String aBackupName, String aOrganizations, String aServiceType, String aDesOrganizations){

        //setCollecIid(aColelctionId);
        setCollecType(aCollecType);
        setFullName(aFullName);
        setAccronym(aAccronym);
        setStatus(aStatus);
        setBackUP(aBackupName);
        setOrganizations(aOrganizations);
        setServiceType(aServiceType);
        setDesOrganizations(aDesOrganizations);
    }

    public Collection(int collID, String aCollecType, String aFullName, String aAccronym, String aStatus, String aBackupName, String aServiceType){

        setCollecIid(collID);
        setCollecType(aCollecType);
        setFullName(aFullName);
        setAccronym(aAccronym);
        setStatus(aStatus);
        setBackUP(aBackupName);

        setServiceType(aServiceType);

    }


    public Collection(){}

    public Collection(String aFullName, String aAcronym, String aType, String aStatus, int id)
    {
        setFullName(aFullName);
        setAccronym(aAcronym);
        setCollecType(aType);
        setStatus(aStatus);
        setCollecIid(id);

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

    public String getOrganizations() {
        return this.organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesOrganizations() {
        return desOrganizations;
    }

    public void setDesOrganizations(String desOrganizations) {
        this.desOrganizations = desOrganizations;
    }
}
