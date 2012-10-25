package com.bookshop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.Book;
import java.util.Enumeration;

/**
 * Servlet implementation class BookController
 */
public class BookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
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

        Book b = new Book();
        List<Book> list = b.getBookList();
        request.setAttribute("bookList", list);
        request.setAttribute("message", "hi");
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
               
        String hiddenId = request.getParameter("hdnBookId");
        System.out.println("hiddenId" + hiddenId);
        HttpSession session = request.getSession(false);
        HashMap<Integer, Book> books = null;
        if (hiddenId != null) {
            Book b = new Book();
            b = b.getBookFromId(Integer.parseInt(hiddenId));
            books = (HashMap<Integer, Book>) session.getAttribute("books");
            System.out.println(books);
            books.put(b.getId(), b);
            session.setAttribute("books", books);
        }
        response.sendRedirect("BookList");
    }
}
