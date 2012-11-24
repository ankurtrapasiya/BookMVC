package com.bookshop.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.bookshop.db.DBConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order {

    @Override
    public String toString() {
        return "Order [order_id=" + order_id + ", order_date=" + order_date
                + ", amount=" + amount + ", status=" + status + ", customer="
                + customer + ", orderDetails=" + orderDetails + "]";
    }
    private int order_id;
    
    private String shippingAddress;

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    private Date order_date;
    private float amount;
    private String status;
    private Customer customer;
    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean addOrder(Order o) {
        boolean retval = false;
        DBConnection db = new DBConnection();

        String query = "insert into `order`(order_date,amount,status,customer_id,shipping_address) values";
        query += "('" + this.order_date;
        query += "'," + this.amount;
        query += ",'" + this.status;
        query += "'," + this.customer.getCustomer_id();
        query += "','" + this.getShippingAddress();
        query += "')";

        System.out.println("query " + query);
        
        int order_id = 0;
        ResultSet rs = null;

        try {
            
            db.connect();
            
            db.executeQuery(query);

            rs = db.customQuery("select last_insert_id() as order_id;");

            rs.next();
            order_id = rs.getInt("order_id");
            this.setOrder_id(order_id);
            
            OrderDetail od;

            for (int i = 0; i < orderDetails.size(); i++) {
                od = orderDetails.get(i);
                od.setOrder_id(order_id);

                query = "insert into order_detail(order_id,book_id,qty) values";
                query += "(" + od.getOrder_id();
                query += "," + od.getBook_id();
                query += "," + od.getQuantity();
                query += ")";
                
                db.executeQuery(query);
                
                
                Book b=new Book();
                b=b.getBookFromId(od.getBook_id());
                b.updateStock(Stock.DECR, od.getQuantity());

            }

            retval = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retval;

    }
}
