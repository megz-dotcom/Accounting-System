<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trainee List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
     
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
   
    <script>
       $(document).ready(function () {
          $('#example').DataTable();
        });
    </script>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: SlateBlue">
        <div>
            <a href="index.jsp" class="navbar-brand"> Accounting System </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="TraineeWeb?action=list" class="nav-link">Trainees</a></li>
        </ul>
    </nav>
</header>

<br>

<div class="container">
    <h3 class="text-center">List of Trainees</h3>
    <hr>
    <div class="container text-left">
        <a href="TraineeWeb?action=add" class="btn btn-success">Add New Trainee</a>
    </div>
    <br>
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Workshop</th>
            <th>Course</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Percent</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trainees}" var="trainee">
            <tr>
                <td><c:out value="${trainee.id}" /></td>
                <td><c:out value="${trainee.name}" /></td>
                <td><c:out value="${trainee.workshop}" /></td>
                <td><c:out value="${trainee.course}" /></td>
                <td><c:out value="${trainee.startDate}" /></td>
                <td><c:out value="${trainee.endDate}" /></td>
                <td><c:out value="${trainee.percent}" /></td>
                <td><c:out value="${trainee.amount}" /></td>
                <td><c:out value="${trainee.status}" /></td>
                <td>
                    <a href="TraineeWeb?action=edit&id=${trainee.id}" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                    <a href="TraineeWeb?action=delete&id=${trainee.id}" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
