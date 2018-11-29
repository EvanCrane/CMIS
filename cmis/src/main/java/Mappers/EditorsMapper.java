package Mappers;

import Models.Editors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EditorsMapper implements RowMapper<Editors> {

    public static final String BASE_SQL =
            "SELECT COL_ID, USER_ID, ACCESS_LVL, PHONE_NUMBER, LOCATION " +
                    "FROM COLLECTION_EDITORS ";

    @Override
    public Editors mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int id = rs.getInt("COL_ID");
        int uID = rs.getInt("USER_ID");
        String accessLvl = rs.getString("ACCESS_LVL");
        String phoneNum = rs.getString("PHONE_NUMBER");
        String location = rs.getString("LOCATION");

        return new Editors(id, uID, accessLvl, phoneNum, location);
    }
}
