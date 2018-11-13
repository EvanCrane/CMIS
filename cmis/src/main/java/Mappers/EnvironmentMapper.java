package Mappers;

import Models.Environment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnvironmentMapper implements RowMapper<Environment> {

    public static final String BASE_SQL //
       = "SELECT COL_ID, SUBTYPE, BACKUP_SEVER, MONITORED, RESP_GROUP FROM ENVIRONMENT";

    @Override
    public Environment mapRow(ResultSet rs, int rowNum) throws SQLException{

        int id = rs.getInt("COL_ID");
        String subType = rs.getString("SUBTYPE");
        String backUp = rs.getString("BACKUP_SEVER");
        boolean monitored = rs.getBoolean("MONITORED");
        String respGroup = rs.getString("RESP_GROUP");

        return new Environment(id, subType, backUp, monitored, respGroup);

    }

}
