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
				<a href="home.html"><br> <img
					src="../images/logo_header.png" class="img-responsive" alt=""
					height="50%" width="50%" /> </a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">

						<li style="color: #ffffff;"><br> Bem vindo(a)
							${modelUsuario.nome}!</li>
						<li><a href="home.html"> <i class="btn btn-primary"
								data-toggle="tooltip" data-placement="bottom" title="Início"><i
									class="fa fa-home"></i>&nbsp;</i>
						</a></li>
						<li><a href="perfil.html"> <i class="btn btn-primary"
								data-toggle="tooltip" data-placement="bottom" title="Perfil">
									<i class="glyphicon glyphicon-user"></i>&nbsp;
							</i>
						</a></li>
						<li><a href="visualizarCaronas.html"> <i
								class="btn btn-primary" data-toggle="tooltip"
								data-placement="bottom" title="Minhas Caronas"><i
									class="fa fa-child"></i>&nbsp;</i>
						</a></li>
						<li><a href="cadastrarCarona.html"> <i
								class="btn btn-primary" data-toggle="tooltip"
								data-placement="bottom" title="Cadastrar Carona"><i
									class="fa fa-car"></i>&nbsp;</i>
						</a></li>
						<li><a href="apresentacao.html"> <i
								class="btn btn-primary" data-toggle="tooltip"
								data-placement="bottom" title="Sair"> <i
									class="fa fa-sign-out"></i>&nbsp;
							</i>
						</a></li>

					</ul>
				</div>
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
						<h1>Cadastrar Carona</h1>
					</center>

				</div>
			</div>

			<form:form modelAttribute="model" method="post">
				<div class="row col-md-6">

					<div class="input-field">
						<label> Origem</label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-globe"></span></span>
							<form:input path="origem" type="text" class="form-control"
								placeholder="Origem" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Data </label>
						<div class='input-group'>
							<span class="input-group-addon"><span
								class="fa fa-calendar"></span></span>
							<form:input path="data" type="text" class="form-control"
								placeholder="Data" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Quantidade de vagas </label>
						<div class='input-group'>
							<span class="input-group-addon"> <span
								class="fa fa-pencil-square-o"></span>
							</span>
							<form:input path="vagas" type="number" class="form-control"
								placeholder="Quantidade de vagas" />
						</div>
						<p></p>
					</div>
					<p></p>

				</div>

				<div class="col-md-6">
					<div class="input-field">
						<label> Destino </label>
						<div class='input-group'>
							<span class="input-group-addon"><span class="fa fa-globe"></span></span>
							<form:input path="destino" type="text" class="form-control"
								placeholder="Destino" />
						</div>
						<p></p>
					</div>
					<p></p>
					<div class="input-field">
						<label> Horário </label>
						<div class='input-group'>
							<span class="input-group-addon"><span
								class="fa fa-clock-o"></span></span>
							<form:input path="hora" type="text" class="form-control"
								placeholder="Horário" />
						</div>
						<p></p>
					</div>
					<br>
					<button type="submit" class="btn btn-success ">
						<span class="fa fa-check fa-lg" aria-hidden="true"></span>&nbsp;
						Cadastrar Carona
					</button>
				</div>
			</form:form>
			<br>
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