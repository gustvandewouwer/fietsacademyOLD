package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Docent;
import be.vdab.services.DocentService;
import be.vdab.util.StringUtils;

@WebServlet("/docenten/vantotwedde.htm")
public class VanTotWeddeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/vantotwedde.jsp";
	private final transient DocentService docentService = new DocentService();
	private static final int AANTAL_RIJEN = 20;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getQueryString() == null) {
			request.setAttribute("tot", docentService.findMaxWedde());
		} else {
			Map<String, String> fouten = new HashMap<>();
			String van = request.getParameter("van");
			if (!StringUtils.isBigDecimal(van)) {
				fouten.put("van", "tik een getal");
			}
			String tot = request.getParameter("tot");
			if (!StringUtils.isBigDecimal(tot)) {
				fouten.put("tot", "tik een getal");
			}
			if (fouten.isEmpty()) {
				int vanafRij = request.getParameter("vanafRij") == null ? 0
						: Integer.parseInt(request.getParameter("vanafRij"));
				request.setAttribute("vanafRij", vanafRij);
				request.setAttribute("aantalRijen", AANTAL_RIJEN);
				List<Docent> docenten = docentService.findByWeddeBetween(new BigDecimal(van), new BigDecimal(tot),
						vanafRij, AANTAL_RIJEN + 1);
				if (docenten.size() <= AANTAL_RIJEN) {
					request.setAttribute("laatstePagina", true);
				} else {
					docenten.remove(AANTAL_RIJEN);
				}
				request.setAttribute("docenten", docenten);
			} else {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
