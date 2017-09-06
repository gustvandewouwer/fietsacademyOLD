package be.vdab.servlets.cursussen;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.CursusService;

/**
 * Servlet implementation class MetNaamServlet
 */
@WebServlet("/cursussen/metnaam.htm")
public class MetNaamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/cursussen/metnaam.jsp";
	private final transient CursusService cursusService = new CursusService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String woord = request.getParameter("woord");
		if (woord != null) {
			if (woord.trim().isEmpty()) {
				request.setAttribute("fouten", Collections.singletonMap("woord", "verplicht"));
			} else {
				request.setAttribute("cursussen", cursusService.findByNaamContains(woord));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}