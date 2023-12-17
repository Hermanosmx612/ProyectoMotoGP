<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<h1> Lista modulos alumnos</h1>
	<br>
	<table class="table table-stripped">
		<thead>
			<th><a style="color:black" class=" " > <spring:message code="etiqueta.imagenMoto" />
			</a></th>
			<th><a style="color:black" class="nav-link "><spring:message code="etiqueta.id" /></a></th>


			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.nombre" />
			</a></th>

			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.nacionalidad" />
			</a></th>
			
			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.anoFundacion" />
			</a></th>
		<thead>
			
				<tr>
					<td><img src="imagenMotos/${moto.id}" class="rounded-circle" style="width:50px; height:50px; float: left; margin-right: 10px;"></td>
					<td>${moto.id}</td>
					<td>${moto.nombre}</td>
					<td>${moto.nacionalidad}</td>
					<td>${moto.anoFundacion}</td>
					
				</tr>
			
	</table>
	</br>
 	<table class="table table-stripped">
		<thead>
			 <th><a style="color:black" class=" " ><spring:message code="etiqueta.imagenMoto" />
			</a></th>
			
			<th><a style="color:black" class=" " ><spring:message code="etiqueta.numLicencia" />
			</a></th>
			<th><a style="color:black" class="nav-link "><spring:message code="etiqueta.nombre" /></a></th>


			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.dorsal" />
			</a></th>
			
			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.equipo" />
			</a></th>
			
			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.nacionalidad" />
			</a></th>

			<th><a style="color:black" class="nav-link " ><spring:message code="etiqueta.accion" />
			</a></th>
		<thead>
			
				<c:forEach items="${pilotosActuales}" var="actuales">
				<tr>
					<td><img src="imagenPilotos/${actuales.numLicencia}" class="rounded-circle" style="width:50px; height:50px; float: left; margin-right: 10px;"></td>
					<td>${actuales.numLicencia}</td>
					<td>${actuales.nombre}</td>
					<td>${actuales.dorsal}</td>
					<td>${actuales.equipo}</td>
					<td>${actuales.nacionalidad}</td>
					<td><a class="btn btn-danger" href="del-pilotoMoto?numLicencia=${actuales.numLicencia}&idMoto=${moto.id}"><i class="fas fa-trash"></i>&nbsp;<spring:message code="boton.borrar" /></a></td>
					
				</tr>
			</c:forEach>
			
	</table>
	 <p><spring:message code="desc.addPiloto" /></p>
	<mvc:form action="addPilto-moto" method="post" modelAttribute="pilotoMoto">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="numLicencia"><spring:message code="etiqueta.nombres" />:</mvc:label>
				<mvc:select path="numLicencia" size="1">
					<mvc:options items="${pilotosNoActuales}"></mvc:options>

				</mvc:select>
				<button type="submit" class="btn btn-success"><i class="fas fa-user-edit"></i>&nbsp;<spring:message code="boton.enviar" /></button>
			</div>
		<mvc:hidden path="idMoto" />
		<br>
		

	</mvc:form> 
	




</div>

<%@ include file="../jspf/footer.jspf"%>