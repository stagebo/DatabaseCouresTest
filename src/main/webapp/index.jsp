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

<link rel="stylesheet" type="text/css"
	href="static/plugins/MiniUI/themes/default/miniui.css" />
<link rel="stylesheet" type="text/css"
	href="static/plugins/Bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css"
	href="static/plugins/Bootstrap/css/bootstrap.css" />
<script src="static/plugins/jquery/jquery-1.12.2.js"></script>
<script src="static/plugins/MiniUI/miniui.js"></script>
<script src="static/plugins/Bootstrap/js/bootstrap.min.js"></script>
<style>
.tab-item {
	width: 80%;
	margin-left: 10%;
	border: 1px solid;
	padding-left: 20px;
}

img {
	width: 150px;
	height: 150px;
	float: left;
	margin-left: 5px;
	margin-top: 5px;
}
</style>
</head>
<body>
	<div class="tab-item">
		<h1>添加学生</h1>

		<br> <span>sno：</span> <input type="input" id="s_no" /> <br>
		<br> <span>sname：</span> <input type="input" id="s_na" /> <br>
		<br> <span>ssex：</span> <input type="input" id="s_sex" /> <br>
		<br> <span>sage：</span> <input type="input" id="s_age" /> <br>
		<br> <span>sdept：</span> <input type="input" id="s_dept" /> <br>
		<button type="button" class="btn btn-primary" id="sub_add_stu">添加学生</button>
		<script>
			$(function() {
				$("#sub_add_stu").click(
						function() {
							var no = $("#s_no").val();
							var na = $("#s_na").val();
							var sex = $("#s_sex").val();
							var age = $("#s_age").val();
							var dept = $("#s_dept").val();
							var sql = "insert into student values ('" + no
									+ "','" + na + "','" + sex + "','" + age
									+ "','" + dept + "')";
							var opt = {
								sql : sql
							};
							$.ajax({
								url : '/DatabaseCourse' + '/Message/excuteSql',
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


		<h1>查找学生</h1>
		<div>
			<button type="button" class="btn btn-primary" id="search_stu">查询学生</button>

			<div id="datagrid2" class="mini-datagrid"
				style="width:700px;height:250px;">
				<div property="columns">
					<div type="indexcolumn"></div>
					<div field="sno" width="120">SNO</div>
					<div field="sna" width="120">SNAME</div>
					<div field="ssex" width="100">SSEX</div>
					<div field="sage" width="100">SAGE</div>
					<div field="sdept" width="100">SDEPT</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			mini.parse();
			var grid = mini.get("datagrid2");
			$(function() {

				$("#search_stu").click(function() {
					$.ajax({
						url : '/DatabaseCourse' + '/Message/getStudent',
						type : 'get',
						data : '',
						dataType : 'json',
						success : function(data) {
							console.log(data);
							mini.get("datagrid2").setData(data);

						}

					});

				});
			})
		</script>

	</div>
	<div class="tab-item">
		<h1>添加课程</h1>
		<br> <span>CNO：</span> <input type="input" id="no" /> <br>
		<br> <span>CNAME：</span> <input type="input" id="na" /> <br>
		<br> <span>CPNO：</span> <input type="input" id="pno" /> <br>
		<br> <span>CCREDIT：</span> <input type="input" id="cc" /> <br>
		<br>
		<button type="button" class="btn btn-primary" id="sub">添加课程</button>
		<br> <br>
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
		<h1>查找课程</h1>
		<div>
			<button type="button" class="btn btn-primary" id="search">查询课程</button>

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
			$(function() {

				$("#search").click(function() {
					$.ajax({
						url : '/DatabaseCourse' + '/Message/getCourse',
						type : 'get',
						data : '',
						dataType : 'json',
						success : function(data) {
							grid.setData(data);

						}

					});

				});
			})
		</script>



	</div>

	<div class="tab-item">
		<h1>上传图片</h1>
		<div>
			<form action="<%=basePath%>/Message/springUpload" method="post"
				enctype="multipart/form-data">
				<input type="file" value="请选择图片" name="imgfile" /> <input
					type="submit" value="上传" />
			</form>
		</div>

		<div id="imgcont"
			style="width:100%;height:500px;background-color:#aaa;">

			<!-- <img src="static/imgFiles/9a5cc4ec-a4da-427e-a7b2-bd72c4f32744.jpg"/> -->

		</div>
		<script>
			$(function() {

				$.ajax({
					url : '/DatabaseCourse' + '/Message/getImgList',
					type : 'get',
					data : '',
					dataType : 'json',
					success : function(data) {

						$("#imgcont").html("");
						console.log(data);
						data.forEach(function(v) {
							var src = v.src;
							var imgItem = '<img src="'+src+'"/>';
							$("#imgcont").append(imgItem);
						});

					}

				});

			});
		</script>
	</div>
</body>
</html>