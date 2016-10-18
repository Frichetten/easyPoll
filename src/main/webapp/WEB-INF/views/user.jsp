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
<<<<<<< HEAD
        <td><form:input type="password" path="password" /></td>
=======
        <td><form:input path="password" type="password"/></td>
>>>>>>> d0233402a8665f3b09169e62a5aedd9689469ced
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