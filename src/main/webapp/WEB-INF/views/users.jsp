<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .modal { display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.4); }
        .modal-content { background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 50%; }
    </style>
</head>
<body>
<h1>User Management</h1>

<h2>Add New User</h2>
<form action="add" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    Name: <input type="text" name="name" required>
    Email: <input type="email" name="email" required>
    Age: <input type="number" name="age" required>
    <button type="submit">Add</button>
</form>

<h2>User List</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>
                <!-- Кнопка "Edit" (открывает модальное окно) -->
                <button onclick="openEditModal(${user.id}, '${user.name}', '${user.email}', ${user.age})">Edit</button>

                <!-- Форма для удаления -->
                <form action="delete" method="post" style="display: inline;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Модальное окно для редактирования -->
<div id="editModal" class="modal">
    <div class="modal-content">
        <h2>Edit User</h2>
        <form action="update" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" id="editId" name="id">
            Name: <input type="text" id="editName" name="name" required><br>
            Email: <input type="email" id="editEmail" name="email" required><br>
            Age: <input type="number" id="editAge" name="age" required><br>
            <button type="submit">Save</button>
            <button type="button" onclick="closeEditModal()">Cancel</button>
        </form>
    </div>
</div>

<script>
    // Открыть модальное окно и подставить данные пользователя
    function openEditModal(id, name, email, age) {
        document.getElementById('editId').value = id;
        document.getElementById('editName').value = name;
        document.getElementById('editEmail').value = email;
        document.getElementById('editAge').value = age;
        document.getElementById('editModal').style.display = 'block';
    }

    // Закрыть модальное окно
    function closeEditModal() {
        document.getElementById('editModal').style.display = 'none';
    }
</script>
</body>
</html>