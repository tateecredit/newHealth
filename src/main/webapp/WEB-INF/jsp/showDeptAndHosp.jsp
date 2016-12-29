<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>预约信息列表</title>
</head>
<body>
    <c:if test="${!empty docBeanList}">
        <c:forEach var="user" items="${docBeanList}">
            医院名称：${user.hospitalName} &nbsp;&nbsp;科室名称：${user.deptName} &nbsp;&nbsp;医生姓名：${user.docName} &nbsp;&nbsp;医生性别：${user.sex} &nbsp;&nbsp;docPosition:：${user.docPosition} &nbsp;&nbsp;orderList:${user.orderList} &nbsp;&nbsp;<br>
        </c:forEach>
    </c:if>
</body>
</html>