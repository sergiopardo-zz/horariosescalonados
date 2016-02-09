<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

 	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
	<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
	<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="/config/css/estilo.css"/>
	
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css"/>
	<link rel="stylesheet" type="text/css" href="config/css/jquery-ui.css" />
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script>
	<script src="config/js/tooltip.js"></script>
	<script type="text/javascript" language="javascript">
	
	
		
		function radioClick(value){
				
			var empleadoid= document.getElementById("nombreUsuario").value;
			var empleadoNombre = document.getElementById("ingresarUsuario").value
						
			if(value=="INTERNOS"){
			
				document.getElementById("consInt").checked = true;
			
			}else if(value=="EXTERNOS"){
			
				document.getElementById("consExt").checked = true;
		
			}else if(value=="AMBOS"){
			
				document.getElementById("consInt").checked = true;
				document.getElementById("consExt").checked = true;
				
			}
			
				if(empleadoNombre != "" && empleadoid != ""){
				document.getElementById("aceptarConsulta").disabled=false;
			}
			
		}
		
		function checkModal(){
			var empleadoid= document.getElementById("nombreUsuario").value;
			var empleadoNombre = document.getElementById("ingresarUsuario").value;
			
			
						
						
			if(empleadoNombre != "" && empleadoid != ""){
				document.getElementById("aceptarConsulta").disabled=false;
			}
		}
		
		function estatusActivo(){
			if(document.getElementById("consQuit").checked){
				document.getElementById("consQuit").checked =false;
			}
		}
		function estatusInactivo(){
			if(document.getElementById("consExt").checked || document.getElementById("consInt").checked){
				document.getElementById("consExt").checked=false;
				document.getElementById("consInt").checked=false;
			}
		}
		
		function busquedaDeUsuario(value){
		
		document.forms["busquedaUsuarios"].submit();
		}
		
		function generar(){
		
		if(document.getElementById("consInt").checked && document.getElementById("consExt").checked){
		
			document.getElementById("hidTipoConsulta").value="AMBOS";
		
		}else if(document.getElementById("consInt").checked){
		
			document.getElementById("hidTipoConsulta").value="INTERNOS";
		
		}else if(document.getElementById("consExt").checked){
		
			document.getElementById("hidTipoConsulta").value="EXTERNOS";
		
		}else if(document.getElementById("consQuit").checked){
			document.getElementById("hidTipoConsulta").value="QUITAR";
		}
		 
			formsubir.submit();
		}

		function cargaArchivo(){
			var fileName = document.getElementById("upload").value;
			document.getElementById("iptExaminar").value = fileName;
		}
		
		</script> 
		
</head>

<body>

