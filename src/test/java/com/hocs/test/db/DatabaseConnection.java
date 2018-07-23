//package com.hocs.test.db;
//
//import config.Environments;
//import config.Users;
//import net.thucydides.core.pages.PageObject;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import static junit.framework.TestCase.fail;
//
//public class DatabaseConnection extends PageObject {
//
//    private Connection connectToDatabase(String sqlConnectionHost, String userName, String password)
//            throws SQLException, ClassNotFoundException {
//        Class.forName("oracle.jdbc.OracleDriver");
//
//        return DriverManager.getConnection(sqlConnectionHost, userName, password);
//    }
//
//    private Connection getDatabase() throws SQLException, ClassNotFoundException {
//        String sqlConnectionHost = Environments.SR2.getDatabaseURL();
//        String username = Users.DATABASE_ADMIN.getUsername();
//        String password = Users.DATABASE_ADMIN.getPassword();
//
//        return connectToDatabase(sqlConnectionHost, username, password);
//    }
//
//    public void closeDbConnection(Connection connection) {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//                System.out.println("Connection closed!");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public Connection establishDatabaseConnection() throws Exception {
//        Connection connection = null;
//        try {
//            connection = getDatabase();
//            if (connection != null) {
//                DatabaseMetaData dbMetadata = connection.getMetaData();
//                System.out.println("Connected to Database!");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            fail("Database Connection Failed");
//        }
//
//        return connection;
//    }
//
//}
