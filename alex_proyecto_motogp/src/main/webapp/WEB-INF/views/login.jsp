<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>


  <div class="container mt-5">
        <p style="color: red">${errores}</p>

        <mvc:form method="post" action="login" modelAttribute="usuario">
            <div class="form-group">
                <mvc:label path="nickname"><spring:message code="etiqueta.usuario" /></mvc:label>
                <mvc:input path="nickname" type="text" class="form-control rounded" />
                <div class="text-warning">
                    <mvc:errors path="nickname" />
                </div>
            </div>

            <div class="form-group">
                <mvc:label path="password"><spring:message code="etiqueta.contrasena"/></mvc:label>
                <mvc:password path="password" class="form-control rounded" />
                <div class="text-warning">
                    <mvc:errors path="password" />
                </div>
            </div>

            <button type="submit" class="btn btn-success rounded-pill"><i class="fas fa-sign-in-alt"></i> <spring:message code="boton.acceder"/></button>
        </mvc:form>
    </div>

    
<%@ include file="../jspf/footer.jspf"%>