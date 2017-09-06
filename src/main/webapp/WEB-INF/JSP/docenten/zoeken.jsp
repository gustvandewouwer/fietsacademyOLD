<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='${empty docent ? "Docent zoeken" : docent.naam}' />
</head>
<body>
	<v:menu />
	<h1>Docent zoeken</h1>
	<form>
		<label>Nummer:<span>${fouten.id}</span> <input name='id'
			value='${param.id}' required autofocus type='number' min='1'></label>
		<input type='submit' value='Zoeken'>
	</form>
	<c:if test='${not empty param and empty fouten and empty docent}'>
		Docent niet gevonden
	</c:if>
	<c:if test='${not empty docent}'>
		${docent.geslacht == 'MAN' ? '&#x2642;' : '&#x2640;'}
		${docent.naam}, wedde: &euro; <fmt:formatNumber
			value='${docent.wedde}' />

		<c:if test='${not empty docent.bijnamen}'>
			<h2>Bijnamen</h2>
			
			<form method='post'>
				<ul>
					<c:forEach items='${docent.bijnamen}' var='bijnaam'>
						<li>
							<label>${bijnaam} 
								<input type='checkbox' name='bijnaam' value='${bijnaam}'>
							</label>
						</li>
					</c:forEach>
				</ul>
				
				<input type='submit' value='Bijnamen verwijderen' name='verwijderen'>
			</form>			
		</c:if>

		<form method='post' id='toevoegform'>
			<label>Bijnaam: <span>${fouten.bijnaam}</span> 
				<input name='bijnaam' value='${param.bijnaam}' required>
			</label> 
			<input type='submit' value='Toevoegen' id='toevoegknop'>
		</form>
		<script>
			document.getElementById('toevoegform').onsubmit = function() {
				document.getElementById('toevoegknop').disabled = true;
			};
		</script>

		<h2>Acties</h2>
		<c:url value='/docenten/verwijderen.htm' var='verwijderURL'>
			<c:param name='id' value='${docent.id}' />
		</c:url>
		<form action='${verwijderURL}' method='post'>
			<input type='submit' value='Verwijderen'>
		</form>

		<c:url value='/docenten/opslag.htm' var='opslagURL'>
			<c:param name='id' value='${docent.id}' />
		</c:url>
		<a href='${opslagURL}' class='knop'><input type="button"
			value='Opslag'></a>
	</c:if>
</body>
</html>