<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>



<table border="1" cellpadding="8" cellspacing="0">
    <c:forEach items="${resultObject}" var="numbers">
        <jsp:useBean id="numbers" scope="page" type="ru.dsoccer1980.lottery_adviser.model.Numbers"/>
        <tr>
            <td>${numbers.drawNumber}</td>
            <td>${numbers.number1}</td>
            <td>${numbers.number2}</td>
            <td>${numbers.number3}</td>
            <td>${numbers.number4}</td>
            <td>${numbers.number5}</td>
            <td>${numbers.number6}</td>
            <td><a href="/deleteDraw/id/${numbers.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

Add numbers of new draw
<form action="/addNumbers" method="post">
    Draw number<input type="text" name="draw_number"> <br>
    Number1<input type="text" name="number1"> <br>
    Number2<input type="text" name="number2"> <br>
    Number3<input type="text" name="number3"> <br>
    Number4<input type="text" name="number4"> <br>
    Number5<input type="text" name="number5"> <br>
    Number6<input type="text" name="number6"> <br>
    <input type="submit" name="submit" value="submit">
</form>


</body>




