<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/tool/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ログイン済みならメイン画面へ --%>
<c:if test="${not empty sessionScope.user}">
	<c:redirect url="/InsSelect.action" />
</c:if>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<br> <br>
		<h1 class="header center ">アイディアボックス</h1>
		<p class="center">アイディアボックスはイラスト投稿サイトです</p>
		<div class="row center">
			<br> <br>
			<div class="row">
				<div class="col s3"></div>
				<div class="col s6">
					<form action="Login.action" method="post">
						<div class="row">
							<div class="input-field col s12">
								<input id="name" type="text" class="validate" name="user_name">
								<label for="name">name</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<input id="password" type="password" class="validate"
									name="pass"> <label for="password">password</label>
							</div>
						</div>
						<div class="row">
							<div class="col s8"></div>
							<div class="col s4">
								<button class="btn waves-effect waves-light blue darken "
									type="submit" name="action">
									ログイン <i class="material-icons right">send</i>
								</button>
							</div>
						</div>

					</form>
					<p>${requestScope.loginMsg}</p>

				</div>
				<div class="col s3"></div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col s1"></div>
				<div class="col s5">
					<a href="entry.jsp">アカウントを作る</a>
				</div>
				<div class="col s5">
					<form method="post" name="form1" action="Login.action">
						<input type="hidden" name="user_name" value="guest">
						<input type="hidden" name="pass" value="">
						<a href="javascript:form1.submit()">ゲストとしてログインする</a>
					</form>
				</div>
				<div class="col s1"></div>
			</div>
		</div>
		<br> <br>
	</div>
</div>

<br>
<br>
<%@ include file="WEB-INF/jsp/tool/footer.jsp"%>
