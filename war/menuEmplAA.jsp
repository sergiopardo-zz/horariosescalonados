 <%@page import="java.text.SimpleDateFormat"%>
    <%@ page import="com.google.appengine.api.datastore.Entity"%>
	<%@ page import="java.util.*"%>
	<%@ page import="java.lang.*"%>
    
    <% String sNombre = session.getAttribute("nombreCompleto")==null?"":(String)session.getAttribute("nombreCompleto"); 
    	%>
    

<!-- Inicia Encabezado -->
	<div class="cabecera">
		<a href="#" class="btnSalir"></a>
		<div class="cabCliente">
			<h2><%=sNombre%></h2>
		</div>
		<img src="/config/img/Ic_Usuario.png">
		<span class="cabTitulo">Horarios Escalonados</span>
	</div>
	<!-- Final Encabezado -->
	<%//if (sTipo.equals("SS")||sTipo.equals(" ")){ %>
	<!-- Inicia Menu -->
	<div class="imgPrincipal imgTam">
		<div class="menuPrincipal">
				<ul class="home">
					<li>
						<a class="btnHome" id="btnHomeApagado" href="/MainMenu"></a>
					</li>
					<li class="menu horario centro">
						<a href="#" class="estiloMenu" >
							<span class="icono imgHorario" id="btnHorario"></span>
							<span class="texto">Horarios</span>
						</a>
						
					</li>
					<li class="menu adm centro">
						<a href="#" class="estiloMenu">
							<span class="icono imgAdministracion" id="btnAdministracion"></span>
							<span class="texto">Administración</span>
						</a>
						<ul class="subMenuAdministracionAA">		
							
							<li class="subMenu"><a href="/cargareportes">
								<span class="iconoSubMenuAdm imgAdministracionSubMenu"></span>
								<span class="textoSubMenu">Reportes</span>
							</a></li>
							
						
						</ul>
						
					</li>
				</ul>
		</div>
	</div>	