<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>根据城市名称设置中心点</title>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=path %>/js/jquery-1.12.3.js"></script>
<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	.anchorBL{display:none;}
</style>
<script type="text/javascript">
$(function(){
	$.ajax({
		
        url: '<%=path %>/QueryData',
        type: 'post',
        //传递到服务端
        success:function(res){
        	
			alert(JSON.parse(res).data)
        }

    })
    
})
	
     


</script>


</head>
<body >
	<h1>hahah</h1>
</body>

</html>
