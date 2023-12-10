<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<p style="color: red;">${error}</p>
	<p style="color:red">${errores}</p>
	<h1> <spring:message code="moto.nuevo.titulo" />:</h1>
	<mvc:form action="add-moto" method="post" enctype="multipart/form-data" modelAttribute="moto">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="id"> <spring:message code="etiqueta.id" />:</mvc:label>
				<mvc:input type="text" path="id" readonly="true" class="form-control" />
			</div>
		</div>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="nombre"> <spring:message code="etiqueta.nombre" /> :</mvc:label>
				<mvc:input type="text" path="nombre" class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nacionalidad"><spring:message code="etiqueta.nacionalidad" /> :</mvc:label>
				<mvc:input type="text" path="nacionalidad" class="form-control" />
				<mvc:errors path="nacionalidad" cssClass="text-warning"></mvc:errors>
			</div>
		</div>
		</br>
		</br>
		 <div class="form-row">
			<div class="col">
				<mvc:label path="anoFundacion"><spring:message code="etiqueta.anoFundacion" />:</mvc:label> <br>
				<mvc:input type="number" path="anoFundacion" class="form-control" />
				<mvc:errors path="anoFundacion" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="imagenMoto"><spring:message code="etiqueta.imagenMoto" />:</mvc:label> 
				<mvc:input type="file" path="imagenMoto"/>
				<mvc:errors path="imagenMoto" cssClass="text-warning"></mvc:errors>
			</div>
		</div>
		<button type="submit" class="btn btn-success"><i class="fas fa-plus-circle"></i></i>&nbsp;<spring:message code="boton.enviar" /></button>
	</mvc:form>





</div>

<%@ include file="../jspf/footer.jspf"%>