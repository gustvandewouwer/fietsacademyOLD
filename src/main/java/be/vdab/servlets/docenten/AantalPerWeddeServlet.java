package be.vdab.servlets.docenten;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.DocentService;

@WebServlet("/docenten/aantalperwedde.htm")
public class AantalPerWeddeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/aantalperwedde.jsp";
	private final transient DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("weddesEnAantallen", docentService.findAantalDocentenPerWedde());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}