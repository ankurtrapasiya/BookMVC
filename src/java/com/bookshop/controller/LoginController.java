package com.bookshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.Customer;
import com.bookshop.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("txtUsername");
		String password = request.getParameter("txtUsername");

		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		boolean retval = u.Login();
		HttpSession session = null;

		if (retval) {
			session = request.getSession(false);

			Customer c=new Customer();
			c=c.getCustomerFromId(u.getUserid());
			
			session.setAttribute("user", u);
			session.setAttribute("customer", c);

			String previousURL = (String) session.getAttribute("previousURL");

			if (previousURL != null) {
				response.sendRedirect(previousURL);
			} else {
				response.sendRedirect("home.jsp");
			}

		}

	}

}
