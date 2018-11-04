package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Users;
import org.springframework.jdbc.core.RowMapper;

// The UserMapper class is used for mapping the columns in the APP_USER table with fields
// in the Users class (Based on the query statement)

public class UserMapper implements RowMapper<Users>{

    public static final String BASE_SQL //
            = "Select u.USER_ID, u.USER_NAME, u.ENCRYPTED_PASSWORD, u.ORGANIZATION From APP_USER u ";

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        int userId = rs.getInt("USER_ID");
        String userName = rs.getString("USER_NAME");
        String encryptedPassword = rs.getString("ENCRYPTED_PASSWORD");
        String organization = rs.getString("ORGANIZATION");

        return new Users(userId, userName, encryptedPassword, organization);
    }
}
