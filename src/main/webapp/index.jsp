<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="static/plugins/MiniUI/themes/default/miniui.css">
<link rel="stylesheet" type="text/css" href="static/plugins/MiniUI/themes/default/miniui.css" />
<script src="static/plugins/jquery/jquery-1.12.2.js"></script>
<script src="static/plugins/MiniUI/miniui.js"></script>
<script>
	$(function() {

		$("#sub").click(function() {
			var cno = $("#no").val();
			var cna = $("#na").val();
			var cpno = $("#pno").val();
			var cc = $("#cc").val();
			var opt = {
				cno : cno,
				cna : cna,
				cpno : cpno,
				cc : cc

			};
			$.ajax({
				url : '/DatabaseCourse' + '/Message/getTimes',
				type : 'get',
				data : opt,
				dataType : 'json',
				success : function(d) {
					alert(d);
				}
			});

		});
	});
</script>
</head>
<body>
	<%-- <%response.sendRedirect("/spring_mvc01/toLogin");%> --%>
	<br> CNO：
	<input type="input" id="no" />
	<br>
	<br> CNAME：
	<input type="input" id="na" />
	<br>
	<br> CPNO：
	<input type="input" id="pno" />
	<br>
	<br> CCREDIT：
	<input type="input" id="cc" />
	<br>
	<br>
	<input type="button" value="添加" id="sub" />
	<br>
	<br>
	<div>
		<input type="button" value="查询" id="search" />


		<div id="datagrid1" class="mini-datagrid"
			style="width:700px;height:250px;">
			<div property="columns">
				<div type="indexcolumn"></div>

				<div field="cno" width="120">CNO</div>
				<div field="cna" width="100">CNAME</div>
				<div field="cpno" width="100" allowSort="true">CPNO</div>
				<div field="cc" width="100" allowSort="true">CCredit</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
mini.parse();
var grid = mini.get("datagrid1");
$(function(){
	
	$("#search").click(function(){
		$.ajax({
			url:'/DatabaseCourse' + '/Message/getCourse',
			type:'get',
			data:'',
			dataType:'json',
			success:function(data){
				grid.setData(data);
				
			}
			
		});
		
	});
})

</script>

	</div>
</body>
</html>