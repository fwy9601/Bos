<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/modules/exporting.js"></script>
<title>highcharts</title>
<script type="text/javascript">
    $(function(){
    	//页面加载完成后，动态创建图表
    	$("#test").Highcharts({
    		
    	});
    });
</script>
</head>
<body> 
    <div id="test">
    
    </div>
</body>
</html>