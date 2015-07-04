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
						<li><a href="home.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
								data-placement="bottom" title="Início"><i class="fa fa-home"></i>&nbsp;</i>
						</a></li>
						<li><a href="perfil.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
								data-placement="bottom" title="Perfil"> <i
									class="glyphicon glyphicon-user"></i>&nbsp;
							</i>
						</a></li>
						<li><a href="caronasCadastradas.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
								data-placement="bottom" title="Caronas Cadastradas"><i
									class="fa fa-database"></i>&nbsp;</i>
						</a></li>
						<li><a href="visualizarCaronas.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
								data-placement="bottom" title="Minhas Caronas"><i
									class="fa fa-child"></i>&nbsp;</i>
						</a></li>
						<li><a href="cadastrarCarona.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
								data-placement="bottom" title="Cadastrar Carona"><i
									class="fa fa-car"></i>&nbsp;</i>
						</a></li>
						<li><a href="apresentacao.html"> <i
								class="btn btn-primary btn-block" data-toggle="tooltip"
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

	<c:choose>
		<c:when test="${temCarona == 'sim'}">
			<section>
				<div class="container">
					<!--TimeLine
      ================================================== -->
					<div class="example">
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-12">
									<h2>Caronas Cadastradas</h2>
								</div>
								<p></p>
								<c:set var="flag" value="2" />
								<div class="timeline" style="margin-top: 14%;">
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
																<p>
																	Origem: ${carona.origem} <br> Destino:
																	${carona.destino} <br> Data: ${carona.data} <br>
																	Horário: ${carona.hora}h <br> Quantidade de vagas:
																	${carona.vagas} <br>
																</p>
																
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
																<p>
																	Origem: ${carona.origem} <br> Destino:
																	${carona.destino} <br> Data: ${carona.data} <br>
																	Horário: ${carona.hora}h <br> Quantidade de vagas:
																	${carona.vagas}
																</p>
																
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
		</c:when>
		<c:otherwise>
			<section>
				<div class="container">
					<div class="row">
						<h2>Você ainda não cadastrou caronas!</h2>
					</div>
				</div>
			</section>
		</c:otherwise>
	</c:choose>
</body>
</html>