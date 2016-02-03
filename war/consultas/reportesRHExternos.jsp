<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>
	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />

</head>

<body>
<div class="contenedor">
	
	<!-- Inicia Encabezado -->
	<div class="cabecera">
		<a href="#" class="btnSalir"></a>
		<div class="cabCliente">
			<h2>JOSÉ DOROTEO ARANGO ARAMBULA</h2>
		</div>
		<!--<a href="img_personalizada.html" class="imgCliente" title="Imagen personalizada">--><img src="config/img/Ic_Usuario.png"><!--</a>-->
		<span class="cabTitulo">Horarios Escalonados</span>
	</div>
	<!-- Final Encabezado -->
	 
	<!-- Inicia Menu -->
	<div class="imgAdm">
		<div class="menuPrincipal">
				<ul class="home">
					<li  >
						<a id="btnHomeApagado" class="btnHome homeApagado"  href="index.html"></a>
					</li>
					<li class="menu horario centro ">
						<a href="reportesRHInternos.html" class="estiloMenu" >
							<span class="texto">Internos</span>
						</a>
					</li>
					<li class="menu adm centro horarioAdm">
						<a href="reportesRHExternos.html" class="estiloMenu">
							<span class="texto">Externos</span>
						</a>						
					</li>
				</ul>
		</div>
	</div>	
	<!-- Final Menu -->
	
	<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMigaAdm"></span>
					</li>
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Reportes - RRHH - Externos</span>
					</li>
				</ul>				
	</div>

	
	<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
				<table width="100%" border="0" class="centro">
				 <tbody>
					 <tr>
						<td><a href="repDiaRHExternos.jsp"><img src="img/Bt_PorDia.png"></a>
							<a href="repSemanaRHExternos.jsp" class="consultaRepo"><img src="img/Bt_PorSemana.png"></a>
							<a href="repMesRHExternos.jsp"><img src="img/Bt_PorMes.png"></a>
						</td>
					
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<!-- Final Contenido -->


</div>
</body>
</html>