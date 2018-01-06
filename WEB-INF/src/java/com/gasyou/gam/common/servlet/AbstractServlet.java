package com.gasyou.gam.common.servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

	protected void setForward(HttpServletRequest req, HttpServletResponse resp, String jsp) throws Exception {
		getServletConfig().getServletContext().getRequestDispatcher(jsp).forward(req, resp);
	}
}
