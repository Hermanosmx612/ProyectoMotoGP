<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<p style="color: red;">${error}</p>
	<p style="color:red">${errores}</p>
	<h1>Nueva Moto<%-- <spring:message code="modulo.nuevo.titulo" /> --%>:</h1>
	<mvc:form action="add-moto" method="post" enctype="multipart/form-data" modelAttribute="moto">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="id">Id<%-- <spring:message code="etiqueta.nombre" /> --%>:</mvc:label>
				<mvc:input type="text" path="id" readonly="true" class="form-control" />
			</div>
		</div>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="nombre">Nombre:<%-- <spring:message code="etiqueta.horas" /> --%>:</mvc:label>
				<mvc:input type="text" path="nombre" class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nacionalidad">Nacionalidad<%-- <spring:message code="etiqueta.abreviatura" /> --%>:</mvc:label>
				<mvc:input type="text" path="nacionalidad" class="form-control" />
				<mvc:errors path="nacionalidad" cssClass="text-warning"></mvc:errors>
			</div>
		</div>
		</br>
		</br>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="anoFundacion">Año Fundacion:<%-- <spring:message code="etiqueta.horas" /> --%>:</mvc:label>
				<mvc:input type="number" path="anoFundacion" class="form-control" />
				<mvc:errors path="anoFundacion" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="imagenMoto">Imagen Moto<%-- <spring:message code="etiqueta.abreviatura" /> --%>:</mvc:label>
				<mvc:input type="file" path="imagenMoto"/>
				<mvc:errors path="imagenMoto" cssClass="text-warning"></mvc:errors>
			</div>
		</div>
		<button type="submit" class="btn btn-success"><i class="fas fa-plus-circle"></i></i>&nbsp;Enviar<%-- <spring:message code="boton.anyadir" /> --%></button>
	</mvc:form>





</div>

<%@ include file="../jspf/footer.jspf"%>