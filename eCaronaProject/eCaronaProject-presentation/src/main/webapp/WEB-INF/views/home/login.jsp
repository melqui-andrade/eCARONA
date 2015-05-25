<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<%@ include file="/WEB-INF/views/imports.jsp"%>
<%@ include file="/WEB-INF/views/includeTags.jsp"%>
</head>
<body>

	<header>
        <div style="color: #FFFFFF; background-color: #2a80b9; position: fixed; width: 100%; height: 35%; top:0;left:0; border-bottom: 12px solid #ecf0f1;">
            <div style="left:50%; padding-top:50px;">
                <img src="../images/logo_login2.png" class="img-responsive" />
              	<i class="fa fa-comment fa-4x"></i>
                <p></p>
                <i class="fa fa-child fa-4x"></i>
              	<i class="fa fa-car fa-4x"></i>
            </div>
        </div>
    </header>

    <div  style="position: fixed; width: 25%; height: 25%; top: 40%; left: 37%; margin: auto; ">
        <b>LOGIN</b>
        <div>
            <form>
                <div class="input-field">
                    <i class="fa fa-user prefix"></i>
                    <input placeholder="Usuário" id="login" type="text" class="validate">
                </div>
                
                <div class="input-field">
                    <i class="fa fa-lock prefix"></i>
                    <input placeholder="Senha" id="senha" type="password" class="validate">
                </div>

                <button onclick="location.href='@Url.Action("Index", "Login")'" type="submit" class="waves-effect waves-light btn btn-default btn-block" >
                    <span class="fa fa-sign-in prefix" aria-hidden="true"></span>  Entrar
                </button>
                
                 <button onclick="location.href='@Url.Action("Index", "Login")'" type="submit" class="waves-effect waves-light btn btn-default btn-block" >
                    <span class="fa fa-hand-o-right prefix" aria-hidden="true"></span>  Novo? Cadastre-se!
                </button>

                <br />
                <br />
                <a href="#">Esqueceu a senha ?</a>
            </form>
        </div>
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