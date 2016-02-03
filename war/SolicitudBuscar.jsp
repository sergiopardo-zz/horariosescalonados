<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.gonet.horariosescalonados.bean.BeanEmpleado"%>
    <%@ page import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%String empleado = (String)(request.getAttribute("empleadoID")!=null?request.getAttribute("empleadoID"):"");%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Horarios Escalonados</title>

	<link rel="stylesheet" type="text/css" href="config/css/estilo.css" />
	<link rel="stylesheet" type="text/css" href="/config/css/jquery-ui.css" />
	<script type="text/javascript" src="config/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="config/js/acciones.js"></script> 
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	
	<script type="text/javascript">
	function getVal(obj){
		var ret = document.getElementById(obj);
		return ret;
	}
	
	function inicial(){
	}
	
	function searchUser(){
		//getVal("divPopup").style.display="block";
		getVal("flag").value = "1";
		document.frmBusqueda.action="/buscasupervisores";
		document.frmBusqueda.method="post";
		getVal("evento").value="buscar"
		//document.frmBusqueda.submit();
	}

	function enabledAcept(id) {
		var rbSeleccionado = getVal(id);
		if(rbSeleccionado.checked) {
			//getVal("aceptar").disabled = false;
			//getVal("aceptar").setAttribute("class", "botonCandidato");
			var vValoresRb = rbSeleccionado.value;
			var vDatos = vValoresRb.split("|");
			vRegAutSel = vDatos[0];
			vNomAutSel = vDatos[1];
			asignaValores(vRegAutSel, vNomAutSel);
		} else {
			//getVal("aceptar").disabled = true;
			getVal("aceptar").setAttribute("class", "botonCandidatoDsb");
			vRegAutSel = "";
			vNomAutSel = "";
		}
	}
	
	function enableSearchButton() {
		var a = getVal("idUser").value;
		var b = getVal("registerUser").value;
		var c = getVal("nameUser").value;
		var d = getVal("firstNameUser").value;
		var e = getVal("secondNameUser").value;
	
		// Se valida que los campos NO sean sólo espacios
		a = soloEspacios(a) ? "" : a;
		b = soloEspacios(b) ? "" : b;
		c = soloEspacios(c) ? "" : c;
		d = soloEspacios(d) ? "" : d;
		e = soloEspacios(e) ? "" : e;
		/*
		Se habilitará el boton [Buscar] si:
			- sólo se ha capturado el ID del usuario
			- ó sólo se ha capturado el número de registro del empleado
			- ó se ha capturado el Nombre y Apellido Paterno
			- ó se ha capturado el Nombre y Apellido Materno
			- ó se ha capturado el Apellido Paterno y Apellido Materno
		*/
		if(a!="" || b!="" || (c!="" && d!="") || (c!="" && e!="") || (d!="" && e!="")) {
			getVal("buscar").disabled = false;
			getVal("buscar").setAttribute("class", "botonCandidato");
		} else {
			getVal("buscar").disabled = true;
			getVal("buscar").setAttribute("class", "botonCandidatoDsb");			
		}
		// Se convierten los valores de los campos a mayusculas
		getVal("idUser").value = a.toUpperCase();
		getVal("registerUser").value = b.toUpperCase();
		getVal("nameUser").value = c.toUpperCase();
		getVal("firstNameUser").value = d.toUpperCase();
		getVal("secondNameUser").value = e.toUpperCase();
	}

	function limpiaCamposIdUser(){
		if(getVal("idUser").value!=""){
			getVal("registerUser").value = "";
			getVal("nameUser").value = "";
			getVal("firstNameUser").value = "";
			getVal("secondNameUser").value = "";
		}
	}
	function limpiaCamposRegistro(){
		if(getVal("registerUser").value!=""){
			getVal("idUser").value = "";
			getVal("nameUser").value = "";
			getVal("firstNameUser").value = "";
			getVal("secondNameUser").value = "";
		}
	}
	
	function limpiaCamposNombre(){
		if((getVal("nameUser").value!="") || (getVal("firstNameUser").value != "") || (getVal("secondNameUser").value != "")){
			getVal("idUser").value = "";
			getVal("registerUser").value = "";
		}
	}	
	
	function soloEspacios(cadena) {
		var vLongitud = cadena.length;
		var contEspacios = 0;
		
		for(var indice=0; indice<vLongitud; indice++) {
			var caracter = cadena.charAt(indice);
			if(caracter == ' ') {
				contEspacios++;
			}
		}
		
		if(vLongitud == contEspacios)
			return true;
		else
			return false;
	}

	function asignaValores(idUser, nombreUser){	
		getVal("selectedIdUser").value = idUser;
		getVal("selectedNombre").value = nombreUser;		
	}

	function replace(value){
		return value.replace(/\s/g, "_");
	}

	$(document).ready(function(){
		$('#aceptar').click(function(){
			var empleado = "<%=empleado%>"; 
			if(empleado != getVal("selectedIdUser").value){
				frmBusqueda.submit("/supervisorseleccionado");
			}else{
				$("#dialogo1").dialog({
					width: 390,
					height: 250,
					show: "scale",
					hide: "scale",
					resizable: "false",
					position: "center",
					modal: "true"
				});
			}
		});

		$('#cancelar').click(function(){
			getVal("hiddenCancelar").value = "true";
			frmBusqueda.submit("/supervisorseleccionado");						
		});
		
		$('#buscar').click(function(){
			getVal("hiddenIdUser").value = getVal("idUser").value.trim();
			getVal("hiddenRegisterUser").value = getVal("registerUser").value.trim();
			getVal("hiddenNameUser").value = getVal("nameUser").value.trim();
			getVal("hiddenFirstNameUser").value = getVal("firstNameUser").value.trim();
			//alert(getVal("hiddenFirstNameUser").value);
			getVal("hiddenSecondNameUser").value = getVal("secondNameUser").value.trim();
			frmBusqueda.action="/buscasupervisores";
			frmBusqueda.submit();
		});

		$('#aceptar1').click(function(){
			$("#dialogo1").dialog("close");						
		});
	});
