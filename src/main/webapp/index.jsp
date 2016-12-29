<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>搜索</title>
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
    		<div class="col-md-12 column" style="padding:10px">
    			<form class="form-horizontal" role="form" name="form" onsubmit="return validate_channel_info(this);" method="post" action="/dept/showHospAndDept">

					<select name="selectbar">
						<option value="0571">杭州</option>
						<option value="0574">宁波</option>
					</select>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">查询相关科室</label>
    					<div class="col-sm-10">
    						<input type="" class="form-control" id="dept" name="dept"/>
    					</div>
    				</div>
    				<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
    						 <button type="submit" class="btn btn-default">查询</button>
    					</div>
    				</div>

    			</form>
    		</div>
    	</div>
    </div>
    <script type="text/javascript">
        var dept = $("#dept").val();
        if(""==dept){
            alert("请输入查询信息")
            return;
        }
        $("#form").submit();

    </script>
</body>
</html>
