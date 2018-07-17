package com.ndelius.test.db.spg_administration;

import static config.ErrorResolutionStatuses.*;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.ndelius.test.db.DatabaseConnection;
import com.ndelius.test.pages.Page;
import config.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import net.serenitybdd.core.pages.PageObject;

public class SpgMessageErrorsDB extends PageObject {

    DatabaseConnection databaseConnection;

    ErrorResolutionStatuses errorResolutionStatuses;

    Page page;

    protected static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void getInboundErrorWithAStatusOfResolved(Connection connection) throws Exception {
        ResultSet rs = searchForInboundErrorWithAStatusOfResolved(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getInboundErrorWithAStatusOfUnderInvestigation(Connection connection)
            throws Exception {
        ResultSet rs = searchForInboundErrorWithAStatusOfUnderInvestigation(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getInboundErrorWithAStatusOfUnresolved(Connection connection) throws Exception {
        ResultSet rs = searchForInboundErrorWithAStatusOfUnresolved(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOutboundErrorWithAStatusOfResolved(Connection connection) throws Exception {
        ResultSet rs = searchForOutboundErrorWithAStatusOfResolved(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOutboundErrorWithAStatusOfUnderInvestigation(Connection connection)
            throws Exception {
        ResultSet rs = searchForOutboundErrorWithAStatusOfUnderInvestigation(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOutboundErrorWithAStatusOfUnresolved(Connection connection) throws Exception {
        ResultSet rs = searchForOutboundErrorWithAStatusOfUnresolved(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("MESSAGE_CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("createdDatetime").to(startDate);
            setSessionVariable("resolutionStatusId").to(rs.getString("RESOLUTION_STATUS_ID"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getRegeneratedOutboundErrorMessage(Connection connection) throws Exception {
        ResultSet rs = searchForRegeneratedOutboundErrorMessage(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("controlReference").to(rs.getString("CONTROL_REFERENCE"));
            String startDate = sdf.format(rs.getDate("DATE_CREATED"));
            setSessionVariable("dateCreated").to(startDate);
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForInboundErrorWithAStatusOfResolved(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForInboundErrorWithAStatusOfResolved =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'I'\n"
                        + "AND e.RESOLUTION_STATUS_ID = '" + RESOLVED.getErrorResolutionStatusName() + "'\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusYears(-1) + "'";

        return stmt.executeQuery(searchForInboundErrorWithAStatusOfResolved);
    }

    private ResultSet searchForInboundErrorWithAStatusOfUnderInvestigation(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForInboundErrorWithAStatusOfUnderInvestigation =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'I'\n"
                        + "AND e.RESOLUTION_STATUS_ID = '" + UNDER_INVESTIGATION.getErrorResolutionStatusName() + "'\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusYears(-1) + "'";

        return stmt.executeQuery(searchForInboundErrorWithAStatusOfUnderInvestigation);
    }

    private ResultSet searchForInboundErrorWithAStatusOfUnresolved(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForInboundErrorWithAStatusOfUnresolved =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'I'\n"
                        + "AND e.RESOLUTION_STATUS_ID IS NULL\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusDays(-7) + "'";

        return stmt.executeQuery(searchForInboundErrorWithAStatusOfUnresolved);
    }

    private ResultSet searchForOutboundErrorWithAStatusOfResolved(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOutboundErrorWithAStatusOfResolved =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'O'\n"
                        + "AND e.RESOLUTION_STATUS_ID = '"  + RESOLVED.getErrorResolutionStatusName() + "'\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusYears(-1) + "'";

        return stmt.executeQuery(searchForOutboundErrorWithAStatusOfResolved);
    }

    private ResultSet searchForOutboundErrorWithAStatusOfUnderInvestigation(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOutboundErrorWithAStatusOfUnderInvestigation =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'O'\n"
                        + "AND e.RESOLUTION_STATUS_ID = '" + UNDER_INVESTIGATION.getErrorResolutionStatusName() + "'\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusYears(-1) + "'";

        return stmt.executeQuery(searchForOutboundErrorWithAStatusOfUnderInvestigation);
    }

    private ResultSet searchForOutboundErrorWithAStatusOfUnresolved(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOutboundErrorWithAStatusOfUnresolved =
                "SELECT e.MESSAGE_CRN, n.CONTROL_REFERENCE, e.CREATED_DATETIME, e.RESOLUTION_STATUS_ID FROM SPG_NOTIFICATION n\n"
                        + "JOIN SPG_ERROR e ON e.SPG_NOTIFICATION_ID = n.SPG_NOTIFICATION_ID\n"
                        + "WHERE n.MESSAGE_DIRECTION = 'O'\n"
                        + "AND e.RESOLUTION_STATUS_ID IS NULL\n"
                        + "AND e.CREATED_DATETIME > '" + page.databaseDateTodayPlusDays(-31) + "'";

        return stmt.executeQuery(searchForOutboundErrorWithAStatusOfUnresolved);
    }

    private ResultSet searchForRegeneratedOutboundErrorMessage(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForRegeneratedOutboundErrorMessage =
                "select s.CONTROL_REFERENCE, o.CRN, s.DATE_CREATED from SPG_NOTIFICATION s\n"
                        + "JOIN OFFENDER o ON s.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "WHERE REGENERATED_NOTIFICATION_ID IS NOT NULL\n"
                        + "AND MESSAGE_DIRECTION = 'O'\n"
                        + "AND ROWNUM < 100\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";

        return stmt.executeQuery(searchForRegeneratedOutboundErrorMessage);
    }

}
