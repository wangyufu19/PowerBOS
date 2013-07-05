<%@ page contentType="text/html;charset=UTF-8" %>
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>

<%
	String contextPath=request.getContextPath();		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=contextPath%>/lib/console/css/css.css" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/jquery.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=contextPath%>/lib/console/js/common.js"></script>
</head>
<body>
<div class="container">
	<div class="operationbar">
		<ul class="guide">
			<li class="title">Home</li>
			<li>&nbsp;&gt;&nbsp;Domain</li>
		</ul>
	</div>
	<div class="space"></div>
	<div class="box">
		<h2 class="boxTop">Domain</h2>
		<div class="innerBox">
			<fieldset>
				<legend>Information and Resources</legend>
				<table width="100%"  cellspacing="0" cellpadding="0" class="content">
					<tr>
						<td>
							<div class="boxItem">
								<h5>Helpful Tools</h5>
								<dl class="sublist">
									<dd><a href="#">Configure applications</a></dd>
									<dd><a href="#">Recent Task Status</a></dd>
								</dl>
							</div>
						</td>
						<td width="30"></td>
						<td width="60%">
							<div class="boxItem">
								<h5>General Information</h5>
								<dl class="sublist">
									<dd><a href="#">Common Administration Task Description</a></dd>
									<dd><a href="#">Set your console preferences</a></dd>
									<dd><a href="#">Read the documentation</a></dd>
								</dl>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>

			<fieldset id="menuBox">
				<legend>Domain Configurations</legend>
				<table width="100%"  cellspacing="0" cellpadding="0" class="content">
					<tr>
						<td width="30%">

							<div class="boxItem">
								<h5>Domain</h5>
								<ul class="list">
									<li><a href="#">Domain</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
								</ul>
							</div>

							<div class="boxItem">
								<h5>Environment</h5>
								<ul class="list">
									<li><a href="#">Servers</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
									<li><a href="#">Clusters</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
									<li><a href="#">Virtual Hosts</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
									<li><a href="#">Machines</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
								</ul>
							</div>

						</td>
						<td width="30"></td>
						<td width="30%">

							<div class="boxItem">
								<h5>Services</h5>
								<ul class="list">
									<li><a href="#">Messaging</a>
										<dl class="sublist">
											<dd><a href="#">JMS Servers</a></dd>
											<dd><a href="#">Store-and-Forward Agents</a></dd>
										</dl>
									</li>
									<li><a href="#">JDBC</a>
										<dl class="sublist">
											<dd><a href="#">Data Sourcess</a></dd>
											<dd><a href="#">Multi Data Sources</a></dd>
										</dl>
									</li>
									<li><a href="#">Persistent Stores</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
									<li><a href="#">XML Registries</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
								</ul>
							</div>

						</td>
						<td width="30"></td>
						<td width="30%">
							
							<div class="boxItem">
								<h5>Interoperability</h5>
								<ul class="list">
									<li><a href="#">WTC Servers</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
									<li><a href="#">Jolt Connection Pools</a>
										<dl class="sublist">
											<dd><a href="#">Configure applications</a></dd>
											<dd><a href="#">Recent Task Status</a></dd>
										</dl>
									</li>
								</ul>
							</div>

						</td>
					</tr>
				</table>
			</fieldset>
		</div>
		<div class="copyright">版权所有 东蓝数码有限公司 Copyright 2010 ,Inc.</div>
	</div>
</div>
<script>
$(document).ready(function(){
	initBox();
}); 	
</script>
</body>	
</html>
