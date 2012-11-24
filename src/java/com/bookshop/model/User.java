package com.bookshop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookshop.db.DBConnection;

public class User {

    private Integer userid;
    private String username;
    private String password;
    private boolean isActive;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean addUser() {
        boolean retval = false;
        DBConnection db = new DBConnection();

        String query = "insert into user(username,password,isactive) values";
        query += "('" + this.username;
        query += "','" + this.password;
        query += "'," + this.isActive;
        query += "')";

        try {
            db.connect();
            retval = db.executeQuery(query);
            if (retval) {
                ResultSet rs = db.customQuery("select * from user where name='"
                        + this.username + "'");
                rs.next();
                this.userid = rs.getInt("userid");
                rs.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return retval;
    }

    public boolean Login() {
        boolean retval = false;
        DBConnection con = new DBConnection();
        String query = "select * from user where username ='" + this.username
                + "' and password='" + this.password + "'";
        try {
            con.connect();
            ResultSet rs = con.customQuery(query);
            if (rs != null) {
                rs.next();
                this.userid = rs.getInt("userid");
                this.isActive = rs.getBoolean("isactive");
                rs.close();
                retval = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                con.disconnect();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return retval;
    }
}
