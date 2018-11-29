package Mappers;

import Models.Collection;
import Models.Contacts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsMapper  implements RowMapper<Contacts>{

    public static final String BASE_SQL
            = "SELECT COL_ID, USER_ID, NAME, CONTACT_TYPE, PHONE_NUMBER, " +
            "LOCATION FROM COLLECTION_CONTACTS ";

    @Override
    public Contacts mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int id = rs.getInt("COL_ID");
        int uID = rs.getInt("USER_ID");
        String name = rs.getString("NAME");
        String contType = rs.getString("CONTACT_TYPE");
        String phoneNum= rs.getString("PHONE_NUMBER");
        String location = rs.getString("LOCATION");

        return new Contacts(id, uID, name, contType, phoneNum, location);
    }
}
