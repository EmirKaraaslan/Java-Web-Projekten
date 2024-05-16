<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
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
            <a href="https://bjk.com.tr/tr" class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <a href="${pageContext.request.contextPath}/new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.country}" /></td>
                    <td>
                        <button type="button" class="btn btn-primary" onclick="editForm(${user.id})">Edit</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/delete?id=${user.id}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit User</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modalBodyContent">
                <!-- Dynamic content will be loaded here -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
    function editForm(userId) {
        $.ajax({
            type: "GET",
            url: "${pageContext.request.contextPath}/edit?id=" + userId,
            success: function(response) {
                // Load the response into the modal body
                $('#modalBodyContent').html(response);
                // Show the modal
                $('#editModal').modal('show');
            },
            error: function(xhr) {
                alert("Error: " + xhr.status + " " + xhr.statusText);
            }
        });
    }
</script>

</body>
</html>
