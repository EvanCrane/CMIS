package Daos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;

import Mappers.UserMapper;
import Models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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
        String sql = UserMapper.BASE_SQL + "where u.USER_NAME = ?";
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

    public int getUserRole(String userName){
        String sql = "SELECT ROLE_ID FROM USER_ROLE " +
                "    inner join APP_USER" +
                "    ON USER_ROLE.USER_ID = APP_USER.USER_ID" +
                "    WHERE USER_NAME = ?";
        Object[] myArg = new Object[] {userName};
        return getJdbcTemplate().queryForObject(sql, myArg, int.class);
    }
}
