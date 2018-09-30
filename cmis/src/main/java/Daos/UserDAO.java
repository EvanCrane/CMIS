package Daos;

import javax.sql.DataSource;

import Mappers.UserMapper;
import Models.User;

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
    public User findUserAccount(String userName){
        //Select ... from APP_USER u Where u.USER_NAME = ?
        String sql = UserMapper.BASE_SQL + "where u.USER_NAME + ?";
        Object[] params = new Object[] {userName};
        UserMapper mapper = new UserMapper();
        try{
            User userinfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userinfo;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
