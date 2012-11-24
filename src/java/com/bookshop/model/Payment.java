package com.bookshop.model;

import java.sql.SQLException;

import com.bookshop.db.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment {

    @Override
    public String toString() {
        return "Payment [id=" + id + ", order=" + order + ", payment_type="
                + payment_type + "]";
    }
    private int id;
    private Order order;
    private int payment_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public boolean doPayment() {
        boolean retval = false;
        DBConnection db = new DBConnection();

        String query = "insert into payment(order_id,payment_type) values";
        query += "(" + this.order.getOrder_id();
        query += "," + this.payment_type;
        query += ")";

        try {
            db.connect();

            retval = db.executeQuery(query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retval;
    }
}
