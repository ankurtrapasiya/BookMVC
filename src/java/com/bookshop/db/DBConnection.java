package com.bookshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// database connection class.
public class DBConnection {

    private String dbUrl = "jdbc:mysql://localhost:3306/BookShop";
    private String dbUser = "root";
    private String dbPass = "a";
    private String dbClass = "com.mysql.jdbc.Driver";
    private Connection connection;
    private ResultSet dbResultSet;
    private Statement dbStatement;

    public boolean connect() throws SQLException, ClassNotFoundException {
        boolean result = false;
        Class.forName(dbClass);
        if (connection == null) {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            if (!connection.isClosed()) {
                result = true;
            }
        }
        return result;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // insert update delete
    public boolean executeQuery(String sql) throws SQLException {
        boolean retVal = true;
        try {
            // this.connect();
            dbStatement = connection.createStatement();
            dbStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("error in insert or update query :"
                    + ex.getMessage());
            retVal = false;
        } finally {
            // this.disconnect();
            dbStatement = null;
            // dbResultSet.close();
        }
        return retVal;
    }

    public ResultSet selectRecords(String tableName) throws SQLException {
        try {
            // this.connect();

            // this.connect();

            dbStatement = connection
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            dbResultSet = dbStatement
                    .executeQuery("select * from " + tableName);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            // this.disconnect();
        }
        return dbResultSet;
    }

    public ResultSet customQuery(String sqlQuery) throws SQLException {
        try {
            // this.connect();
            dbStatement = connection.createStatement();
            dbResultSet = dbStatement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            // this.disconnect();
        }
        return dbResultSet;
    }

    // not for sqlite
    public ResultSet insertQuery(String sql) throws SQLException {
        try {
            this.connect();
            dbStatement = connection.createStatement();
            dbStatement.execute(sql, Statement.RETURN_GENERATED_KEYS);
            dbResultSet = dbStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            this.disconnect();
        }
        return dbResultSet;
    }
}
