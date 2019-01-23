<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>


<table border="1" cellpadding="8" cellspacing="0">
    <c:forEach items="${resultObject}" var="map">
        <tr>
            <td>${map.key}</td>
            <td>${map.value}</td>
        </tr>
    </c:forEach>
</table>


</body>




