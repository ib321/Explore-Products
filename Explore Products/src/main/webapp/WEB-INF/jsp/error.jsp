<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h3>An exception occurred! Please contact Support!</h3>
	<h3>${exception}</h3>
	<h3 class="text-danger" ><a onclick="history.back()">Go Back</a> ${dangermsg}</h3>
</div>

<%@ include file="common/footer.jspf"%>