package Mappers;

import Models.Collection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionMapper implements RowMapper<Collection>{

    public static final String BASE_SQL //
        ="SELECT ID, c.TYPE, c.FULL_NAME, c.ACRONYM, c.STATUS, c.BACKUP_SERVER, c.SERVICE_TYPE, o.ORGANIZATION, d.DES_ORG FROM COLLECTIONS c" +
            " INNER JOIN COLLEC_ORGANIZATION o ON c.ID = o.COL_ID" +
            " INNER JOIN COLLEC_DESIGN_ORGANIZATION d ON o.COL_ID = d.COL_ID";

    @Override
    public Collection mapRow(ResultSet rs, int rowNum) throws SQLException{

        int id = rs.getInt("ID");
        String type = rs.getString("TYPE");
        String fullName = rs.getString("FULL_NAME");
        String acronym = rs.getString("ACRONYM");
        String status = rs.getString("STATUS");
        String backUp = rs.getString("BACKUP_SERVER");
        String serviceType = rs.getString("SERVICE_TYPE");
        String org = rs.getString("o.ORGANIZATION");
        String des_org = rs.getString("d.DES_ORG");

        Collection aCollection = new Collection(id, type, fullName, acronym, status, backUp, serviceType, org, des_org);
        return aCollection;
    }
}
