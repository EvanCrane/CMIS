package Mappers;

import Models.SubObjects;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubObjectsMapper implements RowMapper<SubObjects> {

    public static final String BASE_SQL =
            "SELECT COL_ID, TYPE, NAME, VERSION, CLASS_INFO FROM SUB_OBJECTS ";

    @Override
    public SubObjects mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int colID = rs.getInt("COL_ID");
        String type = rs.getString("TYPE");
        String name = rs.getString("NAME");
        String version = rs.getString("VERSION");
        boolean classified = rs.getBoolean("CLASS_INFO");

        return new SubObjects(colID, type, name, version, classified);

    }


}
