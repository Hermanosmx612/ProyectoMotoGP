<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<h1>Update Piloto<%-- <spring:message code="alumno.nuevo.titulo" /> --%>:</h1>
	<p style="color: red;">${error}</p>

	<mvc:form action="update-piloto" method="post" modelAttribute="piloto">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="numLicencia">Numero de licencia<%-- <spring:message code="etiqueta.dni" /> --%>:</mvc:label>

				<mvc:input type="number" path="numLicencia" required="required"
					class="form-control" min = "111111111" max="999999999"/>
				<mvc:errors path="numLicencia" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nombre">Nombre<%-- <spring:message code="etiqueta.nombre" /> --%>:</mvc:label>
				<mvc:input type="text" path="nombre" required="required"
					class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning"></mvc:errors>
			</div>
			</div>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="dorsal">Dorsal<%-- <spring:message code="etiqueta.edad" /> --%>:</mvc:label>
				<mvc:input type="number" path="dorsal" class="form-control" min = "1" max = "99"/>
				<mvc:errors path="dorsal" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="equipo">Equipo<%-- <spring:message code="etiqueta.ciclo" /> --%>:</mvc:label>
				<mvc:input type="text" path="equipo" class="form-control" />
				<mvc:errors path="equipo" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nacionalidad">Nacionalidad<%-- <spring:message code="etiqueta.curso" /> --%>:</mvc:label>
				<mvc:input type="text" path="nacionalidad" class="form-control" />
				<mvc:errors path="nacionalidad" cssClass="text-warning"></mvc:errors>

			</div>
		</div>
		<br>
		<br>
		
		<div class="form-row">
			<div class="col">
				<mvc:label path="edad">Edad<%-- <spring:message code="etiqueta.edad" /> --%>:</mvc:label>
				<mvc:input type="number" path="dorsal" class="form-control" min = "1" max = "99"/>
				<mvc:errors path="edad" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="estadoFisico">Estado Fisico<%-- <spring:message code="etiqueta.genero" /> --%>:</mvc:label>
				<br />
				<mvc:radiobuttons path="estadoFisico" items="${listaFisico}" element="p" />
			</div>
			<div class="col">
				<mvc:label path="motoActual">Moto Actual<%-- <spring:message code="etiqueta.curso" /> --%>:</mvc:label>
				<mvc:input type="text" path="motoActual" class="form-control" />
				<mvc:errors path="motoActual" cssClass="text-warning"></mvc:errors>

			</div>
			<mvc:hidden path="nombreFicheroConImagen"/>
		</div>
		<input type="submit" path="A�adir" class='btn btn-success' />
	</mvc:form>
</div>






<%@ include file="../jspf/footer.jspf"%>