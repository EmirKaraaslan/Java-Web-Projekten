<%@ page language="java" contentType="text/html; charset=UTF-8"

         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/list"
                   class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <script>
                var formAction = "${pageContext.request.contextPath}/insert";
                var formTitle = "Add New User";
                if (${user != null}) {
                    formAction = "${pageContext.request.contextPath}/update";
                    formTitle = "Edit User";
                }
            </script>

            <form id="userForm" action="" method="post">

                <caption>
                    <h2 id="formTitle">${formTitle}</h2>
                </caption>

                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>User Name</label> <input type="text"
                                                    value="" class="form-control"
                                                    name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Email</label> <input type="text"
                                                     value="" class="form-control"
                                                     name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Country</label> <input type="text"
                                                       value="" class="form-control"
                                                       name="country">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>

            <script>
                document.getElementById("userForm").setAttribute("action", formAction);
                document.getElementById("formTitle").innerText = formTitle;
            </script>
        </div>
    </div>
</div>
</body>
</html>
