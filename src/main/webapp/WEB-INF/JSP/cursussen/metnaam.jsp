<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Cursussen zoeken op naam'></v:head>
</head>
<body>
	<v:menu />
	<h1>Cursussen zoeken op naam</h1>
	<form>
		<label>Woord:<span>${fouten.woord}</span> 
			<input name='woord' value='${param.woord}' autofocus required type='search'>
		</label> 
		<input type='submit' value='Zoeken'>
	</form>

	<c:if test='${not empty param and empty fouten and empty cursussen}'>
		Geen cursussen gevonden
	</c:if>
	
	<c:if test='${not empty cursussen}'>
		<ul>
			<c:forEach items='${cursussen}' var='cursus'>
				<c:set var='soortCursus' value="${cursus['class'].simpleName}" />
				<li>${cursus}
					<img src='<c:url value="/images/${soortCursus}.png"/>'
						alt='${soortCursus}' title='${soortCursus}'>
				</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>