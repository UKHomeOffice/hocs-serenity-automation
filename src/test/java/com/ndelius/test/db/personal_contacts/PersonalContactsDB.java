package com.ndelius.test.db.personal_contacts;

import com.ndelius.test.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class PersonalContactsDB extends PageObject {

    DatabaseConnection databaseConnection;

    public void getPersonalContactsForOffender(Connection connection)
            throws Exception {
        ResultSet rs = searchForPersonalContacts(connection);
        while (rs.next()) {
            Serenity.setSessionVariable("crn").to(rs.getString("CRN"));
            Serenity.setSessionVariable("relationship").to(rs.getString("RELATIONSHIP"));
            Serenity.setSessionVariable("firstName").to(rs.getString("FIRST_NAME"));
            Serenity.setSessionVariable("surname").to(rs.getString("SURNAME"));
        }
        databaseConnection.closeDbConnection(connection);
    }

    private ResultSet searchForPersonalContacts(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String searchForAddress =
                "SELECT o.CRN, p.RELATIONSHIP, p.FIRST_NAME, p.SURNAME\n"
                        + "FROM PERSONAL_CONTACT p\n"
                        + "JOIN OFFENDER o\n"
                        + "ON p.OFFENDER_ID = o.OFFENDER_ID\n"
                        + "ORDER BY dbms_random.value";

        return stmt.executeQuery(searchForAddress);
    }

}
