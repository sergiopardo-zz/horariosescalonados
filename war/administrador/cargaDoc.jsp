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
<head><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../config/css/estilosHE.css">
<title>Upload Sistem</title>
</head>
<body>
<form action="<%=blobstoreService.createUploadUrl("/uploadentradasalida") %>" 	method="post" enctype="multipart/form-data">
		<table align="center" width="80%">
			<tr>
				<td>
					<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif">ENTRADA-SALIDA</p>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left" style="font:Geneva, Arial, Helvetica, sans-serif">Ejemplo de datos contenidos en el archivo:</p>
				</td>
			</tr>
			<tr>
				<td>
					<table width="50%" cellspacing="2" cellpadding="2" class="tblCandidato" border="0">
						<thead>
							<tr class="CabeceraTabla">
								<th width="5%">Fecha (DD/MM/YYYY)</th>
								<th width="5%">Hora (HH:MM:SS AM/PM)</th>
								<th width="5%">Nombre del Empleado (Paterno Materno, Nombre)</th>								
								<th width="5%">Número Personal</th>
								<th width="5%">Id del Usuario (M99999)</th>
								<th width="5%">Número de Tarjeta</th>
								<th width="5%">Lector</th>
								<th width="5%">Nombre Lector</th>
								<th width="5%">Alarma</th>
								<th width="5%">Tipo Alarma</th>
								<th width="5%">Función</th>
								<th width="5%">Tipo Función</th>
								<th width="5%">Edificio</th>
							</tr>
						</thead>
						<tbody>
							<tr class="tdCandidatoD">
								<td style="text-align:center;">28/04/2015</td>
								<td style="text-align:center;">08:38:24 a. m.</td>
								<td style="text-align:center;">HERNANDEZ DEL FUERTE, HERDEZ</td>
								<td style="text-align:center;">3333</td>
								<td style="text-align:center;">M99999</td>
								<td style="text-align:center;">33333</td>
								<td style="text-align:center;">333</td>
								<td style="text-align:center;">ECBC TORNIQUETE_8_SALIDA</td>
								<td style="text-align:center;">0</td>
								<td style="text-align:center;">ACCESO CORRECTO</td>
								<td style="text-align:center;">B1</td>
								<td style="text-align:center;">ENTRADA</td>
								<td style="text-align:center;">CENTRO BANCOMER</td>
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
			<tr>
				<td  class="confirmacion">
				<br/>								
					* Estimado usuario, el campo de fecha debe tener formato de texto en el archivo excel.								
				</td>
			</tr>
			<tr><td><br/><br/></td></tr>
			<tr>
				<td style="text-align:center;">
					 [Este documento es proporcionado por ZEIT]							
				</td>
			</tr>
		</table>
	</form>
	<br/><br/>
	<form action="<%=blobstoreService.createUploadUrl("/cargadocumentomes") %>" 	method="post" enctype="multipart/form-data">
		<table align="center" width="80%">
			<tr>
				<td>
					<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif">CARGA DOCUMENTO REPORTE</p>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="foo" style="display:none"/><input type="file" name="myFile"/>
					&nbsp;&nbsp;&nbsp;<input type="submit" name="Submit" />		
				</td>
			</tr>
			<tr>
				<td  class="confirmacion">
				<br/>								
					* Estimado usuario, el campo de fecha debe tener formato de texto en el archivo excel.								
				</td>
			</tr>
			<tr><td><br/><br/><br/></td></tr>
		</table>
	</form>
</body>
</html>