<div class="contenedor">
	<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:if test="${tipEmp == 'SS'}">
		<%@include file="header.jsp"%> 
	</c:if> 
  	<c:if test="${tipEmp == 'RH'}">
		<%@include file="header.jsp"%> 
	</c:if> 
	 <c:if test="${tipEmp == 'GE'}">
		<%@include file="header.jsp"%> 
	</c:if> 

	<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga" >
						<a href="adminPerfilConsulta.html"  style="text-decoration:none;" class="migaReporte tituloMiga">
							- Asignar perfil de consulta</a>
					</li>
				</ul>				
	</div>

	<form name="busquedaUsuarios" action="/perfilconsulta" method="post">
	<div class="contenido_PC">
					<c:if test="${empleadoBusqueda!=null }">
						<c:set var="empleado" value="${ empleadoBusqueda}"/>
						<c:set var="empleadoNombre" value="${empleadoBusqueda[0].nombre}"/>
						<c:set var="empleadoId" value="${empleadoBusqueda[0].empleadoID}"/>
						<c:set var="empleadoApellido" value="${empleado[0].apePaterno }"/>
						<c:set var="empleadoApeMater" value="${empleado[0].apeMaterno }"/>
						<c:set var="empleadoNomCompleto" value="${empleadoNombre}  ${empleadoApellido} ${empleadoApeMater}"/>
						<c:set var="consulta" value="${consultaBusqueda}"/>
					</c:if>
					
			
					
					
					
					<c:if test="${empleadoBusqueda==null }">
							<c:set var="empleadoNomCompleto" value="No Existe este usuario"/>	
							<c:set var="empleadoId" value=""/>					
					</c:if>
						
			<div>
				<table class=""  border="0" >
					 <tr>
						 <td>
							<span class="etiqueta1 margenIzq_PC"> Ingresar usuario:</span>
							<input type="text" id="ingresarUsuario" name="inputIdUsuario" class="camposPC" value="<c:out value="${empleadoId}"/>" onblur="busquedaDeUsuario(this.value)"></input>
						</td/>
						<td>
							<span class="imagenInputPC fBuscaUsuario"></span>
							<div class ="imagCampoPC"></div>
						</td>
						
						<c:if test="${empleado==null }">
							<c:set var="empleadoNomCompleto" value="No Existe este usuario"/>							
						</c:if>
						
						<td>
							<span class="etiqueta1 margenIzq_PC"> Nombre usuario:</span>
							
							<input type="text" id="nombreUsuario" class="camposPC_NU campoObligatorio"  value="<c:out value="${empleadoNomCompleto}"/>"  readonly>
						</td>
						 
					 </tr>
				</table>
			</div>
			
			<!-- Perfil consulta -->
			<div class="separacionElementos">
				<table   class="radio-group" border="0" style="margin: 0 auto;">
					 <tbody>
						 <tr>
						 	<c:if test="${tipEmp == 'RH'}">
							<td><input type="checkbox" id="consInt" name="addreess" onclick="checkModal();estatusActivo();"/>
								<!-- <label for="consInt"></label> --></td>							
							<td ><label class="textoRadio_PC">Consulta Internos</label></td>
							</c:if> 
							<td><input type="checkbox" id="consExt" name="addreess" onclick="checkModal();estatusActivo();"/>
								<!-- <label for="consExt"></label> --></td>
							<td><label class="textoRadio_PC">Consulta externos</label></td>
							
							<td><input type="checkbox" id="consQuit" name="addreess" onclick="checkModal();estatusInactivo();"/>
								<!-- <label for="consQuit"></label> --></td> 
							<td><label class="textoRadio_PC">Quitar perfil de consulta</label></td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<!-- Fin -->
				<!-- Fin -->
				<form name="formsubir" action="<%=blobstoreService.createUploadUrl("/uploadusuario") %>" method="post" enctype="multipart/form-data">
			<input type="file" id="upload" class="" accept=".txt" style="display:none" name="myFile" onchange="cargaArchivo()"/> 
			<div width="337px" align="center" style="margin-top: 30px">
					<input class="examinar" id="iptExaminar" readonly>
			</div>
			<div class="boton" align="center" style="margin-top: 15px">
				<a href="" id="upload_link" class="cargaUsuario" name="myFile">Cargar usuarios</a>	
			</div>
		
		<div class="boton botonesDerecho muestraReposo noMostrar">		
			<table>
				<tr>
					<td><a id="mostrarModal" class="reposo" name="Submit" href="javascript:generar();">Aceptar</a> </td>
				<td><a href="menuv1.html" class="reposo" ><label>Cancelar</a></td>
				</tr>
			</table>
		</div>
		

		<div class="boton  muestraDeshabilitado" style="margin-left:615px">	
			<table>
				<tr>
					<!--  <td><a class="deshabilitado" id="aceptarConsulta" >Aceptar</a> </td>-->
					<td>
					<label class="labelBotonEnviar" for="aceptarConsulta" style="margin-left:120px" >Enviar</label>
					<button id="aceptarConsulta" class="doubleBoton" disabled onclick ="generar()">Enviar</button>
						
					</td>
					<td><a href="menuv1.jsp" class="reposo" style="position:relative;top:-11px;" >Cancelar</a></td>
				</tr>
			</table>
			<input type="hidden" value="" name="tipoConsulta" id="hidTipoConsulta"></input>
			<input type="hidden" value="<c:out value="${empleadoId}"/>"  name="idUsuarioHid"></input>
		</div>
    </div>
    </form>
<!-- Final Contenido -->
</div>

<!-- Inicio Modal  -->
<div id="modalAviso" style="display: none;">
	<span class="cerrarModal" id="modalCerrar"></span>
	<label class="tituloModal">Aviso</label>
	<div class="contenidoModal">
		<label class="informacionModal">Se ha guardado el perfil de consulta.</label>
	</div>			
	<div class="boton contenidoBoton">
		<a class="reposo" href="index.html">Aceptar</a>
	</div>
</div>
<!-- Fin Modal  -->				
					<c:if test="${fn:length(consulta) gt 0}">
						<script type="text/javascript">
						
						 var tipoConsulta = "<c:out value="${consulta[0].tipoConsulta}"/>";
							window.onload = radioClick(tipoConsulta);
						</script>
					</c:if>
</body>
</html>