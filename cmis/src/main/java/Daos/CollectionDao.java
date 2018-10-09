package Daos;

import Mappers.CollectionMapper;
import Models.Collection;
import org.springframework.beans.factory.annotation.Autowired;
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

        for(String aOrg : form.getOrganizations())
        {
            String sqlAddOrg = "INSERT INTO COLLEC_ORGANIZATION (COL_ID, ORGANIZATION)  VALUE (?, ?)";
            String orgName = aOrg;
            getJdbcTemplate().update(sqlAddOrg, collecID, orgName);

        }

        for(String aDesOrg : form.getDesOrganizations())
        {
            String sqlAddDesOrg = "INSERT INTO COLLEC_DESIGN_ORGANIZATION (COL_ID, DES_ORG) VALUE (?, ?)";
            String desOrg = aDesOrg;
            getJdbcTemplate().update(sqlAddDesOrg, collecID, desOrg);
        }




    }

    public List<String> AllDesOrgs()
    {
        String sqlAllDesOrgs = "SELECT IFNULL (DESIGN_ORGANIZATION, '') FROM cmis_1.DESIGN_ORGANIZATIONS";
        List<String> resultSet = getJdbcTemplate().queryForList(sqlAllDesOrgs, String.class);
        return resultSet;
    }

    public List<String> AllOrgs()
    {
        String sqlAllOrgs = "SELECT ORGANIZATION FROM cmis_1.ORGANIZATIONS";
        List <String> resultSet = getJdbcTemplate().queryForList(sqlAllOrgs, String.class);
        return resultSet;
    }


    public List<Collection> allCollections()
    {
        String sqlAllCollections = "SELECT FULL_NAME, ACRONYM, TYPE, STATUS FROM COLLECTIONS";
        //return getJdbcTemplate().queryForList(sqlAllCollections, String.class);
        return getJdbcTemplate().query(sqlAllCollections, new CollectionMapper());
    }




}
