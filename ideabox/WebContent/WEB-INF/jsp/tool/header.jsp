<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/styles.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
	type="text/css" rel="stylesheet" media="screen,projection" />
</head>
<body>
	<header>
		<nav class="light-blue lighten-2" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="/ideabox/"
					class="brand-logo flow-text ">アイディアボックス</a>
				<ul id="nav-mobile" class="right hide-on-med-and-down">
					<c:if test="${not empty sessionScope.user}">
						<li>${sessionScope.user.userName}</li>
						<li><a href="Logout.action">ログアウト</a></li>
						<li><img class="responsive-img circle" id="header-icon"
							src="<c:url value="getImage">
										<c:param name="id" value="${sessionScope.user.id}" />
									  </c:url>" />
						</li>
					</c:if>
				</ul>

				<ul id="nav-mobile" class="sidenav">
					<li><a href="#">Navbar Link</a></li>
					<li><a href="">ログアウト</a></li>
				</ul>
				<a href="#" data-target="nav-mobile" class="sidenav-trigger"> <i
					class="material-icons">menu</i></a>
			</div>
		</nav>
	</header>
	<main>