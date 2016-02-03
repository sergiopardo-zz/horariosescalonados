<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css">
<title>Upload Empleados</title>
</head>
<body>
	<form action="<%=blobstoreService.createUploadUrl("/uploadhorarios") %>" method="post" enctype="multipart/form-data">
		<table align="center" width="80%" border="0">
			<tr>
				<td>
					<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif">HORARIOS</p>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left" style="font:Geneva, Arial, Helvetica, sans-serif">Ejemplo de datos contenidos en el archivo:</p>
				</td>
			</tr>
			<tr>
				<td>
					<table style="width:300px;" align="center" cellspacing="2" cellpadding="2" class="tblCandidato" border="0">
						<thead>
							<tr class="CabeceraTabla">
								<th width="40%">Id del Usuario (M99999)</th>
								<th width="30%">Hora Entrada (0:00)</th>
								<th width="30%">Hora Salida (0:00)</th>								
							</tr>
						</thead>
						<tbody>
							<tr class="tdCandidatoD">
								<td style="text-align:center;">M99999</td>
								<td style="text-align:center;">8:30</td>
								<td style="text-align:center;">17:30</td>
							</tr>
						</tbody>
					</table>			
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="foo" style="display:none"/><input type="file" name="myFile"/>
					&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit" />		
				</td>
			</tr>
			<tr><td><br/><br/><br/></td></tr>
			<tr>
				<td style="text-align:center;">
					<a href="/excelejemplohorarios" >[Descargar archivo de ejemplo para Horarios]</a>							
				</td>
			</tr>
		</table>
	</form>
</body>
</html>