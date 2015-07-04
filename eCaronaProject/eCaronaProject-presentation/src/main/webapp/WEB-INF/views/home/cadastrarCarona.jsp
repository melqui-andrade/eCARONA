<!DOCTYPE html>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<script type="text/javascript">

	function clickIntermunicipal() {
		document.getElementById("caronaIntermunicipal").style.display = "initial";
		document.getElementById("caronaMunicipal").style.display = "none";
		document.getElementById("caronaRelampago").style.display = "none";
		document.getElementById("mensagemDeSaida").style.display = "none";
	}

	function clickMunicipal() {
		document.getElementById("caronaIntermunicipal").style.display = "none";
		document.getElementById("caronaMunicipal").style.display = "initial";
		document.getElementById("caronaRelampago").style.display = "none";
		document.getElementById("mensagemDeSaida").style.display = "none";

	}

	function clickRelampago() {
		document.getElementById("caronaIntermunicipal").style.display = "none";
		document.getElementById("caronaMunicipal").style.display = "none";
		document.getElementById("caronaRelampago").style.display = "initial";
		document.getElementById("mensagemDeSaida").style.display = "none";

	}
	function submitIntermunicipal() {
		document.getElementById("carona").value = "intermunicipal"
	}
	function submitMunicipal() {
		document.getElementById("carona").value = "municipal"
	}
	function submitRelampagol() {
		document.getElementById("carona").value = "relampago"
	}
	
</script>
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

	<section>
		<div class="container">

			<div class="row">
				<div class="col-lg-12 text-center" style="padding-top: 6px;">
					<center>
						<h1>Cadastrar Carona</h1>
					</center>
				</div>
			</div>

			<div class="row">
				<h3>Escolha o tipo de carona</h3>
				<div class="col-md-4">
					<button type="button" class="btn btn-info btn-block"
						onclick="clickIntermunicipal()">Intermunicipal</button>
					&nbsp;
				</div>
				<div class="col-md-4">
					<button type="button" class="btn btn-info btn-block"
						onclick="clickMunicipal()">Municipal</button>
					&nbsp;
				</div>
				<div class="col-md-4">
					<button type="button" class="btn btn-info btn-block"
						onclick="clickRelampago()">Relâmpago</button>
					&nbsp;
				</div>
			</div>

			<div class="row" id="caronaIntermunicipal" style="display: none;">
				<h3>Carona Intermunicipal</h3>
				<form:form modelAttribute="modelCadastrarCarona" method="post">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Origem</label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="origem" type="text" class="form-control"
										placeholder="Origem" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Data </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span></span>
									<form:input path="data" type="text" class="form-control"
										placeholder="Data" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Quantidade de vagas </label>
								<div class='input-group'>
									<span class="input-group-addon"> <span
										class="fa fa-pencil-square-o"></span>
									</span>
									<form:input path="vagas" type="number" class="form-control"
										placeholder="Quantidade de vagas" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Destino </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="destino" type="text" class="form-control"
										placeholder="Destino" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Horário </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-clock-o"></span></span>
									<form:input path="hora" type="text" class="form-control"
										placeholder="Horário" />
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<br>
							<form:input path="carona" type="hidden" />
							<button type="submit" class="btn btn-success"
								onclick="submitIntermunicipal()">
								<span class="fa fa-check fa-lg" aria-hidden="true"></span>&nbsp;
								Cadastrar Carona
							</button>
						</div>
					</div>
				</form:form>
			</div>

			<div class="row" id="caronaMunicipal" style="display: none;">
				<h3>Carona Municipal</h3>

				<form:form modelAttribute="modelCadastrarCarona" method="post">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Origem</label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="origem" type="text" class="form-control"
										placeholder="Origem" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Data </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span></span>
									<form:input path="data" type="text" class="form-control"
										placeholder="Data" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Quantidade de vagas </label>
								<div class='input-group'>
									<span class="input-group-addon"> <span
										class="fa fa-pencil-square-o"></span>
									</span>
									<form:input path="vagas" type="number" class="form-control"
										placeholder="Quantidade de vagas" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Destino </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="destino" type="text" class="form-control"
										placeholder="Destino" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Horário </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-clock-o"></span></span>
									<form:input path="hora" type="text" class="form-control"
										placeholder="Horário" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Cidade</label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="cidade" type="text" class="form-control"
										placeholder="Cidade" />
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<br>
							<form:input path="carona" type="hidden" />
							<button type="submit" class="btn btn-success"
								onclick="submitMunicipal()">
								<span class="fa fa-check fa-lg" aria-hidden="true"></span>&nbsp;
								Cadastrar Carona
							</button>
						</div>
					</div>
				</form:form>
			</div>


			<div class="row" id="caronaRelampago" style="display: none;">
				<h3>Carona Relâmpago</h3>
				<form:form modelAttribute="modelCadastrarCarona" method="post">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Origem</label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="origem" type="text" class="form-control"
										placeholder="Origem" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Data </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span></span>
									<form:input path="data" type="text" class="form-control"
										placeholder="Data" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Quantidade de vagas </label>
								<div class='input-group'>
									<span class="input-group-addon"> <span
										class="fa fa-pencil-square-o"></span>
									</span>
									<form:input path="vagas" type="number" class="form-control"
										placeholder="Quantidade de vagas" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="input-field">
								<p></p>
								<label> Destino </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="destino" type="text" class="form-control"
										placeholder="Destino" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Horário </label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-clock-o"></span></span>
									<form:input path="hora" type="text" class="form-control"
										placeholder="Horário" />
								</div>
							</div>
							<div class="input-field">
								<p></p>
								<label> Cidade</label>
								<div class='input-group'>
									<span class="input-group-addon"><span
										class="fa fa-globe"></span></span>
									<form:input path="cidade" type="text" class="form-control"
										placeholder="Cidade" />
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<br>
							<form:input path="carona" type="hidden" />
							<button type="submit" class="btn btn-success"
								onclick="submitRelampago()">
								<span class="fa fa-check fa-lg" aria-hidden="true"></span>&nbsp;
								Cadastrar Carona
							</button>
						</div>
					</div>
				</form:form>
			</div>


			<div class="row" id="mensagemDeSaida">
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
		<a class="btn btn-primary" href="#page-top"> <i
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