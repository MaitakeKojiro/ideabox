<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../tool/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="row">
	<%-- 	<c:forEach var="inspiration" items="${list}">
		<a href="InsIdSelect.action?insId=${inspiration.insId}">
			<div class="col s12 m3">
				<div class="card small hoverable">
					<c:choose>
						<c:when test="${not empty inspiration.imgName}">
							<div class="card-image small">
								<img
									src="/ideabox/image/inspiration/${inspiration.imgPath}/${inspiration.imgName}.png">
							</div>
						</c:when>
						<c:otherwise>
							<div class="card-image small">
								<img src="/ideabox/image/inspiration/noimage.png">
							</div>
						</c:otherwise>
					</c:choose>

					<div class="card-content flow-text">
						<p>${inspiration.title}</p>
					</div>
				</div>
			</div>
		</a>
	</c:forEach> --%>
	<h2 class="header center ">アイディアの一覧</h2>
	<br>
	<div class="collection">
		<c:forEach var="inspiration" items="${insList}">
			<a href="InsIdSelect.action?insId=${inspiration.insId}" class="collection-item" >
			<blockquote>${inspiration.title}</blockquote>${inspiration.body}<br>${inspiration.date}
			</a>

		</c:forEach>
	</div>
</div>
<!-- フローティングボタン -->
<%@ include file="../tool/floatingButton.jsp"%>
<%@ include file="../tool/footer.jsp"%>