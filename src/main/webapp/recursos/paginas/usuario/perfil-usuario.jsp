<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AvisaAí - Perfil Usuário</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
	<main>
		<div class="perfil-container">
			<!-- perfil do usuário -->
			<div class="perfil-imagem">
				<img src="<c:out value='${usuario.fotoPerfil}'/>"
					alt="Imagem de Perfil do Usuário">
				<div class="edicao-perfil-imagem">
					<img src="img/icone_editar.png" alt="Ícone Editar Foto Usuário"
						class="">
				</div>
			</div>
			<!-- nome do usuário -->
			<div class="perfil-nome">
				<h2>
					<c:out value='${usuario.nome}' />
					<c:out value='${usuario.sobrenome}' />
				</h2>
			</div>
			<!-- Botão de configuração -->
			<div class="perfil-botoes">
				<button type="button"
					onclick="window.location.href='<!-- Incidentes-acompanhados-->>'">
					<img src="img/icidente-acompanhados.png"
						alt="Ícone Incidentes Acompanhados Usuário"
						class="icone-icidente-acompanhados">
				</button>
				<button type="button"
					onclick="window.location.href='configuracoes.jsp'">
					<img src="img/icone_configuracao.png"
						alt="Ícone Configurações Usuário" class="icone-configuracao">
				</button>
				<a href='<!-- LOGOUT AQUI-->'> <img src="img/logout.png"
					alt="Ícone Logout Usuário" class="icone-logout">
					</button>
			</div>
		</div>
	</main>
</body>
</html>