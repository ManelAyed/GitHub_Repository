<! DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.*" %>
<%@ page import="com.google.common.html.HtmlEscapers" %> <!-- Guava -->
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><body>
	<center>
	<H1>The president is ... </H1> <img src="img/obama.png" />
	
	
<%

    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user == null)
    {%>
        <p>Hello, <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">
        please log in</a> to post comments.</p>
    <%}
    else
    {  
%>
	<form action="/count?counter=pluscounter" method="post">
		<button name="pluscounter">+ 1</button>
		(${pluscounter})
	</form>

	<form action="/count?counter=minuscounter" method="post">
		<button name="minuscounter">- 1</button>
		(${minuscounter})
	</form>

	<form action="/post" method="post">
        	<input type="text" name="user-name" value="<%=user.getNickname()%>" hidden="true">
            <textarea name="user-comment"></textarea><br/>
            <input type="submit" value="OK" /><br/>
            <!-- logout link -->
            <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">logout</a>
        </form>
        
        <c:forEach var="comment" items="${comments}">
			<p><b> ${comment.user}: ${comment.text} </b></p>
		</c:forEach>
		
<% } 
%>
</center>
</body></html>
</html>