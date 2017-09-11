package be.vdab.servlets.campussen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.CampusService;
import be.vdab.services.DocentService;

@WebServlet("/campussen/docenten.htm")
public class CampusDocentenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/docenten.jsp";
	private final transient CampusService campusService = new CampusService();
	private final transient DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("campussen", campusService.findAll());
		String id = request.getParameter("id");
		if (id != null) {
			if (request.getParameter("bestbetaalde") != null) {
				request.setAttribute("docenten", docentService.findBestBetaaldeVanEenCampus(Long.parseLong(id)));
			}
			campusService.read(Long.parseLong(id)).ifPresent(campus -> request.setAttribute("campus", campus));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}