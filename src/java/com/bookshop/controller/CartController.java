/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookshop.controller;

import com.bookshop.model.Book;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ankur
 */
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HashMap<Integer, Integer> books = null;
        HttpSession session = request.getSession(false);
        HashMap<Book, Integer> map = null;

        if (session != null) {

            books = (HashMap<Integer, Integer>) session.getAttribute("books");


            map = new HashMap<>();

            if (books != null) {
                for (Map.Entry<Integer, Integer> val : books.entrySet()) {


                    Book b = new Book();
                    b = b.getBookFromId(val.getKey());

                    b.setPrice(String.valueOf(Float.valueOf(b.getPrice()) * val.getValue()));

                    map.put(b, val.getValue());

                }
            }
        }

        request.setAttribute("books", map);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        HashMap<Integer, Integer> books = (HashMap<Integer, Integer>) session.getAttribute("books");

        if (request.getParameter("hdnBookId") != null) {

            Integer bookId = Integer.parseInt(request.getParameter("hdnBookId"));

            books.remove(bookId);

            session.setAttribute("books", books);

            response.sendRedirect("CartController");
        }
    }
}
