package Daos;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport{

    @Autowired
    public RoleDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<String> getRoleNames(int userId){
        String sql = "Select r.ROLE_NAME "//
                +"FROM USER_ROLE ur, APP_ROLE r " //
                +"WHERE ur.ROLE_ID = r.ROLE_ID AND ur.USER_ID = ?";

        Object[] params = new Object[]{userId};

        return this.getJdbcTemplate().queryForList(sql, params, String.class);
    }

    public int checkRole(int userId){
        String sqlCheckRole = String.format("SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID = %s;", userId);

        try{
            return getJdbcTemplate().queryForObject(sqlCheckRole, Integer.class);
        }
        catch (Exception e){
            return -1;
        }
    }


}
