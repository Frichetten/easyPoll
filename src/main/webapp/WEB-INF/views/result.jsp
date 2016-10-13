<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>easyPoll Alpha</title>
</head>
<body>

<h2>Submitted User Information</h2>
   <table>
    <tr>
        <td>Name </td>
        <td style="padding-left: 10px">${username}</td>
    </tr>
    <tr>
        <td>Password </td>
        <td style="padding-left: 10px">${password}</td>
    </tr>
</table>  
</body>
</html>