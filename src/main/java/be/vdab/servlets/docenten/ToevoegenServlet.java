package be.vdab.servlets.docenten;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Docent;
import be.vdab.enums.Geslacht;
import be.vdab.services.DocentService;
import be.vdab.util.StringUtils;

@WebServlet("/docenten/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/docenten/zoeken.htm?id=%d";
	private final transient DocentService docentService = new DocentService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String voornaam = request.getParameter("voornaam");
		if (!Docent.isVoornaamValid(voornaam)) {
			fouten.put("voornaam", "verplicht");
		}
		String familienaam = request.getParameter("familienaam");
		if (!Docent.isFamilienaamValid(familienaam)) {
			fouten.put("familienaam", "verplicht");
		}
		String weddeString = request.getParameter("wedde");
		BigDecimal wedde = null;
		if (StringUtils.isBigDecimal(weddeString)) {
			wedde = new BigDecimal(weddeString);
			if (!Docent.isWeddeValid(wedde)) {
				fouten.put("wedde", "tik een positief getal of 0");
			}
		} else {
			fouten.put("wedde", "tik een positief getal of 0");
		}
		String geslacht = request.getParameter("geslacht");
		if (geslacht == null) {
			fouten.put("geslacht", "verplicht");
		}
		String rijksRegisterNrString = request.getParameter("rijksregisternr");
		long rijksRegisterNr = 0;
		if (StringUtils.isLong(rijksRegisterNrString)) {
			rijksRegisterNr = Long.parseLong(rijksRegisterNrString);
			if (!Docent.isRijksRegisterNrValid(rijksRegisterNr)) {
				fouten.put("rijksregisternr", "verkeerde cijfers");
			}
		} else {
			fouten.put("rijksregisternr", "verkeerde cijfers");
		}
		if (fouten.isEmpty()) {
			Docent docent = new Docent(voornaam, familienaam, wedde, Geslacht.valueOf(geslacht), rijksRegisterNr);
			docentService.create(docent);
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(), docent.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
