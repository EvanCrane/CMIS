package Mappers;

import Models.Collection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionMapper implements RowMapper<Collection>{

    public static final String BASE_SQL //
        ="SELECT ID, TYPE, FULL_NAME, ACRONYM, STATUS, BACKUP_SERVER, SERVICE_TYPE FROM COLLECTIONS";

    @Override
    public Collection mapRow(ResultSet rs, int rowNum) throws SQLException{

        int id = rs.getInt("ID");
        String type = rs.getString("TYPE");
        String fullName = rs.getString("FULL_NAME");
        String acronym = rs.getString("ACRONYM");
        String status = rs.getString("STATUS");
        String backUp = rs.getString("BACKUP_SERVER");
        String serviceType = rs.getString("SERVICE_TYPE");

        Collection aCollection = new Collection(id, type, fullName, acronym, status, backUp, serviceType);
        return aCollection;
    }
}
