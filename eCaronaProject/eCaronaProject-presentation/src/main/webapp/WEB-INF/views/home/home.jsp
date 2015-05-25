<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Início</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body id="page-top" class="index">

    <nav class="navbar navbar-default navbar-fixed-top" style="height: 10%; border-bottom: 2px solid #ecf0f1; position:fixed; background-color: #2a80b9; padding-bottom: 10px; padding-right: 20px; padding-left: 20px;">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    MENU
                    <span class="fa fa-chevron-down"></span>
                    &nbsp;
                    &nbsp;
                </button>
            	<img src="../images/logo_header.png" class="img-responsive" />
          	 </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right text-white">
                    <li class="hidden">
                        <a href="/Home/Index"></a>
                    </li>
                    <li><a href="/Home/Index"> <i class="fa fa-home"></i> Início </a></li>
                    <li><a href="/Home/Index"> <i class="fa fa-bus"></i> Cadastrar Carona </a></li>
                    <li><a href="/Home/Index"> <i class="fa fa-sign-out"></i> Sair </a></li>
                </ul>
                &nbsp;
                &nbsp;
        	</div>
    </nav>

    <div class="scroll-top page-scroll visible-xs visble-sm">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>
    
    
    <div class="footer-copyright" style="color: #FFFFFF; background-color: #2a80b9;	position:absolute; bottom:0; width:100%;">
		<div class="container">
			eCarona © 2015 Copyright Melquisedec Andrade, Sidney Pimentel
			<a class="grey-text text-lighten-4 right" href="#!">Desenvolvedores</a>
		</div>
	</div>

</body>
</html>

<style>
html {
    position: relative;
    min-height: 100%;
}
footer {
    position: absolute;
    bottom: 0px;
    width: 100%;
    height: 60px;
    background-color: green;
}

</style>