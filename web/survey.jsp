 <link rel="stylesheet" href="styles/main.css">
<h1>Thanks for taking our survey!</h1>

<p>Here is the information that you entered:</p>
<label>Email:</label>
<span>${user.email}</span><br>
<label>First Name:</label>
<span>${user.firstName}</span><br>
<label>Last Name:</label>
<span>${user.lastName}</span><br>

<label>Heard From:</label>
<span>${user.heardFrom}</span><br>
<label>Updates:</label>
<span>${user.wantsUpdates}</span><br>

<p>Email cookie value: ${cookie.SuseridCookie.value}</p>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user.wantsUpdates == 'Yes'}">
<label>contact Via:</label>
<span>${user.contactVia}</span><br>
</c:if>
 

 

 