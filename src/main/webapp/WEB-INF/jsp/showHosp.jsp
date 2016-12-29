<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>医院信息列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 引入 Bootstrap -->
        <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>
    <div class="container">
        	<div class="row clearfix">
        		<div class="col-md-12 column">
        	<c:if test="${!empty hospitalDetialList}">
        		<table class="table">
        			<c:forEach var="user" items="${hospitalDetialList}">
        				<thead>
        					<tr>
        						<th>
        						    地址
        						</th>
        						<th>
        							级别
        						</th>
        						<th>
        							医院名称
        						</th>
        						<th>
                                   性质
                                </th>
                                <th>
                                   电话
                                </th>
                                <th>
                                  类型
                                </th>
        					</tr>
        				</thead>
        				<tbody>
        					<tr class="success">
        						<td>
        							${user.address}
        						</td>
        						<td>
        							${user.level}
        						</td>
        						<td>
        							${user.hospitalname}
        						</td>
        						<td>
                                    ${user.quality}
                                </td>
                                <td>
                                    ${user.tel}
                                </td>
                                <td>
                                    ${user.type}
                                 </td>
        					</tr>
        				</tbody>
        			</c:forEach>
        		</table>
        	</c:if>
        		</div>
        	</div>
    </div>
</body>
</html>
