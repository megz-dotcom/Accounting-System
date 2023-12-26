<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>

                        <form method="post" action="register" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name" required />
                            </div>
	                            <div class="form-group">
							    <label for="email"><i class="zmdi zmdi-email"></i></label>
							    <input type="email" name="email" id="email" placeholder="Your Email" required />
							    <% String emailError = (String) request.getAttribute("emailError");
							    if (emailError != null) { %>
							        <span style="color: red;"><%= emailError %></span>
							    <% } %>
							</div>
	
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" required />
                                <span id="password-error" style="color: red; display: none;">Passwords do not match the criteria</span>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password" required />
                                <span id="confirm-password-error" style="color: red; display: none;">Passwords do not match</span>
                            </div>
                            <div class="form-group">
                                <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="contact" id="contact" placeholder="Contact no" required />
                                <span id="contact-error" style="color: red; display: none;">Invalid contact number</span>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term">
                                    <span><span></span></span>
                                    I agree all statements in <a href="#" class="term-service">Terms of service</a>
                                </label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure>
                            <img src="images/signup-image.jpg" alt="sign up image">
                        </figure>
                        <a href="login.jsp" class="signup-image-link">I am already a member</a>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">

    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "success") {
            swal("Congrats", "Account Created Successfully", "success");
        }

        var password = document.getElementById("pass");
        var confirmPassword = document.getElementById("re_pass");
        var contact = document.getElementById("contact");
        var passwordError = document.getElementById("password-error");
        var confirmError = document.getElementById("confirm-password-error");
        var contactError = document.getElementById("contact-error");
        var email = document.getElementById("email");
        var emailError = document.getElementById("email-error");

        function validateEmail() {
			var email = document.getElementById("email");
			var emailError = document.getElementById("email-error");
			var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!regex.test(email.value)) {
				emailError.style.display = "block";
				return false;
			} else {
				emailError.style.display = "none";
				return true;
			}
		}

        function validatePassword() {
            var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
            if (!regex.test(password.value)) {
                passwordError.style.display = "block";
                return false;
            } else {
                passwordError.style.display = "none";
                return true;
            }
        }

        function validateConfirmPassword() {
            if (password.value !== confirmPassword.value) {
                confirmError.style.display = "block";
                return false;
            } else {
                confirmError.style.display = "none";
                return true;
            }
        }

        function validateContact() {
            var regex = /^[0-9]{10}$/;
            if (!regex.test(contact.value)) {
                contactError.style.display = "block";
                return false;
            } else {
                contactError.style.display = "none";
                return true;
            }
        }

        document.getElementById("register-form").addEventListener("submit", function(event) {
            if (!validatePassword() || !validateConfirmPassword() || !validateContact() || !validateEmail()) {
                event.preventDefault();
            }
        });
    </script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
