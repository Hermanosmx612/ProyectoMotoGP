<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<h1> <spring:message code="titulo.actualizar.piloto" /> :</h1>
	<p style="color: red;">${error}</p>
	<p style="color: red;">${error2}</p>
	

	<mvc:form action="update-piloto" method="post" modelAttribute="corredorEdit">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="numLicencia"><spring:message code="etiqueta.numLicencia" />:</mvc:label>

				<mvc:input type="number" path="numLicencia" required="required"
					class="form-control" min = "111111111" max="999999999"/>
				<mvc:errors path="numLicencia" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nombre"><spring:message code="etiqueta.nombre" />:</mvc:label>
				<mvc:input type="text" path="nombre" required="required"
					class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning"></mvc:errors>
			</div>
			</div>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="dorsal"><spring:message code="etiqueta.dorsal" />:</mvc:label>
				<mvc:input type="number" path="dorsal" class="form-control" min = "1" max = "99"/>
				<mvc:errors path="dorsal" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="equipo"><spring:message code="etiqueta.equipo" />:</mvc:label>
				<mvc:input type="text" path="equipo" class="form-control" />
				<mvc:errors path="equipo" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nacionalidad"><spring:message code="etiqueta.nacionalidad" />:</mvc:label>
				<mvc:input type="text" path="nacionalidad" class="form-control" />
				<mvc:errors path="nacionalidad" cssClass="text-warning"></mvc:errors>

			</div>
		</div>
		<br>
		<br>
		
		<div class="form-row">
			<div class="col">
				<mvc:label path="edad"><spring:message code="etiqueta.edad" />:</mvc:label>
				<mvc:input type="number" path="dorsal" class="form-control" min = "1" max = "99"/>
				<mvc:errors path="edad" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="estadoFisico"><spring:message code="etiqueta.fisico" />:</mvc:label>
				<br />
				<mvc:radiobuttons path="estadoFisico" items="${listaFisico}" element="p" />
			</div>
			<div class="col">
				<mvc:label path="motoActual"><spring:message code="etiqueta.motoActual" />:</mvc:label>
				<mvc:input type="text" path="motoActual" class="form-control" />
				<mvc:errors path="motoActual" cssClass="text-warning"></mvc:errors>

			</div>
			<mvc:hidden path="nombreFicheroConImagen"/>
		</div>
		<input type="submit" path="A�adir" class='btn btn-success' />
		<mvc:hidden path="user"/>
		<mvc:hidden path="ts"/>
	</mvc:form>
</div>






<%@ include file="../jspf/footer.jspf"%>