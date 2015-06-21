<!DOCTYPE html>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
<html>
<body style="background-color: #f1f2f6;">

	<jsp:include page="/WEB-INF/views/tiles/headerLogin.jsp" />
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<center>
						<h1>ECARONA</h1>
					</center>
					<h3 align="center">Rede social para compartilhamento de
						caronas</h3>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-4" style="padding-top: 2%;">
					<p align="justify">Nosso objetivo é que motoristas e caroneiros
						encontrem as melhores opções para uma carona,</p>
					<p align="justify">Ajudando:</p>
					<ul>
						<li align="justify">Na negociação envolvida em solicitar uma
							carona, do ponto de vista dos caroneiros,</li>
						<li align="justify">A escolher os melhores caroneiros, do
							ponto de vista dos motoristas.</li>
					</ul>
					<p align="justify">Melhores caroneiros podem ser definidos por
						vários critérios, como local de residência, reputação, ou
						conhecimento prévio.</p>

					<div style="margin-top: 8%;">
						Ainda não é cadastrado? <a class="btn btn-success"
							href="cadastrar.html"> <span class="fa fa-hand-o-right"
							aria-hidden="true"></span>&nbsp; Cadastre-se agora!
						</a>

					</div>
					<p></p>
				</div>


				<div class="col-md-8">
					<p></p>
					<div class="pricing">
						<ul>
							<li class="unit price-primary">
								<div class="price-title">
									<h3>
										<span class="fa fa-car" aria-hidden="true">
									</h3>
									<p>Cadastre caronas</p>
								</div>
								<div class="price-body">
									<h4>Ofereça caronas!</h4>
									<p>Divulgue suas vagas</p>

								</div>

							</li>
							<li class="unit price-success active">
								<div class="price-title">
									<h3>
										<span class="glyphicon glyphicon-user	" aria-hidden="true">
									</h3>
									<p>Faça parte da nossa rede!</p>
								</div>
								<div class="price-body">
									<h4>Seja um novo usuário!</h4>
									<p>Entre na nossa rede!</p>

								</div>

							</li>
							<li class="unit price-warning">
								<div class="price-title">
									<h3>
										<span class="fa fa-eye" aria-hidden="true">
									</h3>
									<p>Veja as caronas disponíveis!</p>
								</div>
								<div class="price-body">
									<h4>Veja as caronas!</h4>
									<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Saiba a quant. de vagas
										disponíveis</p>
								</div>

							</li>
						</ul>
					</div>

				</div>

				<div class="row">
					<div class="col-lg-4 col-lg-offset-2"></div>
				</div>


				<p></p>
				<footer>
					<p>eCarona © 2015 Copyright Melquisedec Andrade, Sidney
						Pimentel</p>
				</footer>

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