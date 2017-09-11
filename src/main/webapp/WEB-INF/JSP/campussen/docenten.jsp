<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Docenten per campus' />
</head>
<body>
	<v:menu />
	<h1>Docenten per campus</h1>
	<ul class='zonderbolletjes'>
		<c:forEach items='${campussen}' var='campus'>
			<c:url value='' var='url'>
				<c:param name='id' value='${campus.id}' />
			</c:url>
			<li><a href='${url}'>${campus.naam}</a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty campus}'>
		<h2>${campus.naam}(${campus.adres.gemeente})</h2>
		<dl>
			<c:forEach items='${campus.docenten}' var='docent'>
				<dt>${docent.naam}</dt>
				<dd>
					&euro;
					<fmt:formatNumber value='${docent.wedde}' maxFractionDigits='2'
						minFractionDigits='2' />
				</dd>
			</c:forEach>
		</dl>
	</c:if>
</body>
</html>