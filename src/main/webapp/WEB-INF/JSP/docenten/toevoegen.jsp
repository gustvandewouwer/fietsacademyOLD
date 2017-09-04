<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Docent toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Docent toevoegen</h1>
	<form method='post' id='toevoegform'>
		<label>Voornaam:<span>${fouten.voornaam}</span> <input
			name='voornaam' value='${param.voornaam}' autofocus required></label>
		<label>Familienaam: <span>${fouten.familienaam}</span> <input
			name='familienaam' value='${param.familienaam}' required></label> <label>Wedde:<span>${fouten.wedde}</span>
			<input name='wedde' value='${param.wedde}' required type='number'
			min='0' step='0.01'></label>
		<div>
			<label><span>${fouten.geslacht}</span> <input type='radio'
				name='geslacht' value='MAN'
				${param.geslacht=='MAN' ? 'checked' : ''}>Man</label>
		</div>
		<div>
			<label><input type='radio' name='geslacht' value='VROUW'
				${param.geslacht=='VROUW' ? 'checked' : ''}>Vrouw</label>
		</div>
		<label>Rijksregisternummer:<span>${fouten.rijksregisternr}
		</span> <input name='rijksregisternr' value='${param.rijksregisternr}'
			required type='number' min='10000000000' max='99999999999'></label> <input
			type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>