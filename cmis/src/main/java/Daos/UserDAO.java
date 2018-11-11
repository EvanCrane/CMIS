package Daos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;

import Mappers.UserMapper;
import Models.Users;

import Utils.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;


/*
The DAO classes are ones used to access the database for queries such as Insert, Update,
and delete.

The UserDAO class is used to manipulate the APP_USER table.

*/
@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport{

    @Autowired
    public UserDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    // This method is used to find a user in the database given a userName
    public Users findUserAccount(String userName){
        //Select ... from APP_USER u Where u.USER_NAME = ?
        String sql = UserMapper.BASE_SQL + "WHERE u.USER_NAME = ?";
        Object[] params = new Object[] {userName};
        UserMapper mapper = new UserMapper();
        try{
            Users userinfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userinfo;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public void manageUser(int userID, String userName, String organization, String password, String accessLvl)
    {    //Assume input validation is done on front-end

        int accessLvlInt = Integer.parseInt(accessLvl);

        if(userID == -1)
        {
            // new user --- Assume all input values are there
            String maxIndexSql = "SELECT IFNULL(MAX(USER_ID), 0) FROM APP_USER";
            userID = getJdbcTemplate().queryForObject(maxIndexSql, int.class);
            ++userID;

            String newUserSQL = "INSERT INTO APP_USER (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ORGANIZATION) " +
                    "VALUES (?, ?, ?, ?)";
            String encPass = EncryptedPasswordUtils.encryptPassword(password);
            getJdbcTemplate().update(newUserSQL, userID, userName, encPass, organization);

            String userRoleSQL = "INSERT INTO USER_ROLE(ID, USER_ID, ROLE_ID)" +
                    " VALUES (?, ?, ?)";
            getJdbcTemplate().update(userRoleSQL, userID, userID, accessLvlInt);

        }
        else if(password.equals("none"))
        {
            // not updating password
            String theSQL = "UPDATE APP_USER SET USER_NAME = ?, ORGANIZATION = ? WHERE USER_ID = ?";
            getJdbcTemplate().update(theSQL, userName, organization, userID);

            String aSql = "UPDATE USER_ROLE SET ROLE_ID = ? WHERE USER_ID = ?";
            getJdbcTemplate().update(aSql, accessLvlInt, userID);

        }
        else
        {
            String theSQL = "UPDATE APP_USER SET USER_NAME = ?, ORGANIZATION = ?, ENCRYPTED_PASSWORD = ? WHERE USER_ID = ?";
            String encPass = EncryptedPasswordUtils.encryptPassword(password);
            getJdbcTemplate().update(theSQL, userName, organization, encPass, userID);

            String aSql = "UPDATE USER_ROLE SET ROLE_ID = ? WHERE USER_ID = ?";
            getJdbcTemplate().update(aSql, accessLvlInt, userID);
        }
    }

    // Returns a user given a userId
    public Users findUser(Integer userId)
    {
        String sql = UserMapper.BASE_SQL + "where u.USER_ID = ?";
        Object[] params = new Object[] {userId};
        UserMapper mapper = new UserMapper();
        try{
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);

        }
        catch (EmptyResultDataAccessException e){return null;}
    }

    public boolean deleteUser(int userId)
    {
        String delete = "DELETE FROM APP_USER WHERE USER_ID = ?";
        getJdbcTemplate().update(delete, userId);
        return true;
    }


    //This method is used to find all users and their information
    public List<Users> getAllUsers()
    {
        return getJdbcTemplate().query(UserMapper.BASE_SQL, new UserMapper());

    }





    public int getUserRole(String userName){
        String sql = "SELECT ROLE_ID FROM USER_ROLE " +
                "    inner join APP_USER" +
                "    ON USER_ROLE.USER_ID = APP_USER.USER_ID" +
                "    WHERE USER_NAME = ?";
        Object[] myArg = new Object[] {userName};
        return getJdbcTemplate().queryForObject(sql, myArg, int.class);
    }
}
