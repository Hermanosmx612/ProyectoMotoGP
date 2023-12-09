<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<h1> Lista Motos<%-- <spring:message code="modulos.titulo" /> --%>:</h1>
	<p style="color: red">${errores}</p> 
	<mvc:form method="post" action="filtrar-moto" modelAttribute="filtroMoto">
		<div class="form-row">
			<div class="col">
				<mvc:label path="campo"><%-- <spring:message code="etiqueta.opciones" /> --%>Filtrar:</mvc:label>
				</br>
				<mvc:select path="campo">
					<mvc:options items="${campoLista}"></mvc:options>
				</mvc:select>
			</div>
			<div class="col">
				<mvc:label path="valor">Valor<%-- <spring:message code="etiqueta.valor" /> --%>:</mvc:label> </br>
				<mvc:input type="text" path="valor" required="required"/>
				<button path="Buscar" type="submit" class="btn btn-success"><i class="fas fa-search"></i>&nbsp;Filtrar<%-- <spring:message code="boton.filtrar" /> --%></button>
				
			</div>
		</div>
		


	</mvc:form> 
	</br> </br>
	<table class="table table-stripped">
		<thead>
		<th><a class="nav-link " href="list-modulo?orden=id">Imagen
			</a></th>
			<th><a class="nav-link " href="list-modulo?orden=id">Id
			</a></th>
			<th><a class="nav-link " href="list-modulo?orden=nombre">Nombre<%-- <spring:message code="etiqueta.nombre" /> --%> </a></th>
	

			<th><a class="nav-link " href="list-modulo?orden=hora">Nacionalidad<%-- <spring:message code="etiqueta.horas" /> --%>
			</a></th>

			<th><a class="nav-link " href="list-modulo?orden=abreviatura">Año Fundacion<%-- <spring:message code="etiqueta.abreviatura" /> --%>
			</a></th>

			<th><a style="color:black "class="nav-link" href="#">Acciones<%-- <spring:message code="etiqueta.accion" /> --%> </a></th>
		<thead>
		
		<c:forEach items="${listaMotos}" var="moto">
				<tr>
					<td><img src="imagenMotos/${moto.id}" class="rounded-circle" style="width:50px; height:50px; float: left; margin-right: 10px;"></td>
					<td>${moto.id}</td>
					<td>${moto.nombre}</td>
					<td>${moto.nacionalidad}</td>
					<td>${moto.anoFundacion}</td>
					<td><a class="btn btn-danger"
						href="del-moto?idMoto=${moto.id}"><i class="fas fa-trash-alt"></i>&nbsp;Borrar</a></td>
				</tr>
			</c:forEach>
			
	</table>



	<a href="add-moto" class="btn btn-success"><i class="fas fa-motorcycle"></i>&nbsp;Nuevo<%-- <spring:message code="boton.anyadir" /> --%></a>
</div>

<%@ include file="../jspf/footer.jspf"%>