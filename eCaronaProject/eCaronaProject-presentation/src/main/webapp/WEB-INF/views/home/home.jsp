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

				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">

						<li><a href="home.html"> <i class="btn btn-primary"
								data-toggle="tooltip" data-placement="bottom" title="Início"><i
									class="fa fa-home" ></i>&nbsp;</i>
						</a></li>
						<li><a href="home.html"> <i class="btn btn-primary"
								data-toggle="tooltip" data-placement="bottom" title="Perfil">
									<i class="glyphicon glyphicon-user" ></i>&nbsp;
							</i>
						</a></li>
						<li><a href="cadastrarCarona.html"> <i class="btn btn-primary"
								data-toggle="tooltip" data-placement="bottom"
								title="Cadastrar Carona"><i class="fa fa-car"></i>&nbsp;</i>
						</a></li>
						<li><a href="apresentacao.html"> <i
								class="btn btn-primary" data-toggle="tooltip"
								data-placement="bottom" title="Sair"> <i
									class="fa fa-sign-out" ></i>&nbsp;
							</i>
						</a></li>

					</ul>
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<section id="about" style="margin-top: 8%;">
		<div class="container" style="margin-top: 2%;">

			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h2 class="panel-title">Bem vindo ${modelUsuario.nome}!</h2>
						<h3 class="panel-title">${allCaronas}!</h3>
					</div>
				</div>


			</div>
			<br>
		</div>

		<div class="container">

			<!--TimeLine
      ================================================== -->
			<div class="example">
				<h1>Caronas</h1>

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-12">
							<label>Pesquisar caronas</label>
						</div>
						<p></p>
						<div class="col-md-10">
							<input type="text" class="form-control"
								placeholder="digite aqui a sua busca">
						</div>
						<div class="col-md-2">
							<a class="btn btn-success" href="cadastrar.html"> <span
								class="fa fa-search" aria-hidden="true"></span>&nbsp; 
							</a>
						</div>
						<p></p>
						<c:set var="flag" value="2" />
						<div class="timeline" style="margin-top: 6%;">
							<dl>
								<c:forEach items="${todasCaronas}" var="carona">
									<!-- 									<dt>Apr 2014</dt> -->
									<c:choose>
										<c:when test="${flag >1}">
											<dd class="pos-right clearfix">
												<div class="circ"></div>
												<!-- 										<div class="time">Apr 14</div> -->
												<div class="events">
													<div class="pull-left">
														<img class="events-object img-rounded"
															src="../img_theme/photo-1.jpg">
													</div>
													<div class="events-body">
														<!-- 												<h4 class="events-heading">CARONA 001</h4> -->
														<p>${carona}</p>
														<a class="btn btn-success" href="cadastrar.html"> <span
															class="fa fa-check" aria-hidden="true"></span>&nbsp;
															Quero esta carona!
														</a>
														<p></p>
													</div>
												</div>
											</dd>
											<c:set var="flag" value="1" />
										</c:when>
										<c:otherwise>
											<dd class="pos-left clearfix">
												<div class="circ"></div>
												<!-- 										<div class="time">Apr 10</div> -->
												<div class="events">
													<div class="pull-left">
														<img class="events-object img-rounded"
															src="../img_theme/photo-2.jpg">
													</div>
													<div class="events-body">
														<!-- 												<h4 class="events-heading">CARONA 002</h4> -->
														<p>${carona}</p>
														<a class="btn btn-success" href="cadastrar.html"> <span
															class="fa fa-check" aria-hidden="true"></span>&nbsp;
															Quero esta carona!
														</a>
														<p></p>
													</div>
												</div>
											</dd>
											<c:set var="flag" value="2" />
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</dl>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>


</body>
</html>