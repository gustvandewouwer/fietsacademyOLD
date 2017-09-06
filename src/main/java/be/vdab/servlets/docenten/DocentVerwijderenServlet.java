package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.DocentService;

@WebServlet("/docenten/verwijderen.htm")
public class DocentVerwijderenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final transient DocentService docentService = new DocentService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		docentService.delete(Long.parseLong(request.getParameter("id")));
		System.out.println("request.getContextPath()= " + request.getContextPath());
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
	}
}