package Mappers;

import Models.LogicalServer;
import org.springframework.jdbc.core.RowMapper;
import sun.rmi.runtime.Log;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogicalServerMapper implements RowMapper<LogicalServer>{

    public static final String BASE_SQL //
        = "SELECT * FROM LOGICAL_SERVER";

    @Override
    public LogicalServer mapRow(ResultSet rs, int rowNum) throws SQLException{

        int collID = rs.getInt("COL_ID");
        String backUp = rs.getString("BACKUP_SERVER");
        boolean monitored = rs.getBoolean("MONITORED");
        String respGroup = rs.getString("RESP_GROUP");
        return new LogicalServer(collID, backUp, monitored, respGroup);

    }
}
