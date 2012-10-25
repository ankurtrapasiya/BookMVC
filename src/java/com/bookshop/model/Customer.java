package com.bookshop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookshop.db.DBConnection;

public class Customer {

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", name=" + name
				+ ", address=" + address + ", city=" + city + ", pincode="
				+ pincode + "]";
	}

	private int customer_id;
	private String name;
	private String address;
	private String city;
	private String pincode;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public boolean addCustomer() {
		boolean retval = false;
		DBConnection db = new DBConnection();

		String query = "insert into customer(id,name,address,city,pincode) values";
		query += "(" + this.customer_id;
		query += ",'" + this.name;
		query += "','" + this.address;
		query += "','" + this.city;
		query += "','" + this.pincode;
		query += "')";

		try {
			db.connect();
			retval = db.executeQuery(query);
			if (retval) {
				ResultSet rs = db
						.customQuery("select * from customer where name='"
								+ this.name + "'");
				rs.next();
				this.customer_id = rs.getInt("id");
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

	public Customer getCustomerFromId(Integer id) {
		Customer c = null;
		DBConnection db = null;
		try {
			db = new DBConnection();
			db.connect();

			ResultSet rs = db.customQuery("select * from customer where id="
					+ id);
			if (rs != null) {
				rs.next();
				c = new Customer();
				c.setCustomer_id(id);
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setCity(rs.getString("city"));
				c.setPincode(rs.getString("pincode"));
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				db.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return c;
	}
}
