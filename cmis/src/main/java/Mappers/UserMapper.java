package Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import Models.User;
import org.springframework.jdbc.core.RowMapper;

// The UserMapper class is used for mapping the columns in the APP_USER table with fields
// in the User class (Based on the query statement)

public class UserMapper implements RowMapper<User>{

    public static final String BASE_SQL //
            = "Select u.USER_ID, u.USER_NAME, u.ENCRYPTED_PASSWORD From APP_USER u";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int userId = rs.getInt("USER_ID");
        String userName = rs.getString("USER_NAME");
        String encryptedPassword = rs.getString("ENCRYPTED_PASSWORD");

        return new User(userId, userName, encryptedPassword);
    }
}
