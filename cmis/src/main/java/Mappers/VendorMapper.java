package Mappers;

import Models.VendorProducts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorMapper implements RowMapper<VendorProducts>{

    public static final String BASE_SQL
            = "SELECT COL_ID, PRODUCT_NAME, VERSION, DESCRIPTION, CLASS_INFO " +
            "FROM VENDOR_PRODUCTS ";
    @Override
    public VendorProducts mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        int id = rs.getInt("COL_ID");
        String prodName = rs.getString("PRODUCT_NAME");
        String version = rs.getString("VERSION");
        String description = rs.getString("CLASS_INFO");
        boolean classified = rs.getBoolean("CLASS_INFO");

        return new VendorProducts(id, prodName, version, description, classified);
    }
}
