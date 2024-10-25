<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form action="SoftwareServlet" method="post">
    <label for="softwareName">Software Name:</label>
    <input type="text" id="softwareName" name="softwareName" required><br>
    
    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>
    
    <label for="accessLevels">Access Levels:</label><br>
    <input type="checkbox" name="accessLevels" value="Read"> Read<br>
    <input type="checkbox" name="accessLevels" value="Write"> Write<br>
    <input type="checkbox" name="accessLevels" value="Admin"> accept<br>
    
    <input type="submit" value="Create Software">
</form>
