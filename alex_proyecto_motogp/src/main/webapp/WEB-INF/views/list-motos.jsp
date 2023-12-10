<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<h1><spring:message code="moto.lista.titulo" /></h1>
	<p style="color: red">${errores}</p> 
	<mvc:form method="post" action="filtrar-moto" modelAttribute="filtroMoto">
		<div class="form-row">
			<div class="col">
				<mvc:label path="campo"> <spring:message code="etiqueta.filtrar" />:</mvc:label>
				</br>
				<mvc:select path="campo">
					<mvc:options items="${campoLista}"></mvc:options>
				</mvc:select>
			</div>
			<div class="col">
				<mvc:label path="valor"> <spring:message code="etiqueta.valor" />:</mvc:label> </br>
				<mvc:input type="text" path="valor" required="required"/>
				<button path="Buscar" type="submit" class="btn btn-success"><i class="fas fa-search"></i>&nbsp;<spring:message code="boton.filtrar" /><%-- <spring:message code="boton.filtrar" /> --%></button>
				
			</div>
		</div>
		


	</mvc:form> 
	</br> </br>
	<table class="table table-stripped">
		<thead>
		<th><a class="nav-link " href="list-motos"><spring:message code="etiqueta.imagenMoto" />
			</a></th>
			<th><a class="nav-link " href="list-motos?orden=id"><spring:message code="etiqueta.id" />
			</a></th>
			<th><a class="nav-link " href="list-motos?orden=nombre"><spring:message code="etiqueta.nombre" />  </a></th>
	

			<th><a class="nav-link " href="list-motos?orden=nacionalidad"><spring:message code="etiqueta.nacionalidad" />
			</a></th>

			<th><a class="nav-link " href="list-motos?orden=fundacion"><spring:message code="etiqueta.anoFundacion" />
			</a></th>

			<th><a style="color:black "class="nav-link" href="#"> <spring:message code="etiqueta.accion" />  </a></th>
		<thead>
		
		<c:forEach items="${listaMotos}" var="moto">
				<tr>
					<td><img src="imagenMotos/${moto.id}" class="rounded-circle" style="width:50px; height:50px; float: left; margin-right: 10px;"></td>
					<td>${moto.id}</td>
					<td>${moto.nombre}</td>
					<td>${moto.nacionalidad}</td>
					<td>${moto.anoFundacion}</td>
					<td><a class="btn btn-danger"
						href="del-moto?idMoto=${moto.id}"><i class="fas fa-trash-alt"></i>&nbsp;<spring:message code="boton.borrar" /></a></td>
				</tr>
			</c:forEach>
			
	</table>



	<a href="add-moto" class="btn btn-success"><i class="fas fa-motorcycle"></i>&nbsp; <spring:message code="boton.nuevo" /></a>
</div>

<%@ include file="../jspf/footer.jspf"%>