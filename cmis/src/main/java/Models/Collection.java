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

    //General
    private String userPopulation;
    private String primaryCustomer;
    private String subContractMain;
    private String genDescription;

    //Controls/Impacts
    private String functionClass;
    private String dataClass;
    private String missEssRating;

    //Application
    private String appSubType;
    private String userInterface;
    private String appSize;
    private String totalUsers;
    private String totalConcUsers;
    private String priority;

    //Server
    private String backUpServer;
    private boolean serverMonitored;
    private String respGroup;

    //Environment
    private String envSubtype;
    private String envBackup;
    private boolean envMonitored;
    private String envRespGroup;

    // Contacts
    private List<Contacts> collContacts;

    // Vendor Products
    private List<VendorProducts> collVendorProducts;

    // SubObjects
    private List<SubObjects> collSubObjects;

    // Relationships
    private List<Relationships> collRelationships;

    // Editors
    private List<Editors> collEditors;



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

    public Collection(int aColID, String aType, String aFullName, String aAcronym, String aStatus, String aBackup, String aServType, String aOrg, String aDesOrg)
    {
        setCollecIid(aColID);
        setCollecType(aType);
        setFullName(aFullName);
        setAccronym(aAcronym);
        setStatus(aStatus);
        setBackUP(aBackup);
        setServiceType(aServType);
        setOrganizations(aOrg);
        setDesOrganizations(aDesOrg);

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

    public String getUserPopulation() {
        return userPopulation;
    }

    public void setUserPopulation(String userPopulation) {
        this.userPopulation = userPopulation;
    }

    public String getPrimaryCustomer() {
        return primaryCustomer;
    }

    public void setPrimaryCustomer(String primaryCustomer) {
        this.primaryCustomer = primaryCustomer;
    }

    public String getSubContractMain() {
        return subContractMain;
    }

    public void setSubContractMain(String subContractMain) {
        this.subContractMain = subContractMain;
    }

    public String getGenDescription() {
        return genDescription;
    }

    public void setGenDescription(String genDescription) {
        this.genDescription = genDescription;
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

    public String getAppSubType() {
        return appSubType;
    }

    public void setAppSubType(String appSubType) {
        this.appSubType = appSubType;
    }

    public String getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(String userInterface) {
        this.userInterface = userInterface;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(String totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getTotalConcUsers() {
        return totalConcUsers;
    }

    public void setTotalConcUsers(String totalConcUsers) {
        this.totalConcUsers = totalConcUsers;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBackUpServer() {
        return backUpServer;
    }

    public void setBackUpServer(String backUpServer) {
        this.backUpServer = backUpServer;
    }

    public boolean isServerMonitored() {
        return serverMonitored;
    }

    public void setServerMonitored(boolean serverMonitored) {
        this.serverMonitored = serverMonitored;
    }

    public String getRespGroup() {
        return respGroup;
    }

    public void setRespGroup(String respGroup) {
        this.respGroup = respGroup;
    }

    public String getEnvSubtype() {
        return envSubtype;
    }

    public void setEnvSubtype(String envSubtype) {
        this.envSubtype = envSubtype;
    }

    public String getEnvBackup() {
        return envBackup;
    }

    public void setEnvBackup(String envBackup) {
        this.envBackup = envBackup;
    }

    public boolean isEnvMonitored() {
        return envMonitored;
    }

    public void setEnvMonitored(boolean envMonitored) {
        this.envMonitored = envMonitored;
    }

    public String getEnvRespGroup() {
        return envRespGroup;
    }

    public void setEnvRespGroup(String envRespGroup) {
        this.envRespGroup = envRespGroup;
    }

    public List<Contacts> getCollContacts() {
        return collContacts;
    }

    public void setCollContacts(List<Contacts> collContacts) {
        this.collContacts = collContacts;
    }

    public List<VendorProducts> getCollVendorProducts() {
        return collVendorProducts;
    }

    public void setCollVendorProducts(List<VendorProducts> collVendorProducts) {
        this.collVendorProducts = collVendorProducts;
    }

    public List<SubObjects> getCollSubObjects() {
        return collSubObjects;
    }

    public void setCollSubObjects(List<SubObjects> collSubObjects) {
        this.collSubObjects = collSubObjects;
    }

    public List<Relationships> getCollRelationships() {
        return collRelationships;
    }

    public void setCollRelationships(List<Relationships> collRelationships) {
        this.collRelationships = collRelationships;
    }

    public List<Editors> getCollEditors() {
        return collEditors;
    }

    public void setCollEditors(List<Editors> collEditors) {
        this.collEditors = collEditors;
    }
}
