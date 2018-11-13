package Mappers;


import Models.General;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralMapper implements RowMapper<General>{

    public static final String BASE_SQL //
            ="SELECT COL_ID, USER_POPULATION, PRIM_CUSTOM, SUBCON_MAINT, DESCRIPTION FROM GENERAL";

    @Override
    public General mapRow(ResultSet rs, int rowNum) throws SQLException{

        int collID = rs.getInt("COL_ID");
        String aUserPop = rs.getString("USER_POPULATION");
        String aPrimCust = rs.getString("PRIM_CUSTOM");
        String aSubconMaint = rs.getString("SUBCON_MAINT");
        String aDescription = rs.getString("DESCRIPTION");
        return new General(collID, aUserPop, aPrimCust, aSubconMaint, aDescription);
    }
}
