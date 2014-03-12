<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>

<form action="<%= BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/upload") %>" 
	method="post" enctype="multipart/form-data">
    <label>President's name :   </label> <input type="text" name="president-name"/>
    <label>President's picture : </label> <input type="file" name="president-pic"/>
    <input type="submit"/>
</form>