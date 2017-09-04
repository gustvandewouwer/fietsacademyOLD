<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="#">Docenten</a>
			<ul>
				<li><a href="<c:url value='/docenten/zoeken.htm'/>">Zoeken op nummer</a></li>
				<li><a href="<c:url value='/docenten/toevoegen.htm'/>">Toevoegen</a></li>
				<li><a href='<c:url value='/docenten/vantotwedde.htm'/>'>Van tot wedde</a></li>
				<li><a href="<c:url value='/docenten/voornamen.htm'/>">Voornamen</a></li>
				<li><a href="<c:url value='/docenten/aantalperwedde.htm'/>">Aantal docenten per wedde</a></li>
				<li><a href="<c:url value='/docenten/algemeneopslag.htm'/>">Algemene opslag</a></li>
			</ul></li>
		<li><a href="#">Cursussen</a>
			<ul>
				<li><a href="<c:url value='/cursussen/metnaam.htm'/>">Zoeken op naam</a></li>
			</ul></li>
		<li><a href="#">Campussen</a>
			<ul>
				<li><a href="<c:url value='/campussen/ingemeente.htm'/>">Zoeken op gemeente</a></li>
				<li><a href="<c:url value='/campussen/docenten.htm'/>">Docenten</a></li>
			</ul></li>
	</ul>
</nav>
