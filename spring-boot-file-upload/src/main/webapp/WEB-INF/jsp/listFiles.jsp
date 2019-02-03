

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
		<table class="table table-striped">

	<caption>Your files are</caption>
			<tbody>
				<c:forEach items="${files}" varStatus="i">
					<tr>
						<td>${files[i.index]}</td>
						
						<td><a type="button" class="btn btn-danger"
							href="/deleteFile?filename=${files[i.index]}">Delete</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/downloadFile?filename=${files[i.index]}">Download</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
				
	
	


