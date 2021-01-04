<%--ユーザー登録用のページ。サーバーは"user_name","pass","icon" で値を受け取れる --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tool/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="container">
	<h1>ユーザー登録</h1>
	<div class="row">
		<form action="Entry.action" enctype="multipart/form-data"
			class="col s12" method="POST">
			<div class="row">
				<div class="input-field col s12">
					<input id="first_name" type="text" class="validate"
						name="user_name"> <label for="first_name">name</label> <span
						class="helper-text" data-error="wrong" data-success="right">8~16字以内</span>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<input id="password" type="password" class="validate" name="pass">
					<label for="password">Password</label> <span class="helper-text"
						data-error="wrong" data-success="right">8~16字以内</span>
				</div>
			</div>
			<div class="file-field input-field">
				<div class="btn blue">
					<span>アイコン画像を設定</span> <input type="file" name="icon">
				</div>
				<div class="file-path-wrapper">
					<input class="file-path validate" type="text">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col s9"></div>
				<div class="col s3">
					<button class="btn waves-effect waves-light blue" type="submit"
						name="action">アカウントを追加<i class="material-icons right">send</i>
					</button>
				</div>
			</div>
		</form>
		${requestScope.resultMsg }
	</div>
</div>
	<%@ include file="/WEB-INF/jsp/tool/footer.jsp"%>