package com.bookshop.model;

import java.sql.SQLException;

import com.bookshop.db.DBConnection;


public class OrderDetail {

	@Override
	public String toString() {
		return "OrderDetail [order_id=" + order_id + ", book_id=" + book_id
				+ ", quantity=" + quantity + "]";
	}

	private int order_id;
	private int book_id;
	private int quantity;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean addOrderDetail(int order_id, int book_id, int quantity) {
		boolean retval = false;
		String query = null;
		DBConnection db = new DBConnection();

		query = "insert into order_detail(order_id,book_id,qty) values(";
		query += order_id;
		query += "," + book_id + "," + quantity + ")";
		try {
			retval=db.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retval;
	}

}
