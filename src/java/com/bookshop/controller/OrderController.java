package com.bookshop.controller;

import com.bookshop.model.Book;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.Customer;
import com.bookshop.model.Order;
import com.bookshop.model.OrderDetail;
import com.bookshop.model.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class OrderController
 */
public class OrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
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
        HashMap<Integer, Integer> books = (HashMap<Integer, Integer>) session.getAttribute("books");


        Float amount = 0.0f;

        Customer c = (Customer) session.getAttribute("customer");
        Order o = new Order();
        o.setCustomer(c);
        o.setOrder_date(new java.sql.Date(new java.util.Date().getTime()));
        o.setStatus(OrderStatus.IN_PROCESS.toString());

        List<OrderDetail> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> val : books.entrySet()) {
            OrderDetail od = new OrderDetail();

            Book b = new Book();
            b = b.getBookFromId(val.getKey());

            od.setBook_id(b.getId());
            od.setQuantity(val.getValue());

            amount += (Float.valueOf(b.getPrice()) * val.getValue());
            
            list.add(od);
        }

        o.setAmount(amount);

        o.setOrderDetails(list);

        boolean retval = o.addOrder(o);

        //  boolean retval = o.addOrder(books);
        if (retval) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }

        RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
        rd.forward(request, response);

    }
}
