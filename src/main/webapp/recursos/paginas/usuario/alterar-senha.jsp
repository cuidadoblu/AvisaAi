<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AvisaAí - Alterar Senha</title>
<link rel="shortcut icon" href="" type="image/x-icon">
</head>
<body>
	<!-- Logo no topo -->
	<header>
		<div class="logo-acesso">
			<a href="landing-Page.jsp"><img src="img" alt="AvisaAí Logo"
				class="logo-avisaai"></a>
		</div>
	</header>
	<main>
		<div class="container-acesso">
			<!-- Div da imagem -->
			<div class="acesso-esquerda">
				<img src="" alt="cadastro" class="imagem-tela">
			</div>
			<!-- Div do formulário -->
			<div class="acesso-direita">
				<div class="formulario-entradas">
					<h2>Alterar Senha</h2>
					<form action="alterar-senha" method="post">
						<div class="formulario-grupo">
							<input type="tel" id="telefone" name="telefone" placeholder="Telefone:" pattern="[0-9]{11}">
						</div>

						<h3>ou</h3>

						<div class="formulario-grupo">
							<input type="email" id="email" name="email" placeholder="Email:">
						</div>

						<div class="formulario-botoes">
							<button type="submit" class="botao-confirmar">Confirmar</button>
							<button type="button" onclick="window.location.href='cadastro.jsp'"class="botao-cadastrar">Cadastrar-se</button>
							<div class="botao-login">
								Já tem conta? <a href="login.jsp" class="link">Entrar</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
