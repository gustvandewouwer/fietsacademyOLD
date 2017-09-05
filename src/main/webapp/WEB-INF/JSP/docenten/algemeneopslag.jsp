<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Algemene opslag docenten' />
</head>
<body>
	<v:menu />
	<h1>Algemene opslag docenten</h1>
	<form method='post' id='opslagform'>
		<label>Percentage:<span>${fouten.percentage}</span> 
			<input name='percentage' value='${param.percentage}' type='number' min='0.01' step='0.01' autofocus>
		</label> 
		<input type='submit' value='Opslag' id='submitknop'>
	</form>
	<script>
		document.getElementById('opslagform').onsubmit = function() {
			document.getElementById('submitknop').disabled = true;
		};
	</script>
</body>
</html>