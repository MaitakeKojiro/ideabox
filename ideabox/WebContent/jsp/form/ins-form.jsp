<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../tool/header.jsp"%>
<div class="container">
	<!-- アイディアを出すページ -->
	<!-- フォームの遷移先が未記入 -->
	<h1>アイディアを投稿する</h1>
	<form action="InsInsert.action" id="form-id" method="post">
		<!-- タイトル -->
		<div class="row">
			<div class="row">
				<div class="input-field col s12">
					<input placeholder="タイトル" type="text" class="validate" name="title">
				</div>
			</div>

			<!-- アイディアの本文 -->
			<div class="row">
				<div class="row">
					<div class="input-field col s12">
						<textarea id="textarea1" class="materialize-textarea" name="body"></textarea>
						<label for="textarea1">本文</label>
					</div>
				</div>
			</div>
			<!-- 送信ボタン -->
			<button class="btn waves-effect waves-light blue" type="submit" name="action">
				投稿 <i class="material-icons right">send</i>
			</button>
		</div>
	</form>
	${requestScope.msg}
</div>

<%@ include file="../tool/footer.jsp"%>