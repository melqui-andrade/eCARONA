<!DOCTYPE html>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll navbar-right">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1"
					style="border: 1px solid #eeeeee;">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<img src="../images/eCarona_logo_inverso.png" class="img-responsive"
					alt="" height="50%" width="50%" align="right" />
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav nav-wrapper navbar-left">
					<li class="hidden"><a href="/Home/Index"></a></li>
					<li><a href="apresentacao.html"> <i class="fa fa-home"></i>&nbsp;
							Início
					</a></li>
					<li><a href="/Home/Index"> <i class="fa fa-bus"></i>&nbsp;
							Cadastrar Carona
					</a></li>
					<li><a href="login.html"> <i class="fa fa-sign-out"></i>&nbsp;
							Sair
					</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<section>
		<div class="container">
			<div class="col-md-12" style="background-color: #eeeeee;">
				<center style="padding-top: 2%;">
					<h1>Bem vindo caroneiro!</h1>
				</center>

				<div class="col-md-3"
					style="color: #2a80b9; margin-top: -8%; padding-left: 25%; padding-bottom: 4%; font-size: 32px;">
					&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
						class="fa fa-user fa-5x"></i>

				</div>
				<form:form modelAttribute="modelUsuario" method="post">
					<div class="col-md-9" align="justify"
						style="color: #2a80b9; margin-top: -15%; padding-left: 40%; padding-bottom: 4%; font-size: 18px;">
						<label> Nome </label>
						<form:label path="nome" class="label"></form:label>
						<br> <label> Endereço </label>
						<form:label path="endereco" class="label"></form:label>
						<br> <label> E-mail </label>
						<form:label path="email" class="label"></form:label>
					</div>
				</form:form>
			</div>

		</div>
	</section>


</body>
</html>