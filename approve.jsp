<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form action="RequestServlet" method="post">
    <label for="softwareName">Software Name:</label>
    <select id="softwareName" name="softwareName">
        <!-- Dynamically populate with available software -->
    </select><br>
    
    <label for="accessType">Access Type:</label>
    <select id="accessType" name="accessType">
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Accept</option>
    </select><br>
    
    <label for="reason">Reason for Request:</label>
    <textarea id="reason" name="reason" required></textarea><br>
    
    <input type="submit" value="Request Access">
</form>
    