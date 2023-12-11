<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>
<div class="container">
	<h1> <spring:message code="titulo.listar.pilotos" />:</h1>
	<%-- <p>Bienvenido ${nickname}</p> --%>
	<p style="color:red">${errores}</p>
	 <mvc:form method="post" action="filtrar-piloto" modelAttribute="filtroPiloto">
		<div class="form-row">
			<div class="col">
				<mvc:label path="campo"> <spring:message code="etiqueta.filtrar" />:</mvc:label>
				</br>
				<mvc:select path="campo">
					<mvc:options items="${campoLista}"></mvc:options>
				</mvc:select>
			</div>
			<div class="col">
				<mvc:label path="valor"><spring:message code="etiqueta.valor" />:</mvc:label> </br>
				<mvc:input type="text" path="valor" required="required"/>
				<button path="Buscar" type="submit" class="btn btn-success"><i class="fas fa-search"></i>&nbsp;<spring:message code="boton.filtrar" /></button>
				
			</div>
		</div>
		


	</mvc:form> 
	</br> </br>

	<table class="table table-stripped">
		<thead>
			 <th><a class="nav-link " href="#"> <spring:message code="etiqueta.imagenMoto" />
		
			<th><a class="nav-link " href="list-corredores?orden=numLicencia"><spring:message code="etiqueta.numLicencia" /> 
			</a></th>
			<th><a class="nav-link " href="list-corredores?orden=nombre"><spring:message code="etiqueta.nombre" /></a></th>


			<th><a class="nav-link " href="list-corredores?orden=dorsal"><spring:message code="etiqueta.dorsal" />
			</a></th>
			
			<th><a class="nav-link " href="list-corredores?orden=equipo"><spring:message code="etiqueta.equipo" /> 
			</a></th>

			<th><a class="nav-link " href="list-corredores?orden=nacionalidad"><spring:message code="etiqueta.nacionalidad" /> 
			</a></th>
			
			<th><a class="" href="#"><spring:message code="etiqueta.accion" /> </a></th>
		<thead>
			<c:forEach items="${corredoresList}" var="corredor">
				<tr>
					<td><img src="imagenPilotos/${corredor.getNumLicencia()}" class="rounded-circle" style="width:50px; height:50px; float: left; margin-right: 10px;"></td>
					<td>${corredor.getNumLicencia()}</td>
					<td>${corredor.getNombre()}</td>
					<td>${corredor.getDorsal()}</td>
					<td>${corredor.getEquipo()}</td>
					<td>${corredor.getNacionalidad()}</td>
					<%-- <td><input type="checkbox" ${alumno.getErasmusChecked()}
						disabled="disabled"></td>
					<td>${alumno.getModificado()}</td> --%>
					
					 <td><a class="btn btn-success"
						href="update-imagenPiloto?numLicencia=${corredor.getNumLicencia()}"><spring:message code="boton.modificarFoto" /></a></td>
				 	<td><a class="btn btn-danger"
						href="del-piloto?piloto=${corredor.getNumLicencia()}"><i class="fas fa-trash-alt"></i>&nbsp;<spring:message code="boton.borrar" /></a></td>
					 <td><a class="btn btn-success"
						href="update-piloto?piloto=${corredor.getNumLicencia()}"><spring:message code="boton.modificar" /></a></td> 
				</tr>
			</c:forEach>
	</table>



<p>	<a  class="btn btn-success" href="add-piloto"><i class="fas fa-user-plus"></i>&nbsp;<spring:message code="boton.nuevo" /></a></p></div>





<%@ include file="../jspf/footer.jspf"%>