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
		<div class="container" style="margin-top: 2%;">
			<div class="row">
				<h2>Perfil</h2>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1 class="panel-title">
							<span class="glyphicon glyphicon-user"></span>&nbsp;
							${modelUsuario.nome}
						</h1>
					</div>
					<div class="panel-body">
						<p>
							<span class="fa fa-road"></span> &nbsp; Endereço:
							${modelUsuario.endereco} <br> <span class="fa fa-envelope"></span>
							&nbsp; E-mail: ${modelUsuario.email}
						</p>

					</div>
				</div>
				<br>
			</div>
		</div>
	</section>


</body>
</html>