<!DOCTYPE html>
  <%@ include file="/WEB-INF/views/imports.jsp"%>
  <%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll navbar-right">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" style="border: 1px solid #eeeeee;">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<img src="../images/eCarona_logo_inverso.png" class="img-responsive" alt="" height="50%" width="50%" align="right"/>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav nav-wrapper navbar-left">
				<li class="hidden"><a href="/Home/Index"></a></li>
				<li><a href="apresentacao.html"> <i class="fa fa-home"></i>&nbsp; Início</a></li>
				<li><a href="/Home/Index"> <i class="fa fa-bus"></i>&nbsp; Cadastrar Carona</a></li>
				<li><a href="login.html"> <i class="fa fa-sign-out"></i>&nbsp; Sair</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>




</body>
</html>