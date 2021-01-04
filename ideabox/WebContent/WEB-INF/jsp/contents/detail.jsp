<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="../tool/header.jsp"%>
<div class="container">
	<h3 class="title">${inspiration.title}</h3>
	<div>本文：${inspiration.body}</div>
	<hr>
	<h4>みんなの作品</h4>
	<br>
	<div class="row">
		<c:forEach var="pers" items="${persList}">
			<div class="col s12 m4">
				<div class="card medium">


					<div class="card-image waves-effect waves-block waves-light">
						<img class="activator"
							src="image/perspiration/${pers.imgPath}/${pers.imgName}">
					</div>


					<div class="card-content">
						<span class="card-title activator grey-text text-darken-4">
							<div class="row">
								<div class="col s4">
									<img class="responsive-img circle" id="header-icon"
										src="<c:url value="getImage">
										<c:param name="id" value="${pers.userId}" />
												</c:url>" />
								</div>
								<div class="col s8">
									<div class="orange-text">${pers.userName}</div>
								</div>
							</div> <i class="material-icons right">more_vert</i>
						</span>
						<p>${pers.persDate}</p>
					</div>

					<div class="card-reveal">
						<span class="card-title grey-text text-darken-4">${pers.userName}
							<i class="material-icons right">close</i>
						</span>
					</div>

				</div>
			</div>
		</c:forEach>
	</div>
	<hr>

	<h5>イラストを投稿する</h5>
	<form action="/ideabox/PersInsert.action" enctype="multipart/form-data"
		method="post">


		<!-- 画像 -->
		<!-- 完成品なので、画像があること前提にするため、画像が設定されていない場合は、送信できないようにする -->
		<div class="row">
			<div class="col s6">
				<div class="file-field input-field">
					<div class="btn blue">
						<span><i class="large material-icons">attach_file</i></span> <input
							type="file" name="image">
					</div>
					<div class="file-path-wrapper">
						<input class="file-path validate" type="text">
					</div>
				</div>
			</div>
		</div>
		<div class="row"></div>
		<div class="row">
			<!-- サーブレット側で扱うためのデータ -->
			<input type="hidden" name="ins_id" value="${inspiration.insId}" />
			<!-- 送信ボタン -->
			<button class="btn waves-effect waves-light blue" type="submit"
				name="action">
				送信<i class="material-icons right">send</i>
			</button>
		</div>
	</form>
	<p>${requestScope.msg}</p>
</div>

<%@ include file="../tool/footer.jsp"%>