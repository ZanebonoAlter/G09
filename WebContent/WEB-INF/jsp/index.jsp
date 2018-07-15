<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Title</title>
<script type="text/javascript">
function  getconnecting()
{
	$.ajax({
			type:"post",
			url:"/Tomcat_Control/connecting/load_all_connecting",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){					
				
				location.reload();	
			},
			
			
			error:function(){
				
			}
		});
	
}


</script>
</head>
<body>
<div id="table"  >
        <table class="table table-hover table-striped" >
            <thead>
            <tr>
                <td>连接IpAddress</td>
                <td>连接状态</td>
                <td>连接名称</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${connecting}" var="connecting">
            <c:out value="${status.ipaddress}"/>
                <tr>
                    <td>${connecting.ipaddress}</td>
                    <td>${connecting.status}</td>
                    <td>${connecting.name}</td>                
                </tr>
            </c:forEach>
            </tbody>
        </table>       
    </div>
    <button type="button" class="btn btn-default" onclick="getconnecting()">获取当前连接</button>        
</body>
</html>