</script>
		
</head>
<body>
<div class="contenedor">
<form name="frmBusqueda" action="/supervisorseleccionado" method="post"
		target="_self">
		<input type="hidden" id="hiddenIdUser" name="hiddenIdUser" value="">
		<input type="hidden" id="hiddenRegisterUser" name="hiddenRegisterUser" value=""> 
		<input type="hidden" id="hiddenNameUser" name="hiddenNameUser" value=""> 
		<input type="hidden" id="hiddenFirstNameUser" name="hiddenFirstNameUser" value="">
		<input type="hidden" id="hiddenSecondNameUser" name="hiddenSecondNameUser" value=""> 
		
		<input type="hidden" id="selectedIdUser" name="selectedIdUser" value=""> 
		<input type="hidden" id="selectedNombre" name="selectedNombre" value="">
		
		<input type="hidden" name="hiddenSelectedRadio" id="hiddenSelectedRadio" value="<%=request.getAttribute("hiddenSelectedRadio")%>"> 
		<input type="hidden" name="hiddenIsChecked" id="hiddenIsChecked" value="<%=request.getAttribute("hiddenIsChecked")%>"> 
		<input type="hidden" name="hiddenCancelar" id="hiddenCancelar" value="false">
		<input type="hidden" name="hiddenFrom" id="hiddenFrom" value="<%=request.getAttribute("hiddenFrom")%>">
		<input type="hidden" id="opcion" name="opcion" value="buscar" />
		
		<input type="hidden" id="opcion" name="opcion" value="buscar" />
	
	<!-- Inicia Encabezado -->
<c:set var="tipEmp" scope="session" value="${sessionScope.tipo_empleado}"/>
	<c:if test="${tipEmp == 'SS'}">
	<%@include file="header.jsp"%> 
	</c:if>
		
	<c:if test="${tipEmp == 'AA'}">
	<%@include file="menuEmplAA.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'E'}">
	<%@include file="menuEmplE.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'S'}">
	<%@include file="menuEmplS.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'A'}">
	<%@include file="menuEmplA.jsp"%> 
	</c:if>
	
	<c:if test="${tipEmp == 'C'}">
	<%@include file="menuEmplC.jsp"%> 
	</c:if> 
	<!-- Final Encabezado -->
	 
	<!-- Inicia Contenido -->
	<div class="contenidoMiga">
				<ul class="posicionMiga">	
					<li class="menuMiga">
						<span class="iconoMiga imgMiga"></span>
					</li>
					<li class="menuMiga">
						<span class="separacionMiga">-</span>
						<span class="tituloMiga">Solicitud</span>
					</li>
				</ul>				
	</div>
	
	
