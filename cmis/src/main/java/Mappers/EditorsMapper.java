package Mappers;

import Models.Editors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EditorsMapper implements RowMapper<Editors> {

    public static final String BASE_SQL =
            "SELECT e.COL_ID, e.USER_ID, e.ACCESS_LVL, e.PHONE_NUMBER, e.LOCATION, " +
                    "u.USER_NAME " +
                    "FROM COLLECTION_EDITORS e INNER JOIN APP_USER u ON " +
                    "e.USER_ID = u.USER_ID ";

    @Override
    public Editors mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int id = rs.getInt("COL_ID");
        int uID = rs.getInt("USER_ID");
        String userName = rs.getString("u.USER_NAME");
        String accessLvl = rs.getString("ACCESS_LVL");
        String phoneNum = rs.getString("PHONE_NUMBER");
        String location = rs.getString("LOCATION");

        return new Editors(id, uID, userName, accessLvl, phoneNum, location);
    }
}
