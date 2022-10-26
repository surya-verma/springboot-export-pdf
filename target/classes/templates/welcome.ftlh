<!DOCTYPE html>
<html>
<head>
<title>Welcome Letter</title>
</head>
<body>
<img src="https://picsum.photos/id/237/200/300"></img>
	<h3>Dear ${name},</h3>

	<p>A warm welcome and lots of good wishes on becoming part of our growing team.</p>
	<p>We are pleased to inform you that you will work with the following team members:</p>
	<ol>
		<#list teamMembers as teamMember>		
			<li>${teamMember}</li>			
		</#list>		
	</ol>	
	<p>Regards</p>
	<p>DFPS Team</p>
	<p>Location: ${location}</p>
</body>
</html>
