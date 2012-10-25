package com.bookshop.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.bookshop.db.DBConnection;

public class Order {

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_date=" + order_date
				+ ", amount=" + amount + ", status=" + status + ", customer="
				+ customer + ", orderDetails=" + orderDetails + "]";
	}

	private int order_id;

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
	private OrderDetail orderDetails;

	public OrderDetail getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetail orderDetails) {
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

	public boolean addOrder(Map<Integer, Book> map) {
		boolean retval = false;
		DBConnection db = new DBConnection();

		String query = "insert into order(order_date,amount,status,customer_id) values";
		query += "(" + this.order_date;
		query += "," + this.amount;
		query += "," + this.status;
		query += "," + this.customer.getCustomer_id();
		query += ")";

		int order_id = 0;
		ResultSet rs = null;

		try {
			db.executeQuery(query);

			rs = db.customQuery("select last_insert_rowid();");

			order_id = rs.getInt("order_id");
			OrderDetail od;

			for (Map.Entry<Integer, Book> val : map.entrySet()) {
				od = new OrderDetail();
				od.addOrderDetail(order_id, val.getKey(), val.getValue()
						.getQuantity());
				val.getValue().updateStock(Stock.DECR,
						val.getValue().getQuantity());
			}

			retval = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retval;

	}

}