<div id="contenido" class="contenido">	
		<div class="contenidoSolicitud">
			
			
			<p class="subTitulos2">
				Búsqueda de usuario autorizador
			</p>
			<div>
				<table  border="0" >
					 <tr>
						 <td>
							<span class="etiqueta2">ID de usuario:</span>
							<input type="text" id="idUser" name="idUser" onKeyUp="limpiaCamposIdUser();" class="camposPC2" value="<%=request.getAttribute("hiddenIdUser")!=null?request.getAttribute("hiddenIdUser"):""%>">
						</td>
						<td>
							<span class="etiqueta2">Nombre:</span>
							<input type="text" onKeyUp="limpiaCamposNombre();" id="nameUser" class="camposPC2" value="<%=request.getAttribute("hiddenNameUser")!=null?request.getAttribute("hiddenNameUser"):""%>">
						</td>
						<td>
							<span class="etiqueta2">Apellido paterno:</span>
							<input type="text" onKeyUp="limpiaCamposNombre();" id="firstNameUser" class="camposPC2" value="<%=request.getAttribute("hiddenFirstNameUser")!=null?request.getAttribute("hiddenFirstNameUser"):""%>" >
						</td>
												 
					 </tr>
					 <tr>
						 <td>
							<span class="etiqueta2">Apellido materno:</span>
							<input type="text" onKeyUp="limpiaCamposNombre();" id="secondNameUser" class="camposPC2" value="<%=request.getAttribute("hiddenSecondNameUser")!=null?request.getAttribute("hiddenSecondNameUser"):""%>">
						</td>
						<td>
							<span class="etiqueta2">Registro:</span>
							<input type="text" onKeyUp="limpiaCamposRegistro();" id="registerUser" class="camposPC2" value="<%=request.getAttribute("hiddenRegisterUser")!=null?request.getAttribute("hiddenRegisterUser"):""%>">
						</td>
																		 
					 </tr>
				</table>
				<div class="boton botonCentrado" >		
			            <a class="reposo" value="Buscar" id="buscar">Búscar</a>
		        </div>
			</div>
			
			
		
            
                <table class="tabla-general contertable">                     
                         <caption>
                             Resultado de la búsqueda
                         </caption>                    
                        <thead>
                            <tr>
                            	<td > </td>
                                <td >ID de usuario</td>
                                <td>Registro</td>
                                <td>Nombre</td>
                                <td>Apellidos</td>										 
                            </tr>
                        </thead>
                        <tbody>
                            <%	
	Iterable<BeanEmpleado> ieAutorizadores = (Iterable)request.getAttribute("listaAutorizadores");
	int iReng1 = 0;
	int flag = 0;
	if(ieAutorizadores!=null){
		for(BeanEmpleado e:ieAutorizadores){
			flag++;
			break;
		}
	}
	
	if(ieAutorizadores!=null && flag>0){
		for(BeanEmpleado eAutorizador:ieAutorizadores) {
			String sClaseTr = iReng1++%2 == 0 ? "tblSeguimientoI1" : "tblSeguimientoI2";
			String sIdUsuario = eAutorizador.getEmpleadoID();
			String sRegUsuario = eAutorizador.getRegistro();
			String snombre = eAutorizador.getNombre();
			String sApellidos =  eAutorizador.getApePaterno()+" "+eAutorizador.getApeMaterno();
			String sNomAutorizador = sApellidos+", "+snombre;%>
							<tr id="tr1">
								<td class="<%=sClaseTr%>"><input type="radio"
									onClick="javascript:enabledAcept(this.id);"
									name="rbAutorizador" id="autorizador<%=iReng1%>"
									value="<%=sIdUsuario%>|<%=sNomAutorizador%>"></td>
								<td class="<%=sClaseTr%>"><%=sIdUsuario%></td>
								<td class="<%=sClaseTr%>"><%=sRegUsuario%></td>
								<td class="<%=sClaseTr%>"><%=snombre%></td>
								<td class="<%=sClaseTr%>"><%=sApellidos%></td>
							</tr>
							<%	
		}// fin del for 		
	}else{	
	%>
	<tr id="tr1">
								<td colspan="5" class="tblSeguimientoI1">No se encontro
									informaci&oacute;n</td>
							</tr>
							<%	}%>
                        </tbody>
                    </table>
                    
				
			<!--<div class="imgResutadoBusqueda"></div>--> 
			
			<div class="boton botonCentrado" >		
			    <a class="reposo" value="Aceptar" id="aceptar">Regresar</a>
		    </div>
			
			
					
		
		</div>
	</div>
	<!-- Final Contenido -->
</div>
<a name="abajo"></a>
</body>
</html>