<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add/Edit Trainee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="TraineeWeb?action=${trainee.id != null ? 'edit' : 'add'}" method="POST" onsubmit="return validateForm()">
                    <input type="hidden" name="id" value="${trainee.id}">
                    
                    <caption>
                        <h2>${trainee.id != null ? "Edit Trainee" : "Add New Trainee"}</h2>
                    </caption>

                    <fieldset class="form-group">
                        <label>Trainee Name</label>
                        <input type="text" placeholder="Enter your name" value="<c:out value='${trainee.name}'/>" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Workshop</label>
                        <select class="form-control" name="workshop" required="required">
                            <option value="">Select Workshop</option>
                            <option value="Personal and Professional Development" <c:if test="${trainee != null && trainee.workshop eq 'Personal and Professional Development'}">selected</c:if>>Personal and Professional Development</option>
                            <option value="Technical Development" <c:if test="${trainee != null && trainee.workshop eq 'Technical Development'}">selected</c:if>>Technical Development</option>
                        </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Course Name</label>
                        <input type="text" placeholder="Course Name" value="<c:out value='${trainee.course}' />" class="form-control" name="course" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Start Date</label>
                        <input type="date" placeholder="mm/dd/yyyy" value="<c:out value='${trainee.startDate}' />" class="form-control" name="startDate" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>End Date</label>
                        <input type="date" placeholder="mm/dd/yyyy" value="<c:out value='${trainee.endDate}' />" class="form-control" name="endDate" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Percentage Paid</label>
                        <input type="text" placeholder="Percentage Paid" value="<c:out value='${trainee.percent}' />" class="form-control" name="percent" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Amount Paid</label>
                        <input type="text" placeholder="Amount Paid" value="<c:out value='${trainee.amount}' />" class="form-control" name="amount" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Trainee Status</label>
                        <select class="form-control" name="status" required="required">
                            <option value="">Select Status</option>
                            <option value="Pending" <c:if test="${trainee != null && trainee.status eq 'Pending'}">selected</c:if>>Pending</option>
                            <option value="Paid" <c:if test="${trainee != null && trainee.status eq 'Paid'}">selected</c:if>>Paid</option>
                        </select>
                    </fieldset>

                    <div class="d-grid gap-2 col-4 mx-auto">
                        <button type="submit" class="btn btn-primary">${trainee.id != null ? "Update Trainee" : "Add Trainee"}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        function validateForm() {
            var percentagePaid = document.forms[0].percent.value;
            var amountPaid = document.forms[0].amount.value;
            var percentageRegex = /^100$|^(\d{1,2}(\.\d{1,2})?)$/;
            var amountRegex = /^[1-9]\d*$/;

            if (!percentageRegex.test(percentagePaid)) {
                alert("Please enter a valid percentage for Percentage Paid.");
                return false;
            }

            if (!amountRegex.test(amountPaid)) {
                alert("Please enter a valid amount for Amount Paid.");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
