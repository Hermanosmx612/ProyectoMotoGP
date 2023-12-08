<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<h1><%-- <spring:message code="usuarioImagen.titulo" /> --%>Actu Imagen:</h1>
	<p>
		<font color="red">${errores}</font>
	</p>
	<img style="width: 150px; height: 150px" src="imagenPilotos/${imagenPiloto.getNumLicencia()}" class="rounded-circle">
	<mvc:form action="guardar-imagen-piloto" method="post" enctype="multipart/form-data" modelAttribute="imagenPiloto">
		<mvc:hidden path="numLicencia"/>
		<mvc:label path="imagen"><%-- <spring:message code="usuarioImagen.explicacion" /> --%></mvc:label></br>
		<mvc:input path="imagen" type="file"/><form:errors path="imagen"/></br>
		<mvc:errors path="imagen" cssClass="text-warning"/>
		<button type="submit" class="btn btn-success"><i class="fas fa-user-edit"></i>&nbsp; Actualizar</button>
	
	</mvc:form>


</div>


<%@ include file="../jspf/footer.jspf"%>