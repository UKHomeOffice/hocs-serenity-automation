package com.ndelius.test.db.spg_administration;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.ndelius.test.db.DatabaseConnection;
import config.ExceptionStatuses;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import net.serenitybdd.core.pages.PageObject;

public class SpgMessageExceptionsDB extends PageObject {

    DatabaseConnection databaseConnection;

    protected static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void getCrnWithNumberOfExceptions(Connection connection, int numberOfExceptions)
            throws Exception {
        ResultSet rs = searchForCrnWithNumberOfExceptions(connection, numberOfExceptions);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getCrnWithNumberOfExceptionStatus(Connection connection,
            int numberOfExceptions, ExceptionStatuses statusName) throws Exception {
        ResultSet rs = searchForCrnWithNumberOfExceptionStatus(connection, numberOfExceptions,
                statusName);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            String startDate = sdf.format(rs.getDate("CREATED_DATETIME"));
            setSessionVariable("exceptionStartDate").to(startDate);
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForCrnWithNumberOfExceptions(Connection connection,
            int numberOfExceptions)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOffenderWithNumberOfPersonalCircumstance =
                "SELECT o.CRN, COUNT(*) FROM SPG_EXCEPTION s\n"
                        + "JOIN OFFENDER o ON o.OFFENDER_ID = s.OFFENDER_ID\n"
                        + "WHERE ROWNUM < 100\n"
                        + "GROUP BY o.CRN\n"
                        + "HAVING COUNT(*) = " + numberOfExceptions + "\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";

        return stmt.executeQuery(searchForOffenderWithNumberOfPersonalCircumstance);
    }

    private ResultSet searchForCrnWithNumberOfExceptionStatus(Connection connection,
            int numberOfExceptions, ExceptionStatuses statusName)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForCrnWithNumberOfExceptionStatus =
                "SELECT o.CRN, s.CREATED_DATETIME FROM SPG_EXCEPTION s\n"
                        + "JOIN OFFENDER o ON o.OFFENDER_ID = s.OFFENDER_ID\n"
                        + "WHERE EXISTS\n"
                        + "    (SELECT o.CRN, COUNT(*) FROM SPG_EXCEPTION s\n"
                        + "    JOIN OFFENDER o ON o.OFFENDER_ID = s.OFFENDER_ID\n"
                        + "    GROUP BY o.CRN\n"
                        + "    HAVING COUNT(*) = " + numberOfExceptions + ")\n"
                        + "AND s.EXCEPTION_TYPE_ID = " + statusName.getExceptionStatusName() + "\n"
                        + "and ROWNUM < 100\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";

        return stmt.executeQuery(searchForCrnWithNumberOfExceptionStatus);
    }

}
