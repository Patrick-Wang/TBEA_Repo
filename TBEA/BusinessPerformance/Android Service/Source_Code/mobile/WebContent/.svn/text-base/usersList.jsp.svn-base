<%@ page language="java" contentType="text/html; charset=gbk" import="java.util.*,java.sql.*,com.tbea.javaBean.*"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>		
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>用户列表</title>
		<meta http-equiv="content-script-type" content="text/javascript">
		<meta http-equiv="content-style-type" content="text/css">
		<meta http-equiv="content-language" content="en-gb">
		<meta http-equiv="imagetoolbar" content="no" />
		<link rel="stylesheet" href="dataTable.css" media="screen">
		<style type="text/css">
		body, th, td { font-family: Arial, Verdana, sans-serif; font-size: 0.9em; }
		a:link, a:visited { color: #59B337; }
		a:hover, a:active, a:focus { color: #000000; }
		table.dataTable tr.marked { background-color: #FFD900; }
		</style>
		<!-- jquery packed -->
		<script type="text/javascript" src="../jquery-1.2.3.pack.js"></script>
		<!-- tableRowCheckboxToggle -->
		<script type="text/javascript" src="../tableRowCheckboxToggle.js"></script>


	</head>

	<body>
			<h2>
				用户信息列表
			</h2>
			<%
				//要显示的数据从request中取

				ArrayList al = (ArrayList) request.getAttribute("result");
			%>
			<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
				<thead>
    				<tr>
    				<th class="dataTableHeader">IMEI</th>
    				<th class="dataTableHeader">姓名</th>   				
					<th class="dataTableHeader">电话号码</th>
					<th class="dataTableHeader">menu权限</th>
					<th class="dataTableHeader">公司权限</th>
					<th class="dataTableHeader">验证码</th>
					<th class="dataTableHeader">验证码有效期</th>
					<th class="dataTableHeader">删除用户</th>
				</tr>
				</thead>
				 <tbody>
				<%
					String[] color = { "odd_row", "even_row" };
					for (int i = 0; i < al.size(); i++) {
						UserBean userbean = (UserBean) al.get(i);
				%>
				<tr class="<%=color[i % 2]%>">
					<td><%=userbean.getImei()%></td>	
					<td><%=userbean.getUsername()%></td>				
					<td><%=userbean.getUserid()%></td>
					<td><%=userbean.getMenuqx()%></td>
					<td><%=userbean.getCompanyqx()%></td>
				    <td><%=userbean.getVerificationCode()%></td>
					<td><%=userbean.getVerificateDate()%></td>					
					<td>
						
							<a onclick="return abc();"
								href="UserClServlet?flag=delete&userid=<%=userbean.getUserid()%>">删除</a>
						
					</td>
				</tr>

				<%
					}
				%>
				</tbody>
			</table>
<hr>
			<%
				//显示超链接
				//得到pageNow
				int pageNow = Integer.parseInt(request.getAttribute("pageNow")
						.toString());

				if (pageNow != 1) {
					out.println("<a herf=UserClServlet?flag=paging&pageNow=" + (pageNow - 1)
							+ ">上一页</a>");
				}

				//得到pageCount
				int pageCount = Integer.parseInt(request.getAttribute("pageCount")
						.toString());

				for (int i = 1; i <= pageCount; i++) {
					out.println("<a href=UserClServlet?flag=paging&pageNow=" + i
							+ ">[" + i + "]</a>");
				}
				if (pageNow != pageCount) {
					out.println("<a herf=UserClServlet?flag=paging&pageNow=" + (pageNow + 1)
							+ ">下一页</a>");
				}
			%>
	</body>
</html>