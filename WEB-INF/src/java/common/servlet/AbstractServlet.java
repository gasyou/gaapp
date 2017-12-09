package common.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

	protected void setForward(HttpServletRequest req, HttpServletResponse resp, String jsp) throws ServletException, IOException {
		getServletConfig().getServletContext().getRequestDispatcher(jsp).forward(req, resp);
	}
}
