package com.bookshop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookshop.db.DBConnection;


public class Author {

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", details=" + details
				+ "]";
	}
	public Author(String name, String details) {
		super();
		this.name = name;
		this.details = details;
	}
	public Author()
	{
		
	}

	private Integer id;
	private String name;
	private String details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean addAuthor() throws SQLException {
		boolean retval = false;
		DBConnection db = new DBConnection();
		try {
			db.connect();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String query = "insert into author(name,details) values";
		query += "('" + this.name;
		query += "','" + this.details;
		query += "')";

		try {
			retval = db.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// db.disconnect();
		}

		return retval;
	}

	public List<Author> getAuthorList() {
		List<Author> value = null;

		DBConnection db = new DBConnection();

		try {
			if (db.connect()) {
				ResultSet rs = db.customQuery("select * from author");
				if (rs != null) {
					value = new ArrayList<Author>();

					while (rs.next()) {

						Author a = new Author();
						int authorId = rs.getInt("id");
						a.setId(authorId);
						a.setName(rs.getString("name"));
						a.setDetails(rs.getString("details"));
						value.add(a);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				db.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return value;
	}

}
