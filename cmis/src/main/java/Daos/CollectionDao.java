package Daos;

import Mappers.*;
import Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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



    public List<String> AllOrgs()
    {
        String sqlAllOrgs = "SELECT ORGANIZATION FROM cmis_1.ORGANIZATIONS";
        List <String> resultSet = getJdbcTemplate().queryForList(sqlAllOrgs, String.class);
        return resultSet;
    }


    public List<Collection> allCollections()
    {
        String sqlAllCollections = CollectionMapper.BASE_SQL;
        //return getJdbcTemplate().queryForList(sqlAllCollections, String.class);
        return getJdbcTemplate().query(sqlAllCollections, new CollectionMapper());
    }

    public String collecType(int collId)
    {
        String sqlColID = "SELECT TYPE FROM COLLECTIONS WHERE ID = ?";
        Object[] myArg = new Object[] {collId};
        return getJdbcTemplate().queryForObject(sqlColID, myArg, String.class);
    }

    public Collection collectionHighlights(int collecID)
    {
        String thisSQL = CollectionMapper.BASE_SQL + " WHERE ID = ?";
        Object[] parameter = new Object[] {collecID};
        CollectionMapper mapper = new CollectionMapper();
        return getJdbcTemplate().queryForObject(thisSQL, parameter, mapper);

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
