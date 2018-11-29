package Daos;

import Mappers.*;
import Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CollectionDao extends JdbcDaoSupport {

    @Autowired
    public CollectionDao(DataSource dataSource){
        this.setDataSource(dataSource);
    }




    @Autowired
    static JdbcTemplate jdbcTemplate;

    public void addCollection(Collection form)
    {
        String sqlAddCollection = "INSERT INTO COLLECTIONS (ID, TYPE, FULL_NAME, ACRONYM, STATUS, BACKUP_SERVER, SERVICE_TYPE) VALUE(?, ?, ?, ?, ?, ?, ?)";

        String maxIndexSql = "SELECT IFNULL(MAX(ID), 0) FROM COLLECTIONS";
        int collecID = getJdbcTemplate().queryForObject(maxIndexSql, int.class);
        ++collecID;

        String collecType = form.getCollecType();
        String collecFullName = form.getFullName();
        String collecAccronym = form.getAccronym();
        String collecStatus = form.getStatus();
        String collecBackUp = form.getBackUP();
        String collecServType = form.getServiceType();

        getJdbcTemplate().update(sqlAddCollection, collecID, collecType, collecFullName, collecAccronym, collecStatus, collecBackUp, collecServType);

        String sqlAddOrg = "INSERT INTO COLLEC_ORGANIZATION (COL_ID, ORGANIZATION)  VALUE (?, ?)";
        String orgName = form.getOrganizations();
        getJdbcTemplate().update(sqlAddOrg, collecID, orgName);

        String sqlAddDesOrg = "INSERT INTO COLLEC_DESIGN_ORGANIZATION (COL_ID, DES_ORG) VALUE (?, ?)";
        String desOrg = form.getDesOrganizations();
        getJdbcTemplate().update(sqlAddDesOrg, collecID, desOrg);

    }
    
    public void deleteCollection(int collID){
        //Deleting collection from high level 
        String sql = "DELETE FROM COLLECTIONS WHERE ID = ?";
        Object [] colArg = new Object [] {collID};

        getJdbcTemplate().update(sql, collID);

        String sql2 = "DELETE FROM COLLEC_ORGANIZATION WHERE COL_ID=?";
        getJdbcTemplate().update(sql2, collID);
        String sql3 = "DELETE FROM COLLEC_DESIGN_ORGANIZATION WHERE COL_ID=?";
        getJdbcTemplate().update(sql3, collID);

        //Delete General Level

        Object[] doesGeneral;
        String testGeneral = "SELECT COL_ID FROM GENERAL WHERE COL_ID =?";
        try{
            doesGeneral = getJdbcTemplate().queryForObject(testGeneral, colArg, Object[].class);
        }
        catch(EmptyResultDataAccessException e)
        {
            doesGeneral = null;
        }


        if(doesGeneral != null)
        {
            String generalDelete = "DELETE FROM GENERAL WHERE COL_ID=?";
            getJdbcTemplate().update(generalDelete, collID);
        }

        //Delete Controls/Impacts

        String testSql = "SELECT COL_ID FROM CONTROLS_IMPACTS WHERE COL_ID = ?";

        Object[] testVal;
        try
        {
            testVal = getJdbcTemplate().queryForObject(testSql, colArg, Object[].class);
        }
        catch (EmptyResultDataAccessException e)
        {
            testVal = null;
        }

        if(testVal != null)
        {
            String controlSql = "DELETE FROM CONTROLS_IMPACTS WHERE COL_ID=?";
            getJdbcTemplate().update(controlSql, collID);
        }

        // Delete Logical Server info
        String testLogic = "SELECT COL_ID FROM LOGICAL_SERVER WHERE COL_ID = ?";

        Object[] doesLogic;
        try
        {
            doesLogic = getJdbcTemplate().queryForObject(testLogic, colArg, Object[].class);
        }
        catch (EmptyResultDataAccessException e)
        {
            doesLogic = null;
        }

        if(doesLogic != null)
        {
            String deleteServer = "DELETE FROM LOGICAL_SERVER WHERE COL_ID=?";
            getJdbcTemplate().update(deleteServer, collID);
        }


        //Deleting Environment Info
        String testEnv = "SELECT COL_ID FROM ENVIRONMENT WHERE COL_ID = ?";

        Object[] doesEnv;
        try
        {
            doesEnv = getJdbcTemplate().queryForObject(testEnv, colArg, Object[].class);
        }
        catch (EmptyResultDataAccessException e)
        {
            doesEnv = null;
        }

        if(doesEnv == null) {
            if (doesEnv != null) {
                String deleteEnv = "DELETE FROM ENVIRONMENT WHERE COL_ID=?";
                getJdbcTemplate().update(deleteEnv, collID);
            }

        }

    // Delete Application info
        String testApp = "SELECT COL_ID FROM APPLICATION WHERE COL_ID = ?";

        Object doesApp;
        try
        {
            doesApp = getJdbcTemplate().queryForObject(testApp, colArg, Object[].class);
        }
        catch (EmptyResultDataAccessException e)
        {
            doesApp = null;
        }

        if(doesApp != null) {
            String deleteApp = "DELETE FROM APPLICATION WHERE COL_ID=?";
            getJdbcTemplate().update(deleteApp, collID);
        }
    }
    
    public void updateCollection(Collection form)
    {

        // ************ Update high level collection stuff  *******************
        String thisSQL = "UPDATE COLLECTIONS SET TYPE = ?, FULL_NAME = ?, ACRONYM = ?, STATUS = ?," +
                " BACKUP_SERVER = ?, SERVICE_TYPE = ? WHERE ID = ?;";
        int collID = form.getCollecIid();
        Object [] colArg = new Object [] {collID};
        String type = form.getCollecType();
        String fullName = form.getFullName();
        String acronym = form.getAccronym();
        String status = form.getStatus();
        String backUp = form.getBackUP();
        String servType = form.getServiceType();
        getJdbcTemplate().update(thisSQL, type, fullName, acronym, status, backUp, servType, collID);

        String thisSQL2 = "UPDATE COLLEC_ORGANIZATION SET ORGANIZATION = ? WHERE COL_ID = ?;";
        String org = form.getOrganizations();
        getJdbcTemplate().update(thisSQL2, org, collID);

        String thisSQL3 = "UPDATE COLLEC_DESIGN_ORGANIZATION SET DES_ORG = ? WHERE COL_ID = ?;";
        String desOrg = form.getDesOrganizations();
        getJdbcTemplate().update(thisSQL3, desOrg, collID);

        // Update General Level

        Object[] doesGeneral;
        String testGeneral = "SELECT COL_ID FROM GENERAL WHERE COL_ID =?";
        try{
            doesGeneral = getJdbcTemplate().queryForObject(testGeneral, colArg, Object[].class);
        }
        catch(EmptyResultDataAccessException e)
        {
            doesGeneral = null;
        }


        if(doesGeneral == null)
        {
            String generalInsert = "INSERT INTO GENERAL (COL_ID, USER_POPULATION, PRIM_CUSTOM, SUBCON_MAINT, DESCRIPTION)" +
                    " VALUES (?, ?, ?, ?, ?)";
            getJdbcTemplate().update(generalInsert, collID, form.getUserPopulation(), form.getPrimaryCustomer(), form.getSubContractMain()
            , form.getGenDescription());
        }
        else
        {
            String generalUpdate = "UPDATE GENERAL SET USER_POPULATION = ?, PRIM_CUSTOM = ?, SUBCON_MAINT = ?, " +
                    "DESCRIPTION = ? WHERE COL_ID = ?";
            getJdbcTemplate().update(generalUpdate, form.getUserPopulation(), form.getPrimaryCustomer(), form.getSubContractMain()
            , form.getGenDescription(), collID);
        }

        // Update Controls/Impacts

        String testSql = "SELECT COL_ID FROM CONTROLS_IMPACTS WHERE COL_ID = ?";

        Object[] testVal;
        try
        {
            testVal = getJdbcTemplate().queryForObject(testSql, colArg, Object[].class);
        }
        catch (EmptyResultDataAccessException e)
        {
            testVal = null;
        }

        if(testVal == null)
        {
            String controlSql1 = "INSERT INTO CONTROLS_IMPACTS (COL_ID, FUNCTION_CLASS, DATA_CLASS, MISS_ESS_RAT) " +
                    "VALUES (?, ?, ?, ?)";
            getJdbcTemplate().update(controlSql1, collID, form.getFunctionClass(), form.getDataClass(), form.getMissEssRating());
        }
        else
        {
            String controlSql = "UPDATE CONTROLS_IMPACTS SET FUNCTION_CLASS = ?, DATA_CLASS = ?, " +
                    "MISS_ESS_RAT = ? WHERE COL_ID = ?";
            getJdbcTemplate().update(controlSql, form.getFunctionClass(), form.getDataClass(), form.getMissEssRating(), collID);
        }



        // Update Logical Server info
        if(form.getCollecType().equals("Logical Server"))
        {
            String testLogic = "SELECT COL_ID FROM LOGICAL_SERVER WHERE COL_ID = ?";

            Object[] doesLogic;
            try
            {
                doesLogic = getJdbcTemplate().queryForObject(testLogic, colArg, Object[].class);
            }
            catch (EmptyResultDataAccessException e)
            {
                doesLogic = null;
            }

            if(doesLogic == null)
            {
                String insertServer = "INSERT INTO LOGICAL_SERVER (COL_ID, BACKUP_SERVER, MONITORED, RESP_GROUP) " +
                        "VALUES (?, ?, ?, ?)";
                getJdbcTemplate().update(insertServer, collID, form.getBackUpServer(), form.isServerMonitored(), form.getRespGroup());
            }
            else
            {
                String serverSql = "UPDATE LOGICAL_SERVER SET BACKUP_SERVER = ?, MONITORED = ?," +
                        " RESP_GROUP = ? WHERE COL_ID = ?";
                getJdbcTemplate().update(serverSql, form.getBackUpServer(), form.isServerMonitored(), form.getRespGroup()
                        , collID);
            }

        }

        // Update Environment info
        if(form.getCollecType().equals("Environment"))
        {
            String testEnv = "SELECT COL_ID FROM ENVIRONMENT WHERE COL_ID = ?";

            Object[] doesEnv;
            try
            {
                doesEnv = getJdbcTemplate().queryForObject(testEnv, colArg, Object[].class);
            }
            catch (EmptyResultDataAccessException e)
            {
                doesEnv = null;
            }

            if(doesEnv == null)
            {
                String insertEnv = "INSERT INTO ENVIRONMENT (COL_ID, SUBTYPE, BACKUP_SEVER, MONITORED, RESP_GROUP) " +
                        "VALUES (?, ?, ?, ?, ?)";
                getJdbcTemplate().update(insertEnv, collID, form.getEnvSubtype(), form.getEnvBackup(),
                        form.isEnvMonitored(), form.getEnvRespGroup());

            }
            else
            {
                String envSql = "UPDATE ENVIRONMENT SET SUBTYPE = ?, BACKUP_SEVER = ?, MONITORED = ?, " +
                        "RESP_GROUP = ? WHERE COL_ID = ?";
                getJdbcTemplate().update(envSql, form.getEnvSubtype(), form.getEnvBackup(), form.isEnvMonitored()
                        , form.getEnvRespGroup(), collID);
            }

        }

        // Update Application info
        if(form.getCollecType().equals("Instance Application"))
        {
            String testApp = "SELECT COL_ID FROM APPLICATION WHERE COL_ID = ?";

            Object doesApp;
            try
            {
                doesApp = getJdbcTemplate().queryForObject(testApp, colArg, Object[].class);
            }
            catch (EmptyResultDataAccessException e)
            {
                doesApp = null;
            }

            if(doesApp == null)
            {
                String insertApp = "INSERT INTO APPLICATION (COL_ID, SUBTYPE, USER_INTERFACE, SIZE, TOTAL_USERS, " +
                        "CONC_USERS, PRIORITY) VALUES (?, ?, ?, ?, ?, ?, ?)";
                getJdbcTemplate().update(insertApp, collID, form.getAppSubType(), form.getUserInterface(),
                        form.getAppSize(), form.getTotalUsers(), form.getTotalConcUsers(), form.getPriority());
            }
            else
            {
                String appSql = "UPDATE APPLICATION SET SUBTYPE = ?, USER_INTERFACE = ?, SIZE = ?, TOTAL_USERS = ?," +
                        " CONC_USERS = ?, PRIORITY = ? WHERE COL_ID = ?";
                getJdbcTemplate().update(appSql, form.getAppSubType(), form.getUserInterface(), form.getAppSize(),
                        form.getTotalUsers(), form.getTotalConcUsers(), form.getPriority(), collID);
            }

        }

    }



    public List<String> AllOrgs()
    {
        String sqlAllOrgs = "SELECT ORGANIZATION FROM cmis_1.ORGANIZATIONS";
        List <String> resultSet = getJdbcTemplate().queryForList(sqlAllOrgs, String.class);
        return resultSet;
    }


    public List<Collection> allCollections()
    {
        String sqlAllCollections = CollectionMapper.BASE_SQL;
        return getJdbcTemplate().query(sqlAllCollections, new CollectionMapper());
    }

    public String collecType(int collId)
    {
        String sqlColID = "SELECT TYPE FROM COLLECTIONS WHERE ID = ?";
        Object[] myArg = new Object[] {collId};
        return getJdbcTemplate().queryForObject(sqlColID, myArg, String.class);
    }

    // Change to get the full collection
    public Collection getFullCollection (int collecID)
    {
        String thisSQL = CollectionMapper.BASE_SQL + " WHERE ID = ?";
        Object[] parameter = new Object[] {collecID};
        CollectionMapper mapper = new CollectionMapper();
        Collection fullCollection = getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        fullCollection.setOrganizations(getCollecOrg(collecID));
        fullCollection.setDesOrganizations(getCollecDesOrg(collecID));

        // General stuff
        General generalInfo = getGeneral(collecID);
        fullCollection.setUserPopulation(generalInfo == null || generalInfo.getPrimaryCustomer() == null ? "" : generalInfo.getUserPop());
        fullCollection.setPrimaryCustomer(generalInfo == null || generalInfo.getPrimaryCustomer() == null ? "" : generalInfo.getPrimaryCustomer());
        fullCollection.setSubContractMain(generalInfo == null || generalInfo.getSubcontMaintainer() == null ? "" : generalInfo.getSubcontMaintainer());
        fullCollection.setGenDescription(generalInfo == null || generalInfo.getDescription() == null ? "" : generalInfo.getDescription());

        // Controls/Impacts
        ControlsImpacts contImpact = getControlsImpacts(collecID);
        fullCollection.setFunctionClass(contImpact == null || contImpact.getFunctionClass() == null ? "" : contImpact.getFunctionClass());
        fullCollection.setDataClass(contImpact == null || contImpact.getFunctionClass() == null ? "" : contImpact.getDataClass());
        fullCollection.setMissEssRating(contImpact == null || contImpact.getMissEssRating() == null ? "" : contImpact.getMissEssRating());

        if(fullCollection.getCollecType().equals("Instance Application"))
        {
            //Application
            Application thisApp = getApplication(collecID);
            fullCollection.setAppSubType(thisApp == null || thisApp.getSubType() == null ? "" : thisApp.getSubType());
            fullCollection.setUserInterface(thisApp == null || thisApp.getUserInterface() == null ? "" : thisApp.getUserInterface());
            fullCollection.setAppSize(thisApp == null || thisApp.getSize() == null ? "" : thisApp.getSize());
            fullCollection.setTotalUsers(thisApp == null || thisApp.getTotalUsers() == null ? "" : thisApp.getTotalUsers());
            fullCollection.setTotalConcUsers(thisApp == null || thisApp.getConcUsers() == null ? "" : thisApp.getConcUsers());
            fullCollection.setPriority(thisApp == null || thisApp.getPriority() == null ? "" : thisApp.getPriority());
        }
        else if(fullCollection.getCollecType().equals("Logical Server"))
        {
            //Server
            LogicalServer thisServer = getLogicalServer(collecID);
            fullCollection.setBackUpServer(thisServer == null || thisServer.getBackUp() == null ? "" : thisServer.getBackUp());
            fullCollection.setServerMonitored(thisServer.isMonitored());
            fullCollection.setRespGroup(thisServer == null || thisServer.getRespGroup() == null ? "" : thisServer.getRespGroup());
        }
        else if(fullCollection.getCollecType().equals("Environment"))
        {
            //Environment
            Environment thisEnvironment = getEnvironment(collecID);
            fullCollection.setEnvSubtype(thisEnvironment == null || thisEnvironment.getSubType() == null ? "" : thisEnvironment.getSubType());
            fullCollection.setEnvBackup(thisEnvironment == null || thisEnvironment.getBackUp() == null ? "" : thisEnvironment.getBackUp());
            fullCollection.setEnvMonitored(thisEnvironment.isMonitored());
            fullCollection.setEnvRespGroup(thisEnvironment == null || thisEnvironment.getRespGroup() == null ? "" : thisEnvironment.getRespGroup());
        }

        // Contacts
        fullCollection.setCollContacts(getAllContacts(collecID));

        // Vendor Products
        fullCollection.setCollVendorProducts(getAllVendorProducts(collecID));

        // SubObjects
        fullCollection.setCollSubObjects(getAllSubObjects(collecID));

        // Relationships
        fullCollection.setCollRelationships(getAllRelationships(collecID));

        // Editors
        fullCollection.setCollEditors(getAllEditors(collecID));

        return fullCollection;

    }

    public String getCollecOrg(int collecID)
    {
        String thisSQL = "SELECT ORGANIZATION FROM COLLEC_ORGANIZATION WHERE COL_ID = ?";
        Object[] myArg = new Object[] {collecID};
        return getJdbcTemplate().queryForObject(thisSQL, myArg, String.class);
    }

    public String getCollecDesOrg(int collecID)
    {
        String thisSQL = "SELECT DES_ORG FROM COLLEC_DESIGN_ORGANIZATION WHERE COL_ID = ?";
        Object[] myArg = new Object[] {collecID};
        return getJdbcTemplate().queryForObject(thisSQL, myArg, String.class);
    }

    public General getGeneral(int collecId)
    {
        String thisSQL = GeneralMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] parameter = new Object[] {collecId};
        GeneralMapper mapper = new GeneralMapper();
        try{
            return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public List<Contacts> getAllContacts(int collecID)
    {
        String sqlAllContacts = ContactsMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] collID = new Object[] {collecID};
        try
        {
            return getJdbcTemplate().query(sqlAllContacts, new ContactsMapper(), collID);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }

    }

    public Contacts getAContact(int aCollID, int aUserID)
    {
        String sqlContact = ContactsMapper.BASE_SQL + " WHERE COL_ID = ? AND USER_ID = ?";
        Object[] collID = new Object[] {aCollID};
        Object[] userID = new Object[] {aUserID};
        try
        {
            return getJdbcTemplate().queryForObject(sqlContact, new ContactsMapper(), collID, userID);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public List<VendorProducts> getAllVendorProducts(int collecID)
    {
        String sqlAllVendorProducts = VendorMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] collID = new Object[] {collecID};
        try
        {
            return getJdbcTemplate().query(sqlAllVendorProducts, new VendorMapper(), collID);
        }
        catch (EmptyResultDataAccessException e){return null;}

    }

    public List<SubObjects> getAllSubObjects(int collecID)
    {
        String sqlAllSubObjects = SubObjectsMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] collID = new Object[] {collecID};
        try
        {
            return getJdbcTemplate().query(sqlAllSubObjects, new SubObjectsMapper(), collID);
        }
        catch(EmptyResultDataAccessException e){return null;}
    }

    public List<Relationships> getAllRelationships(int collecID)
    {
        String sqlAllRelationships = RelationshipsMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] collID = new Object[] {collecID};
        try
        {
            return getJdbcTemplate().query(sqlAllRelationships, new RelationshipsMapper(), collID);
        }
        catch(EmptyResultDataAccessException e) {return null;}
    }

    public List<Editors> getAllEditors(int collecID)
    {
        String sqlAllEditors = EditorsMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] collID = new Object[] {collecID};
        try
        {
            return getJdbcTemplate().query(sqlAllEditors, new EditorsMapper(), collID);
        }
        catch(EmptyResultDataAccessException e) {return null;}
    }


    public ControlsImpacts getControlsImpacts(int collecId)
    {
        String thisSQL = ControlsImpactsMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] parameter = new Object[] {collecId};
        ControlsImpactsMapper mapper = new ControlsImpactsMapper();
        try{
            return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public Environment getEnvironment (int collecId)
    {
        String thisSQL = EnvironmentMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] parameter = new Object[] {collecId};
        EnvironmentMapper mapper = new EnvironmentMapper();
        try{
            return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public LogicalServer getLogicalServer (int collecId)
    {
        String thisSQL = LogicalServerMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] parameter = new Object[] {collecId};
        LogicalServerMapper mapper = new LogicalServerMapper();
        try{
            return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public Application getApplication (int collecId)
    {
        String thisSQL = ApplicationMapper.BASE_SQL + " WHERE COL_ID = ?";
        Object[] parameter = new Object[] {collecId};
        ApplicationMapper mapper = new ApplicationMapper();
        try{
            return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }

    }





}
