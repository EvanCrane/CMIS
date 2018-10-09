package Mappers;

import Models.Collection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionMapper implements RowMapper<Collection>{

    public static final String BASE_SQL //
        ="SELECT FULL_NAME, ACRONYM, TYPE, STATUS FROM COLLECTIONS";

    @Override
    public Collection mapRow(ResultSet rs, int rowNum) throws SQLException{

        String fullName = rs.getString("FULL_NAME");
        String acronym = rs.getString("ACRONYM");
        String type = rs.getString("TYPE");
        String status = rs.getString("STATUS");
        Collection aCollection = new Collection(fullName, acronym, type, status);
        return aCollection;
    }
}
