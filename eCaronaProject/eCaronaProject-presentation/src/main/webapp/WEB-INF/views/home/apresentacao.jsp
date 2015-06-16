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

					<li><a href="cadastrar.html"> <i class="fa fa-hand-o-right"></i>&nbsp;
							Cadastre-se
					</a></li>
					<li><a href="login.html"> <i class="fa fa-sign-in"></i>&nbsp;
							Login
					</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center" style="padding-top: 6px;">
					<center>
						<img src="../images/eCarona_logo.png" class="img-responsive"
							alt="" />
					</center>
					<h3 align="center">rede social para compartilhamento de
								caronas</h3>

				</div>
			</div>
			
			<div class="row">
				<p></p>
				<div class="col-lg-6" style="padding-top: 4%;">
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
				</div>

				<div class="col-md-6"
					style="color: #2a80b9; padding-top: 0%; padding-left: 15%; font-size: 32px;">
					&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
						class="fa fa-comment fa-5x"></i>
					<p></p>
					<i class="fa fa-child fa-5x"></i> &nbsp; &nbsp;<i
						class="fa fa-car fa-5x"></i>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-4 col-lg-offset-2"></div>
			</div>

			<footer>
				<p style="color: #000000; font-size: 12px;">eCarona © 2015
					Copyright Melquisedec Andrade, Sidney Pimentel</p>
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