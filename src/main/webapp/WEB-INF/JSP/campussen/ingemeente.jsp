<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head
	title='Campussen in ${empty param.gemeente ? "een gemeente" : param.gemeente}'></v:head>
</head>
<body>
	<v:menu />
	<h1>Campussen in ${empty param.gemeente ? 'een gemeente' : param.gemeente}</h1>
	<form>
		<label>Gemeente: <span>${fouten.gemeente}</span> 
			<input name='gemeente' value='${param.gemeente}' autofocus required type='search'>
		</label> 
		<input type='submit' value='Zoeken'>
	</form>
	
	<c:if test='${not empty param and empty fouten and empty campussen}'>
		Geen campussen gevonden
	</c:if>
	
	<c:if test="${not empty campussen}">
		<ul>
			<c:forEach items='${campussen}' var='campus'>
				<li>${campus.naam}:${campus.adres.straat}
					${campus.adres.huisNr}
				</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>