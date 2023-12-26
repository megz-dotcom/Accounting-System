<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add/Edit Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: SlateBlue">
            <div>
                <a href="index.jsp" class="navbar-brand"> Accounting System </a>
            </div>
            <ul class="navbar-nav">
                <li><a href="EmployeeWeb?action=list" class="nav-link">Employees</a></li>
            </ul>
        </nav>
    </header>

    <br>

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="EmployeeWeb?action=${employee.id != null ? 'edit' : 'add'}" method="POST" onsubmit="return validateForm()">
                    <input type="hidden" name="id" value="${employee.id}">
                    
                    <caption>
                        <h2>${employee.id != null ? "Edit Employee" : "Add New Employee"}</h2>
                    </caption>

                    <fieldset class="form-group">
                        <label>Employee Name</label>
                        <input type="text" placeholder="Enter Employee name" value="<c:out value='${employee.name}'/>" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Bank Type</label>
                        <select class="form-control" name="bank" required="required">
                            <option value="">select bank</option>
                            <option value="ICICI" ${employee.bank == 'ICICI' ? 'selected' : ''}>ICICI</option>
                            <option value="HDFC" ${employee.bank == 'HDFC' ? 'selected' : ''}>HDFC</option>
                            <option value="SBI" ${employee.bank == 'SBI' ? 'selected' : ''}>SBI</option>
                            <option value="KARNATAKA-BANK" ${employee.bank == 'KARNATAKA-BANK' ? 'selected' : ''}>KARNATAKA BANK LTD</option>
                        </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>IFSC code</label>
                        <input type="text" placeholder="Enter IFSC code" value="<c:out value='${employee.ifsc}' />" class="form-control" name="ifsc" required="required">
                    </fieldset>
                    
                    <fieldset class="form-group">
                        <label>Account Number</label>
                        <input type="text" placeholder="Enter Account Number" value="<c:out value='${employee.account}' />" class="form-control" name="account" required="required">
                    </fieldset>
                    
                    <fieldset class="form-group">
                        <label>Amount Paid</label>
                        <input type="text" placeholder="Amount Paid" value="<c:out value='${employee.amount}' />" class="form-control" name="amount" required="required">
                    </fieldset>
                    
                    <fieldset class="form-group">
                        <label>Employee Payment Status</label>
                        <select class="form-control" name="status" required="required">
                            <option value="">select status</option>
                            <option value="Pending" ${employee.status == 'Pending' ? 'selected' : ''}>Pending</option>
                            <option value="Paid" ${employee.status == 'Paid' ? 'selected' : ''}>Paid</option>
                        </select>
                    </fieldset>

                    <div class="d-grid gap-2 col-4 mx-auto">
                        <button type="submit" class="btn btn-primary">${employee.id != null ? "Update Employee" : "Add Employee"}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        function validateForm() {
            var ifscCode = document.forms[0].ifsc.value;
            var accountNumber = document.forms[0].account.value;
            var ifscRegex = /^[A-Za-z]{4}\d{7}$/;
            var accountRegex = /^\d{9,18}$/;

            if (!ifscRegex.test(ifscCode)) {
                alert("Please enter a valid IFSC code.");
                return false;
            }

            if (!accountRegex.test(accountNumber)) {
                alert("Please enter a valid account number.");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
