<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>easyPoll Alpha</title>
</head>
<body>

<h2>easyPoll Login</h2>
<form:form method="POST" action="/test/addUser">
   <table>
    <tr>
        <td><form:label path="username">Username: </form:label></td>
        <td><form:input path="username" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password: </form:label></td>
        <td><form:input type="password" path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>