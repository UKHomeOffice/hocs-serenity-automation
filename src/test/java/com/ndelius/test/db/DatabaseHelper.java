package com.ndelius.test.db;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DatabaseHelper extends PageObject {

    private final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private DatabaseConnection databaseConnection;

    public void getCourtAppearanceDetail(Connection connection) throws Exception {
        ResultSet rs = searchForCourtAppearance(connection);
        while (rs.next()) {
            String appearanceDate = sdf.format(rs.getDate("APPEARANCE_DATE"));
            Serenity.setSessionVariable("appearanceDate").to(appearanceDate);
            Serenity.setSessionVariable("court").to(rs.getString("COURT_NAME"));
            Serenity.setSessionVariable("offenderId").to(rs.getString("OFFENDER_ID"));
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getCourtReportDetail(Connection connection) throws Exception {
        ResultSet rs = searchForCourtReport(connection);
        while (rs.next()) {
            String requiredDate = sdf.format(rs.getDate("DATE_REQUIRED"));
            Serenity.setSessionVariable("requiredDate").to(requiredDate);
            Serenity.setSessionVariable("court").to(rs.getString("COURT_NAME"));
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForCourtAppearance(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String searchForCourtAppearance =
                "SELECT a.APPEARANCE_DATE, c.COURT_NAME, a.OFFENDER_ID, o.CRN, p.DESCRIPTION\n" +
                        "  FROM COURT_APPEARANCE a\n" +
                        "  JOIN COURT c ON a.COURT_ID = c.COURT_ID\n" +
                        "  JOIN OFFENDER o ON a.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN PROBATION_AREA p ON p.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE a.APPEARANCE_DATE > '01-JAN-18' \n" +
                        "AND a.COURT_ID = '1500003525' \n" +
                        "AND o.OFFENDER_ID = '2500156068'";

        return stmt.executeQuery(searchForCourtAppearance);
    }

    private ResultSet searchForCourtReport(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String searchForCourtReport =
                "SELECT r.DATE_REQUIRED, c.COURT_NAME, o.CRN , p.DESCRIPTION\n" +
                        "  FROM COURT_REPORT r\n" +
                        "  JOIN COURT c ON r.REQUIRED_BY_COURT_ID = c.COURT_ID\n" +
                        "  JOIN OFFENDER o ON r.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN PROBATION_AREA p ON p.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE r.DATE_REQUIRED > '01-JAN-18' \n" +
                        "AND r.REQUIRED_BY_COURT_ID = '961'";

        return stmt.executeQuery(searchForCourtReport);

    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

}
