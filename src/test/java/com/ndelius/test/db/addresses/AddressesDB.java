package com.ndelius.test.db.addresses;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.ndelius.test.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class AddressesDB extends PageObject {

    DatabaseConnection databaseConnection;

    public void assertColumnIsNull() {
        assertThat(Serenity.sessionVariableCalled("dbColumn"), nullValue());
    }

    public void getAddressDetail(Connection connection) throws Exception {
        ResultSet rs = searchForAddress(connection);
        while (rs.next()) {
            Serenity.setSessionVariable("offenderId").to(rs.getString("OFFENDER_ID"));
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("buildingName").to(rs.getString("BUILDING_NAME"));
            Serenity.setSessionVariable("addressNumber").to(rs.getString("ADDRESS_NUMBER"));
            Serenity.setSessionVariable("streetName").to(rs.getString("ADDRESS_NUMBER"));
            Serenity.setSessionVariable("district").to(rs.getString("DISTRICT"));
            Serenity.setSessionVariable("townCity").to(rs.getString("TOWN_CITY"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getAddressDetailForKnownOffender(Connection connection, String dbColumn) throws Exception {
        ResultSet rs = searchAddressForKnownOffender(connection);
        while (rs.next()) {
            Serenity.setSessionVariable("offenderId").to(rs.getString("OFFENDER_ID"));
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("buildingName").to(rs.getString("BUILDING_NAME"));
            Serenity.setSessionVariable("addressNumber").to(rs.getString("ADDRESS_NUMBER"));
            Serenity.setSessionVariable("streetName").to(rs.getString("ADDRESS_NUMBER"));
            Serenity.setSessionVariable("district").to(rs.getString("DISTRICT"));
            Serenity.setSessionVariable("townCity").to(rs.getString("TOWN_CITY"));
            Serenity.setSessionVariable("dbColumn").to(rs.getString(dbColumn));
        }
        databaseConnection.closeDbConnection(connection);
    }

    private  ResultSet searchForAddress(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String searchForAddress = "SELECT a.OFFENDER_ADDRESS_ID, o.OFFENDER_ID, o.CRN, a.BUILDING_NAME,  a.ADDRESS_NUMBER, a.STREET_NAME, a.DISTRICT, a.TOWN_CITY\n"
                + "FROM OFFENDER o\n"
                + "JOIN OFFENDER_ADDRESS a ON o.OFFENDER_ID = a.OFFENDER_ID\n"
                + "WHERE a.BUILDING_NAME IS NOT NULL\n"
                + "AND a.END_DATE IS NULL\n"
                + "ORDER BY dbms_random.value";

        return stmt.executeQuery(searchForAddress);
    }

    private ResultSet searchAddressForKnownOffender(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String searchAddressForKnownOffender = "SELECT o.OFFENDER_ID, o.CRN, a.BUILDING_NAME,  a.ADDRESS_NUMBER, a.STREET_NAME, a.DISTRICT, a.TOWN_CITY\n"
                + "FROM OFFENDER o\n"
                + "JOIN OFFENDER_ADDRESS a ON o.OFFENDER_ID = a.OFFENDER_ID\n"
                + "WHERE CRN = '" + Serenity.sessionVariableCalled("crn") + "'";

        return stmt.executeQuery(searchAddressForKnownOffender);
    }

}