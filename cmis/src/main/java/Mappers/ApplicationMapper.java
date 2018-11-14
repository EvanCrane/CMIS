package Mappers;

import Models.Application;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper implements RowMapper<Application> {

    public static final String BASE_SQL = "SELECT * FROM APPLICATION";

    @Override
    public Application mapRow(ResultSet rs, int rowNum) throws SQLException{

        int collID = rs.getInt("COL_ID");
        String subType = rs.getString("SUBTYPE");
        String userInteface = rs.getString("USER_INTERFACE");
        String size = rs.getString("SIZE");
        String totalUsers = rs.getString("TOTAL_USERS");
        String concUsers = rs.getString("CONC_USERS");
        String priority = rs.getString("PRIORITY");
        return new Application(collID, subType, userInteface, size, totalUsers, concUsers, priority);
    }
}
