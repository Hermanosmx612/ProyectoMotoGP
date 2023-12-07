<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>
<div class="container">
	<h1><%-- <spring:message code="alumnos.titulo" /> --%>Lista corredores:</h1>
	<%-- <p>Bienvenido ${nickname}</p> --%>
	<p style="color:red">${errores}</p>
	<%-- <mvc:form method="post" action="filtrar-alumno" modelAttribute="filtroAlumno">
		<div class="form-row">
			<div class="col">
				<mvc:label path="campo"><spring:message code="etiqueta.opciones" />Filtrar:</mvc:label>
				</br>
				<mvc:select path="campo">
					<mvc:options items="${campoLista}"></mvc:options>
				</mvc:select>
			</div>
			<div class="col">
				<mvc:label path="valor"><spring:message code="etiqueta.valor" />:</mvc:label> </br>
				<mvc:input type="text" path="valor" required="required"/>
				<button path="Buscar" type="submit" class="btn btn-success"><i class="fas fa-search"></i>&nbsp;</i>&nbsp;<spring:message code="boton.filtrar" /></button>
				
			</div>
		</div>
		


	</mvc:form> --%>
	</br> </br>

	<table class="table table-stripped">
		<thead>
			<th><a class="nav-link " href="list-alumno?orden=nombre">Id<%-- <spring:message code="etiqueta.nombre" /> --%>
			</a></th>
			<th><a class="nav-link " href="list-alumno?orden=dni">Nombre<%-- <spring:message code="etiqueta.dni" /> --%></a></th>


			<th><a class="nav-link " href="list-alumno?orden=edad">Dorsal<%-- <spring:message code="etiqueta.edad" /> --%>
			</a></th>

			<th><a class="nav-link " href="list-alumno?orden=modulo">Nacionalidad<%-- <spring:message code="etiqueta.ciclo" /> --%>
			</a></th>
			
			<th><a class="" href="#">Acciones </a></th>
		<thead>
			<c:forEach items="${corredoresList}" var="corredor">
				<tr>
					<td>${corredor.getId()}</td>
					<td>${corredor.getNombre()}</td>
					<td>${corredor.getDorsal()}</td>
					<td>${corredor.getNacionalidad()}</td>
					<%-- <td><input type="checkbox" ${alumno.getErasmusChecked()}
						disabled="disabled"></td>
					<td>${alumno.getModificado()}</td> --%>
					
					<%-- <td><a class="btn btn-success"
						href="update-alumno?update=${alumno.dni}">Actualizar<spring:message code="boton.modificar" /></a></td>
					<td><a class="btn btn-danger"
						href="del-alumno?alumno=${alumno.dni}">Borrar<spring:message code="boton.borrar" /></a></td>
					<td><a class="btn btn-success"
						href="doc-alumno?alumno=${alumno.dni}">Agregar Documento<spring:message code="boton.documentacion" /></a></td> --%>
				</tr>
			</c:forEach>
	</table>



<p>	<a  class="btn btn-success" href="add-alumno"><i class="fas fa-user-plus"></i>&nbsp;Nuevo<%-- <spring:message code="boton.nuevo" /> --%></a></p></div>





<%@ include file="../jspf/footer.jspf"%>