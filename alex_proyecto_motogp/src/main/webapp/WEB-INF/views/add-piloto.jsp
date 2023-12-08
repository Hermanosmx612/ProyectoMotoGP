<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">
	<p style="color: red;">${error}</p>

	<h1>Add piloto<%-- <spring:message code="alumno.nuevo.titulo" /> --%>:</h1>

	<p>Nuevo Piloto<%-- <spring:message code="alumno.nuevo.explicacion" /> --%>:</p>
	<mvc:form action="add-alumno" method="post" modelAttribute="corredorEdit">
		<mvc:errors path="*" cssClass="text-warning"></mvc:errors>
		<div class="form-row">
			<div class="col">
				<mvc:label path="numLicencia">Numero de licencia<%-- <spring:message code="etiqueta.dni" /> --%>:</mvc:label>

				<mvc:input type="text" path="numLicencia" required="required"
					class="form-control" />
				<mvc:errors path="numLicencia" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="nombre">Nombre<%-- <spring:message code="etiqueta.nombre" /> --%>:</mvc:label>
				<mvc:input type="text" path="nombre" required="required"
					class="form-control" />
				<mvc:errors path="nombre" cssClass="text-warning"></mvc:errors>
			</div>
		<%-- </div>
		<div class="form-row">
			<div class="col">
				<mvc:label path="edad"><spring:message code="etiqueta.edad" />:</mvc:label>
				<mvc:input type="number" path="edad" class="form-control" />
				<mvc:errors path="edad" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="modulo"><spring:message code="etiqueta.ciclo" />:</mvc:label>
				<mvc:input type="text" path="modulo" class="form-control" />
				<mvc:errors path="modulo" cssClass="text-warning"></mvc:errors>
			</div>
			<div class="col">
				<mvc:label path="curso"><spring:message code="etiqueta.curso" />:</mvc:label>
				<mvc:input type="number" path="curso" class="form-control" />
				<mvc:errors path="curso" cssClass="text-warning"></mvc:errors>

			</div>
		</div>
		<br>
		<div class="form-row">
			<div class="col">
				<mvc:checkbox path="erasmus" />
				<mvc:label path="erasmus"><spring:message code="etiqueta.erasmus" /></mvc:label>
			</div>
			<div class="col">
				<mvc:label path="interesadoEn"><spring:message code="etiqueta.interesado" />:</mvc:label>
				</br>
				<mvc:checkboxes path="interesadoEn" items="${interesadoEnLista}" />

			</div>
			<div class="col">
				<mvc:label path="lenguajeFavorito"><spring:message code="etiqueta.lenguaje.favorito" />:</mvc:label>
				<mvc:checkbox path="lenguajeFavorito" value="Java" />
				Java
			</div>
		</div>

		<div class="form-row">
			<div class="col">
				<mvc:label path="genero"><spring:message code="etiqueta.genero" />:</mvc:label>
				<br />
				<mvc:radiobuttons path="genero" items="${listGeneros}" element="p" />
			</div>
			<div class="col">
				<mvc:label path="horario"><spring:message code="etiqueta.horario" />:</mvc:label>
				<mvc:select path="horario" size="1">
					<mvc:options items="${listarHorario}"></mvc:options>
				</mvc:select>
			</div>
			<div class="col">
				<mvc:label path="pais"><spring:message code="etiqueta.pais" />:</mvc:label>
				<mvc:select path="pais">
					<mvc:option value="-" label="Ninguno"></mvc:option>
					<mvc:options items="${listaPaises}"></mvc:options>

				</mvc:select>
			</div>
			<div class="col">

				<mvc:label path="matriculadoEn"><spring:message code="etiqueta.matriculado" />:</mvc:label>
				<br>

				<mvc:select path="matriculadoEn">


					<mvc:options items="${moduloLista}" itemValue="id"
						itemLabel="nombre"></mvc:options>

				</mvc:select>


			</div>

		</div>

		<div class="form-row">

			<div class="col">

				<mvc:label path="hobbies"><spring:message code="etiqueta.hobbies" />:</mvc:label>
				<br>

				<mvc:textarea path="hobbies" rows="3" cols="70"></mvc:textarea>


			</div>
		</div> --%>
		</div>
		<input type="submit" path="A�adir" class='btn btn-success' />
	</mvc:form>





</div>



<%@ include file="../jspf/footer.jspf"%>