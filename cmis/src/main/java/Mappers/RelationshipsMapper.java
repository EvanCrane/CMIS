package Mappers;

import Models.Relationships;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationshipsMapper implements RowMapper<Relationships> {

    public static final String BASE_SQL =
            "SELECT COL_ID, TYPE, VALUE FROM RELATIONSHIPS ";

    @Override
    public Relationships mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int id = rs.getInt("COL_ID");
        String type = rs.getString("TYPE");
        String value = rs.getString("VALUE");

        return new Relationships(id, type, value);
    }

}
