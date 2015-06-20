<!DOCTYPE html>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<body style="background-color: #f1f2f6;">


	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<p></p>
				<a class="navbar-brand" href="#">ECARONA</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<form:form modelAttribute="model" method="post"
					class="navbar-form navbar-right">
					<p></p>
					<div class="col-md-4">
						<div class='input-group'>
							<span class="input-group-addon"
								style="background-color: #A0D468;"><span
								class="glyphicon glyphicon-user"></span></span>
							<form:input path="login" type="text" class="form-control"
								placeholder="Login" />
						</div>
					</div>

					<div class="col-md-4">
						<div class='input-group'>
							<span class="input-group-addon"
								style="background-color: #A0D468;"><span
								class="glyphicon glyphicon-lock"></span></span>
							<form:input path="senha" type="password" class="form-control"
								placeholder="Senha" />
						</div>
					</div>
					<div class="col-md-4">
						<button type="submit" class="btn btn-success btn-block">
							<span class="fa fa-sign-in fa-lg" aria-hidden="true"></span>&nbsp;
							Entrar
						</button>
					</div>

					<p></p>
				</form:form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<section id="about" style="margin-top: 4%;">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center" style="padding-top: 6px;">
					<center>
						<h1>Cadastrar Carona</h1>
					</center>

				</div>
			</div>

			<form:form modelAttribute="model" method="post">
				<div class="row col-md-6">

					<div class="input-field">
						<label> Login</label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<form:input path="login" type="text" class="form-control"
								placeholder="Login" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Senha </label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-lock"></span></span>
							<form:input path="senha" type="password" class="form-control"
								placeholder="Senha" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Nome </label>
						<div class='input-group'>
							<span class="input-group-addon"> <span
								class="fa fa-pencil-square-o"></span>
							</span>
							<form:input path="nome" type="text" class="form-control"
								placeholder="Nome" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Endereço </label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-road"></span></span>
							<form:input path="endereco" type="text" class="form-control"
								placeholder="Endereço" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> E-mail </label>
						<div class='input-group'>
							<span class="input-group-addon"><span
								class="fa fa-envelope"></span></span>
							<form:input path="email" type="text" class="form-control"
								placeholder="E-mail" />
						</div>
						<p></p>
					</div>

					<button type="submit" class="btn btn-primary btn-block">
						<span class="fa fa-sign-in fa-lg" aria-hidden="true"></span>&nbsp;
						Cadastrar
					</button>
				</div>
			</form:form>
			<div class="col-md-6"
				style="color: #2a80b9; padding-top: 10%; padding-left: 15%; font-size: 32px;">
				&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-user-plus fa-5x"></i>
				<div class="alert alert-info" role="alert">
					<label>${mensagem}</label>
				</div>
			</div>
		</div>
	</section>

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