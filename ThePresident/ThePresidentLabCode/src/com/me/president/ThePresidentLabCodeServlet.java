package com.me.president;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ThePresidentLabCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
            // Requête Objectify
            List<Comment> comments = Comment.retrieveAllCached();          
            req.setAttribute("comments", comments);
            req.setAttribute("pluscounter", Counter.value("pluscounter"));
        	req.setAttribute("minuscounter",Counter.value("minuscounter"));
            this.getServletContext().getRequestDispatcher("/comments.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
        try {
        	Comment.store(req.getParameter("user-comment"), req.getParameter("user-name"));
            resp.sendRedirect("/");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
