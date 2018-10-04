package Daos;

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

    public static List<Collection> allCollections;

    @Autowired
    static JdbcTemplate jdbcTemplate;

    public void addCollection(Collection form)
    {
        String sqlAddCollection = "INSERT INTO COLLECTIONS (collecID, collecType, collecFullName, collecAccronym, collecStatus, collecBackUp, collecServType ) VALUE(?, ?, ?, ?, ?, ?, ?)";

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
            String sqlAddOrg = "INSERT INTO ORGANIZATION (collecID, orgName) VALUE (?, ?)";
            String orgName = aOrg;
            getJdbcTemplate().update(sqlAddOrg, collecID, orgName);

        }

        for(String aDesOrg : form.getDesOrganizations())
        {
            String sqlAddDesOrg = "INSERT INTO DESIGN_ORGANIZATION (collecID, desOrg) VALUE (?, ?)";
            String desOrg = aDesOrg;
            getJdbcTemplate().update(sqlAddDesOrg, collecID, desOrg);
        }


    }

    public List<String> allCollections()
    {
        String sqlAllCollections = "SELECT FULL_NAME, ACRONYM, TYPE, STATUS FROM COLLECTIONS";
        return jdbcTemplate.queryForList(sqlAllCollections, String.class);
    }




}
