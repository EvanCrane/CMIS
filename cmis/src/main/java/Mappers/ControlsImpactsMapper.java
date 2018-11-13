package Mappers;

import Models.ControlsImpacts;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlsImpactsMapper implements RowMapper<ControlsImpacts>{

    public static final String BASE_SQL //
            = "SELECT COL_ID, FUNCTION_CLASS, DATA_CLASS, MISS_ESS_RAT FROM CONTROLS_IMPACTS";

    @Override
    public ControlsImpacts mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("COL_ID");
        String funcClass = rs.getString("FUNCTION_CLASS");
        String dataClass = rs.getString("DATA_CLASS");
        String missEssRating = rs.getString("MISS_ESS_RAT");
        return new ControlsImpacts(id, funcClass, dataClass, missEssRating);
    }
}
