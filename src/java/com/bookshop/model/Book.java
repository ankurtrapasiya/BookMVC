package com.bookshop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bookshop.db.DBConnection;

public class Book {

    private Integer id;
    private String name;
    private String ISBN;
    private String image;
    private String title;
    private String publisher;
    private String price;
    private ArrayList<Author> authors;
    private Integer stock;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String isbn) {
        ISBN = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return true;
    }

    public boolean updateStock(Stock stock, int qty) {

        boolean retval = false;
        String query;

        DBConnection db = new DBConnection();
        query = "select stock from book_stock where bookId=" + this.id;

        try {
            ResultSet rs = db.customQuery(query);

            int stk = rs.getInt("stock");

            if (stock == Stock.DECR) {
                if (stk > 0 && (stk - qty) > 0) {
                    query = "update book_stock set stock=" + (stk - qty);
                    db.executeQuery(query);
                }
            } else if (stock == Stock.INCR) {
                if (stk > 0) {
                    query = "update book_stock set stock=" + (stk + qty);
                    db.executeQuery(query);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        // String query="update Book set stock=" ;

        return retval;

    }

    public boolean addBook() {
        boolean retval = false;

        DBConnection db = new DBConnection();
        try {
            db.connect();
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String query = "insert into book(name,ISBN,image,title,publisher,price) values";
        query += "('" + this.name;
        query += "','" + this.ISBN;
        query += "','" + this.image;
        query += "','" + this.title;
        query += "','" + this.publisher;
        query += "','" + this.price;
        query += "')";

        int bookId, authorId;
        ResultSet rs = null;

        try {
            if (db.executeQuery(query)) {

                query = "SELECT id from book where name='" + this.name + "'";

                System.out.println(query);

                rs = db.customQuery(query);

                rs.next();

                bookId = rs.getInt("id");

                Iterator<Author> iterator = this.authors.iterator();

                rs.close();

                while (iterator.hasNext()) {
                    Author a = iterator.next();

                    a.addAuthor();

                    query = "select id from author where name = '"
                            + a.getName() + "'";

                    System.out.println(query);

                    rs = db.customQuery(query);

                    rs.next();

                    authorId = rs.getInt("id");

                    rs.close();

                    System.out.println(authorId);
                    System.out.println(bookId);

                    query = "insert into book_author(book_id,author_id) values("
                            + bookId + "," + authorId + ")";

                    db.executeQuery(query);

                }
                retval = true;
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

        return retval;
    }

    public List<Book> getBookList() {

        List<Book> value = null;

        DBConnection db = new DBConnection();
        try {
            db.connect();
            ResultSet rs = db.selectRecords("book");
            if (rs != null) {
                value = new ArrayList<Book>();

                while (rs.next()) {

                    Book b = new Book();
                    int bookId = rs.getInt("id");
                    b.setId(bookId);
                    b.setName(rs.getString("name"));
                    b.setISBN(rs.getString("ISBN"));
                    b.setImage(rs.getString("image"));
                    b.setTitle(rs.getString("title"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setPrice(String.valueOf(rs.getDouble("price")));

                    ResultSet rs2 = db
                            .customQuery("select * from book_author where book_id ="
                            + bookId);

                    ArrayList<Author> list = null;

                    if (rs2 != null) {

                        list = new ArrayList<Author>();

                        while (rs2.next()) {

                            ResultSet rs3 = db
                                    .customQuery("select * from author where id="
                                    + rs2.getInt("author_id"));

                            while (rs3.next()) {
                                Author a = new Author();
                                a.setId(rs3.getInt("id"));
                                a.setName(rs3.getString("name"));
                                a.setDetails(rs3.getString("details"));

                                System.out.println(a);
                                list.add(a);
                            }
                        }
                        System.out.println(list);
                        b.setAuthors(list);
                        value.add(b);
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

    public Book getBookFromId(Integer id) {
        Book b = null;
        try {
            DBConnection db = new DBConnection();
            db.connect();

            ResultSet rs = db.customQuery("select * from book where id=" + id);
            if (rs != null) {
                rs.next();
                b = new Book();
                b.setId(id);
                b.setName(rs.getString("name"));
                b.setISBN(rs.getString("ISBN"));
                b.setImage(rs.getString("image"));
                b.setTitle(rs.getString("title"));
                b.setPublisher(rs.getString("publisher"));
                b.setPrice(String.valueOf(rs.getDouble("price")));

                ResultSet rs2 = db
                        .customQuery("select * from book_author where book_id ="
                        + id);

                ArrayList<Author> list = null;

                if (rs2 != null) {

                    list = new ArrayList<Author>();

                    while (rs2.next()) {

                        ResultSet rs3 = db
                                .customQuery("select * from author where id="
                                + rs2.getInt("author_id"));

                        while (rs3.next()) {
                            Author a = new Author();
                            a.setId(rs3.getInt("id"));
                            a.setName(rs3.getString("name"));
                            a.setDetails(rs3.getString("details"));

                            System.out.println(a);
                            list.add(a);
                        }
                    }
                    b.setAuthors(list);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", ISBN=" + ISBN
                + ", image=" + image + ", title=" + title + ", publisher="
                + publisher + ", price=" + price + ", authors=" + authors
                + ", stock=" + stock + "]";
    }
}
