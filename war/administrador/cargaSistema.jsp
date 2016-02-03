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
<title>Upload Sistem</title>
</head>
<body>
	<form action="<%=blobstoreService.createUploadUrl("/uploadempleados") %>" method="post" enctype="multipart/form-data">
		<table align="center" width="80%">
			<tr>
				<td>
					<p align="center" style="font: Geneva, Arial, Helvetica, sans-serif">EMPLEADOS</p>
				</td>
			</tr>
			<tr>
				<td>
					<p align="left" style="font:Geneva, Arial, Helvetica, sans-serif">Ejemplo de datos contenidos en el archivo:</p>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" cellspacing="2" cellpadding="2" class="tblCandidato" border="0">
						<thead>
							<tr class="CabeceraTabla">
								<th width="5%">Id del Usuario (M99999)</th>
								<th width="5%">Id del Supervisor (M99999)</th>
								<th width="5%">Edificio</th>
								<th width="5%">Tipo Empleado</th>
								<th width="10%">Apellido Paterno</th>
								<th width="10%">Apellido Materno</th>
								<th width="10%">Nombre</th>
								<th width="15%">Correo Empleado</th>
								<th width="5%">Horario Empleado</th>
								<th width="5%">Registro Empleado</th>
								<th width="5%">Nombre CR</th>
								<th width="5%">DGA (LN3)</th>
							</tr>
						</thead>
						<tbody>
							<tr class="tdCandidatoD">
								<td style="text-align:center;">M99991</td>
								<td style="text-align:center;">M99999</td>
								<td style="text-align:center;">REFORMA</td>
								<td style="text-align:center;">E</td>
								<td style="text-align:center;">HERNANDEZ</td>
								<td style="text-align:center;">DEL FUERTE</td>
								<td style="text-align:center;">HERDEZ</td>
								<td style="text-align:center;">correo.empleado.contactor@bbva.com</td>
								<td style="text-align:center;">N/A</td>
								<td style="text-align:center;">11111</td>
								<td style="text-align:center;">JAREZ CABALLITO</td>
								<td style="text-align:center;">RED COMERCIAL</td>
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
			<tr><td><br/><br/></td></tr>
			<tr>
				<td style="text-align:center;">
					<a href="/excelejemploempleados" >[Descargar archivo de ejemplo]</a>							
				</td>
			</tr>
		</table>
	</form>
	<br/><br/><br/><br/>
	
</body>
</html>