<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

	<h1>Spring Boot file upload example</h1>

	<form method="post" action="/upload" enctype="multipart/form-data">
		> <input type="file" name="file" /><br />
		<br /> <input type="submit" />
	</form>

</body>
</html>
