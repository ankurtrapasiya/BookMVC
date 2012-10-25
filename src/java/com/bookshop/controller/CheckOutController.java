package com.bookshop.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.Book;
import com.bookshop.util.Util;

/**
 * Servlet implementation class CheckOutController
 */
public class CheckOutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        boolean flag = false;
        if (session != null) {
            @SuppressWarnings("unchecked")
            HashMap<Integer, Book> books = (HashMap<Integer, Book>) session
                    .getAttribute("books");

            for (Integer key : books.keySet()) {
                String btnRemoveName = "btnRemove" + key;
                String btnUpdateName = "btnUpdate" + key;
                String txtQuantityName = "txtQuantity" + key;
                if (request.getParameter(btnRemoveName) != null) {
                    books.remove(key);
                    flag = true;
                    break;
                } else if (request.getParameter(btnUpdateName) != null) {
                    Book b = books.get(key);
                    b.setQuantity(Integer.parseInt(request
                            .getParameter(txtQuantityName)));
                    books.put(key, b);
                    flag = true;
                    break;
                }
            }
            session.setAttribute("amount", Util.calculateAmount(books));
            if (!flag) {
                RequestDispatcher rd = request
                        .getRequestDispatcher(("order.jsp"));
                rd.forward(request, response);
            }

        }
    }
}
