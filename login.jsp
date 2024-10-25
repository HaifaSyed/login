<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form action="LoginServlet" method="post">
    <label for="username">login:</label>
    <input type="text" id="username" name="username" required><br>
    
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    
    <input type="submit" value="Login">
</form>
