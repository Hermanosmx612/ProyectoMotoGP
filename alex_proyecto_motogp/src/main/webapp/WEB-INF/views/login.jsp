<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>


  <div class="container mt-5">
        <p style="color: red">${errores}</p>

        <mvc:form method="post" action="login" modelAttribute="usuario">
            <div class="form-group">
                <mvc:label path="nickname">Introduzca usuario:</mvc:label>
                <mvc:input path="nickname" type="text" class="form-control rounded" />
                <div class="text-warning">
                    <mvc:errors path="nickname" />
                </div>
            </div>

            <div class="form-group">
                <mvc:label path="password">Introduzca contrasenya:</mvc:label>
                <mvc:password path="password" class="form-control rounded" />
                <div class="text-warning">
                    <mvc:errors path="password" />
                </div>
            </div>

            <button type="submit" class="btn btn-success rounded-pill"><i class="fas fa-sign-in-alt"></i> Acceder</button>
        </mvc:form>
    </div>

    
<%@ include file="../jspf/footer.jspf"%>