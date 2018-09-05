//package com.hocs.test.db;
//
//import net.serenitybdd.core.Serenity;
//import net.serenitybdd.core.pages.PageObject;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//
//public class DatabaseHelper extends PageObject {
//
//    private final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//    private DatabaseConnection databaseConnection;
//
//    public void getCourtAppearanceDetail(Connection connection) throws Exception {
//        ResultSet rs = searchForCourtAppearance(connection);
//        while (rs.next()) {
//
//        }
//        databaseConnection.closeDbConnection(connection);
//    }
//
//     public void setDatabaseConnection(DatabaseConnection databaseConnection) {
//        this.databaseConnection = databaseConnection;
//    }
//
//}
