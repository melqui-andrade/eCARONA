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

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
			<div id="divCenter">
				<img src="../images/eCarona_logo.png" class="img-responsive" alt="" />
				<form:form modelAttribute="model" method="post">
					<div class="input-field">
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<form:input path="login" type="text" class="form-control"
								placeholder="Login" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-lock"></span></span>
							<form:input path="senha" type="password" class="form-control"
								placeholder="Senha" />
						</div>
						<p></p>
					</div>
					<button type="submit" class="btn btn-primary btn-block">
						<span class="fa fa-sign-in fa-lg" aria-hidden="true"></span>&nbsp;
						Login
					</button>
				</form:form>
				<p></p>

				<a href="#">Esqueceu a senha ?</a>
			</div>
		</div>
	</div>
	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-top page-scroll visible-xs visble-sm">
		<a class="btn btn-primary" href="#page-top"><i
			class="fa fa-chevron-up"></i></a>
	</div>
</body>

</html>

<style type="text/css">
#divCenter {
	width: 400px;
	height: 150px;
	left: 50%;
	margin: -130px 0 0 -210px;
	padding: 10px;
	position: absolute;
	top: 50%;
}
</style>