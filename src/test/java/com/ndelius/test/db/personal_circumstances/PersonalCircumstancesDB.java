package com.ndelius.test.db.personal_circumstances;

import static config.PersonalCircumstanceSubtype.MATERNITY;
import static config.PersonalCircumstanceType.PREGNANCY_MATERNITY;
import static net.serenitybdd.core.Serenity.*;

import com.ndelius.test.db.DatabaseConnection;
import config.PersonalCircumstanceSubtype;
import config.PersonalCircumstanceType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class PersonalCircumstancesDB extends PageObject {

    DatabaseConnection databaseConnection;

    PersonalCircumstanceSubtype personalCircumstanceSubtype;

    PersonalCircumstanceType personalCircumstanceType;

    protected static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void getOffenderWithPersonalCircumstance(Connection connection) throws Exception {
        ResultSet rs = searchForOffenderWithPersonalCircumstance(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("statusVerified").to(rs.getString("EVIDENCED"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOffenderWithPersonalCircumstanceDocumentation(Connection connection)
            throws Exception {
        ResultSet rs = searchForOffenderWithPersonalCircumstanceDocumentation(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOffenderWithoutPersonalCircumstanceDocumentation(Connection connection)
            throws Exception {
        ResultSet rs = searchForOffenderWithoutPersonalCircumstanceDocumentation(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOffenderWithGreaterThanXNumberOfPersonalCircumstances(Connection connection, int number)
            throws Exception {
        ResultSet rs = searchForOffenderWithGreaterThanXNumberOfPersonalCircumstances(connection, number);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getOffenderWithXNumberOfPersonalCircumstances(Connection connection, int number)
            throws Exception {
        ResultSet rs = searchForOffenderWithXNumberOfPersonalCircumstances(connection, number);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getPersonalCircumstanceDetailScreenData(Connection connection) throws Exception {
        ResultSet rs = searchForPersonalCircumstanceDetailScreenData(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("circumstanceType").to(rs.getString("CODE_DESCRIPTION"));
            setSessionVariable("circumstanceSubtype").to(rs.getString("SUB_TYPE"));
            String startDate = sdf.format(rs.getDate("START_DATE"));
            setSessionVariable("circumstanceStartDate").to(startDate);
            setSessionVariable("statusVerified").to(rs.getString("EVIDENCED"));
            setSessionVariable("notes").to(rs.getString("NOTES"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getPersonalCircumstanceMarriageCivilPartnership(Connection connection)
            throws Exception {
        ResultSet rs = searchForPersonalCircumstanceMarriageCivilPartnership(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            setSessionVariable("surname").to(rs.getString("SURNAME"));
            setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getPersonalCircumstanceMaternity(Connection connection) throws Exception {
        ResultSet rs = searchForPersonalCircumstanceMaternity(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            setSessionVariable("surname").to(rs.getString("SURNAME"));
            setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getPersonalCircumstanceNotMarriageOrPregnancy(Connection connection)
            throws Exception {
        ResultSet rs = searchForPersonalCircumstanceNotMarriageOrPregnancy(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            setSessionVariable("surname").to(rs.getString("SURNAME"));
            setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    public void getPersonalCircumstancePregnancyDetail(Connection connection) throws Exception {
        ResultSet rs = searchForPersonalCircumstancePregnancy(connection);
        while (rs.next()) {
            setSessionVariable("crn").to(rs.getString("CRN"));
            setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            setSessionVariable("surname").to(rs.getString("SURNAME"));
            setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForOffenderWithPersonalCircumstance(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOffenderWithPersonalCircumstance =
                "SELECT o.CRN, p.EVIDENCED FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "JOIN  OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "WHERE p.EVIDENCED IS NOT NULL\n"
                        + "AND ROWNUM <= 10\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";

        return stmt.executeQuery(searchForOffenderWithPersonalCircumstance);
    }

    private ResultSet searchForOffenderWithPersonalCircumstanceDocumentation(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceDocumentation =
                "SELECT DISTINCT o.CRN FROM DOCUMENT d\n"
                        + "INNER JOIN OFFENDER o\n"
                        + "ON d.offender_id = o.offender_id\n"
                        + "where TABLE_NAME = 'PERSONAL_CIRCUMSTANCE'\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";

        return stmt.executeQuery(searchForPersonalCircumstanceDocumentation);
    }

//todo ability to search for all users with PC docs rather than return the same user each time  - Dom Barnett 06/07/18
    private ResultSet searchForOffenderWithoutPersonalCircumstanceDocumentation(
            Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOffenderWithoutPersonalCircumstanceDocumentation =
                "SELECT DISTINCT o.CRN, p.CIRCUMSTANCE_TYPE_ID, d.TABLE_NAME FROM PERSONAL_CIRCUMSTANCE P\n"
                        + "    JOIN OFFENDER o ON O.OFFENDER_ID = P.OFFENDER_ID\n"
                        + "    FULL JOIN DOCUMENT d ON d.OFFENDER_ID = P.OFFENDER_ID\n"
                        + "    WHERE o.crn > '1'\n"
                        + "    AND ROWNUM = 1";

        return stmt.executeQuery(searchForOffenderWithoutPersonalCircumstanceDocumentation);
    }

    private ResultSet searchForOffenderWithGreaterThanXNumberOfPersonalCircumstances(Connection connection,
            int number)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOffenderWithNumberOfPersonalCircumstance =
                "SELECT o.CRN, COUNT(*) FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "GROUP BY o.CRN\n"
                        + "HAVING COUNT(*) > " + number;

        return stmt.executeQuery(searchForOffenderWithNumberOfPersonalCircumstance);
    }

    private ResultSet searchForOffenderWithXNumberOfPersonalCircumstances(Connection connection,
            int number)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForOffenderWithNumberOfPersonalCircumstance =
                "SELECT o.CRN, COUNT(*) FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "GROUP BY o.CRN\n"
                        + "HAVING COUNT(*) = " + number;

        return stmt.executeQuery(searchForOffenderWithNumberOfPersonalCircumstance);
    }

    private ResultSet searchForPersonalCircumstanceDetailScreenData(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceDetailScreenData =
                "SELECT o.CRN, t.CODE_DESCRIPTION, s.CODE_DESCRIPTION AS SUB_TYPE, p.START_DATE, p.END_DATE, p.EVIDENCED, p.NOTES from PERSONAL_CIRCUMSTANCE p\n"
                        + "JOIN OFFENDER o on p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "JOIN R_CIRCUMSTANCE_TYPE t ON p.CIRCUMSTANCE_TYPE_ID = t.CIRCUMSTANCE_TYPE_ID\n"
                        + "JOIN R_CIRCUMSTANCE_SUB_TYPE s ON p.CIRCUMSTANCE_SUB_TYPE_ID = s.CIRCUMSTANCE_SUB_TYPE_ID\n"
                        + "WHERE EXISTS (SELECT o.CRN, COUNT(p.PERSONAL_CIRCUMSTANCE_ID) FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "    JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "    GROUP BY o.CRN\n"
                        + "    HAVING COUNT(p.PERSONAL_CIRCUMSTANCE_ID)= 1)\n"
                        + "ORDER BY DBMS_RANDOM.VALUE";
        return stmt.executeQuery(searchForPersonalCircumstanceDetailScreenData);
    }

    public void getPersonalCircumstanceNotPregnancyOrMaternityDetail(Connection connection) throws Exception {
        ResultSet rs = searchForPersonalCircumstanceNotPregnancyOrMaternity(connection);
        while (rs.next()) {
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            Serenity.setSessionVariable("surname").to(rs.getString("SURNAME"));
            Serenity.setSessionVariable("provider").to(rs.getString("DESCRIPTION"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForPersonalCircumstanceMarriageCivilPartnership(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceMarriageCivilPartnership =
                "SELECT o.CRN, p.OFFENDER_ID, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION, COUNT(p.PERSONAL_CIRCUMSTANCE_ID) FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n"
                        + "JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "JOIN COURT c ON a.COURT_ID = c.COURT_ID\n"
                        + "JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n"
                        + "WHERE o.CRN IN (SELECT o.CRN\n"
                        + "     FROM PERSONAL_CIRCUMSTANCE p\n"
                        + "     JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n"
                        + "     JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "     JOIN COURT c ON a.COURT_ID = c.COURT_ID\n"
                        + "     JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n"
                        + "     WHERE p.CIRCUMSTANCE_SUB_TYPE_ID = '51'\n"
                        + "     AND q.DESCRIPTION = 'NPS Midlands'\n"
                        + "     AND p.END_DATE IS NULL\n"
                        + "     AND o.Current_restriction = '0'\n"
                        + "     AND o.Current_Exclusion = '0')\n"
                        + "GROUP BY o.CRN, p.OFFENDER_ID, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION\n"
                        + "HAVING COUNT(p.PERSONAL_CIRCUMSTANCE_ID)= 1";

        return stmt.executeQuery(searchForPersonalCircumstanceMarriageCivilPartnership);
    }

    private ResultSet searchForPersonalCircumstanceMaternity(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceMaternity =
                "SELECT DISTINCT p.OFFENDER_ID, o.CRN, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION\n" +
                        "  FROM PERSONAL_CIRCUMSTANCE p\n" +
                        "  JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n" +
                        "  JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN COURT c ON a.COURT_ID = c.COURT_ID\n" +
                        "  JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE p.CIRCUMSTANCE_TYPE_ID = '" + PREGNANCY_MATERNITY.getPersonalCircumstanceType() + "'\n" +
                        "AND p.CIRCUMSTANCE_SUB_TYPE_ID = '" + MATERNITY.getPersonalCircumstanceSubtype() + "'\n" +
                        "AND q.DESCRIPTION = 'NPS Midlands'\n" +
                        "AND p.END_DATE IS NULL\n" +
                        "AND o.Current_restriction = '0'\n" +
                        "AND o.Current_Exclusion = '0'\n" +
                        "AND ROWNUM = 1";

        return stmt.executeQuery(searchForPersonalCircumstanceMaternity);
    }

    private ResultSet searchForPersonalCircumstancePregnancy(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstancePregnancy =
                "SELECT DISTINCT p.OFFENDER_ID, o.CRN, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION\n" +
                        "  FROM PERSONAL_CIRCUMSTANCE p\n" +
                        "  JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n" +
                        "  JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN COURT c ON a.COURT_ID = c.COURT_ID\n" +
                        "  JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE p.CIRCUMSTANCE_TYPE_ID = '2500000000'\n" +
                        "AND p.CIRCUMSTANCE_SUB_TYPE_ID = '2500001710'\n" +
                        "AND q.DESCRIPTION = 'NPS Midlands'\n" +
                        "AND p.END_DATE IS NULL\n" +
                        "AND o.Current_restriction = '0'\n" +
                        "AND o.Current_Exclusion = '0'\n" +
                        "AND ROWNUM = 1";

        return stmt.executeQuery(searchForPersonalCircumstancePregnancy);
    }

    private ResultSet searchForPersonalCircumstanceNotPregnancyOrMaternity(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceNotPregnancyOrMaternity =
                "SELECT DISTINCT p.OFFENDER_ID, o.CRN, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION\n" +
                        "  FROM PERSONAL_CIRCUMSTANCE p\n" +
                        "  JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n" +
                        "  JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN COURT c ON a.COURT_ID = c.COURT_ID\n" +
                        "  JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE p.CIRCUMSTANCE_TYPE_ID != '2500000000'\n" +
                        "AND q.DESCRIPTION = 'NPS Midlands'\n" +
                        "AND p.END_DATE IS NULL\n" +
                        "AND o.Current_restriction = '0'\n" +
                        "AND o.Current_Exclusion = '0'\n" +
                        "AND ROWNUM = 1";

        return stmt.executeQuery(searchForPersonalCircumstanceNotPregnancyOrMaternity);
    }

    private ResultSet searchForPersonalCircumstanceNotMarriageOrPregnancy(Connection connection)
            throws Exception {
        Statement stmt = connection.createStatement();
        String searchForPersonalCircumstanceNotMarriageOrPregnancy =
                "SELECT DISTINCT p.OFFENDER_ID, o.CRN, o.FIRST_NAME, o.SURNAME, q.DESCRIPTION, p.CIRCUMSTANCE_TYPE_ID\n" +
                        "  FROM PERSONAL_CIRCUMSTANCE p\n" +
                        "  JOIN COURT_APPEARANCE a ON a.OFFENDER_ID = p.OFFENDER_ID\n" +
                        "  JOIN OFFENDER o ON p.OFFENDER_ID = o.OFFENDER_ID\n" +
                        "  JOIN COURT c ON a.COURT_ID = c.COURT_ID\n" +
                        "  JOIN PROBATION_AREA q ON q.PROBATION_AREA_ID = c.PROBATION_AREA_ID\n" +
                        "WHERE p.CIRCUMSTANCE_TYPE_ID != '2500000000'\n" +
                        "AND p.CIRCUMSTANCE_SUB_TYPE_ID != '2500001710'\n" +
                        "AND p.CIRCUMSTANCE_SUB_TYPE_ID != '51'\n" +
                        "AND q.DESCRIPTION = 'NPS Midlands'\n" +
                        "AND p.END_DATE IS NULL\n" +
                        "AND o.Current_restriction = '0'\n" +
                        "AND o.Current_Exclusion = '0'\n" +
                        "AND ROWNUM = 1";

        return stmt.executeQuery(searchForPersonalCircumstanceNotMarriageOrPregnancy);
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

}
