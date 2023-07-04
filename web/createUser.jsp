<%-- 
    Document   : createUser
    Created on : Jun 5, 2023, 8:59:35 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@page import="longvu.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
        <!--CSS-->
        <link rel="stylesheet" href="css/createUserStyle.css">
        <!--Boxicons CSS-->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <section class="container forms">
            <div class="form signup">
                <div class="form-content">
                    <header>Signup</header>

                    <form action="MainController" method="POST" id="form">
                        <div class="field input-field">
                            <input type="text" name="username" value="" 
                                   placeholder="Username" class="input" required="">
                        </div>

                        <div class="field input-field">
                            <input type="text" name="fullName" value="" 
                                   placeholder="Fullname" class="input" required="">
                        </div>
                        
                        <div class="field input-field">
                            <input type="text" name="email" value="" 
                                   placeholder="Email (Optional)" class="input">
                        </div>
                        
                        <div class="field input-field">
                            <input type="text" name="roleId" value="US" 
                                   placeholder="Role ID" class="input" readonly="">
                        </div>

                        <div class="field input-field">
                            <input type="password" name="password" value="" 
                                   placeholder="Password" class="password" required="">
                        </div>

                        <div class="field input-field">
                            <input type="password" name="confirm" value="" 
                                   placeholder="Confirm password" class="password" required="">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>
                        
                        <div class="g-recaptcha recaptcha-box" 
                             data-sitekey="6Ld66IgmAAAAAL9cKR5k5HJgsCHBYrM3cZkOKc5c"></div>
                        <div id="error"></div>

                        <!--Store the value of btAction-->
                        <input type="hidden" name="btAction" value="Create" 
                               readonly="" class="metadata"  />
                        <div class="field button-field">
                            <button type="submit">
                                Signup
                            </button>
                        </div>
                    </form>

                    <div class="form-link">
                        <span>Already have an account?
                            <a href="MainController?login.html" class="signup-link">
                                Login
                            </a>
                        </span>
                    </div>
                </div>
        </section>
        <!--Javascript-->
        <!--reCaptcha script-->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("form");
                const error = document.getElementById("error");
                
                form.addEventListener("submit", function () {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML = "Please check the reCaptcha box!";
                    }
                });
            }
        </script>
        <script src="js/createUserScript.js"></script>
    </body>
</html>
