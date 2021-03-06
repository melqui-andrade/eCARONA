<!DOCTYPE html>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<body style="background-color: #f1f2f6;">


	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"
		style="position: relative;">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a href="apresentacao.html"><br> <img
					src="../images/logo_header.png" class="img-responsive" alt=""
					height="50%" width="50%" /> </a>
				<p></p>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
				<li><a href="apresentacao.html"> <i class="btn btn-primary"
						data-toggle="tooltip" data-placement="bottom" title="In�cio"><i
							class="fa fa-home"></i>&nbsp;</i>
				</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center" style="padding-top: 6px;">
					<center>
						<h1>Cadastro</h1>
					</center>

				</div>
			</div>

			<form:form modelAttribute="model" method="post">
				<div class="row col-md-6">

					<div class="input-field">
						<label> Login</label>
						<div class='input-group'>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-user"></span></span>
							<form:input path="login" type="text" class="form-control"
								placeholder="Login" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Senha </label>
						<div class='input-group'>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-lock"></span></span>
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
						<label> Endere�o </label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-road"></span></span>
							<form:input path="endereco" type="text" class="form-control"
								placeholder="Endere�o" />
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
				style="color: #2a80b9; padding-top: 2%; padding-left: 15%; font-size: 32px;">
				&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<center>
					<i class="fa fa-user-plus fa-5x"></i>
				</center>
				<c:choose>
					<c:when test="${status == 'positivo'}">
						<div class="alert alert-success" role="alert">${mensagem}</div>
					</c:when>
					<c:otherwise>
						<c:if test="${status == 'negativo'}">
							<div class="alert alert-danger" role="alert">${mensagem}</div>
						</c:if>
					</c:otherwise>
				</c:choose>
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