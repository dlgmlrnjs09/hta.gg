<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<h1 style="text-align: center; margin-top: 30px;">내가 작성한 댓글 목록</h1>
<div style="margin: 70px;">
	<table style="width: 1000px;">
		<tr>
			<th>댓글 번호</th>
			<th>댓글 내용</th>
			<th>댓글 작성일</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>111</td>
				<td><a href="${pageContext.request.contextPath }/community/detail?pNum=${vo.pNum }&cNum=${vo.cNum }">${vo.title }</a></td>
				<td>${vo.hit }</td>
				<td>${vo.nohit }</td>
				<td>${vo.viewCount }</td>
				<td>${vo.commentCount }</td>
				<td>${vo.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<c:if test="${pu.startPageNum>5 }">
		<a href="/lol/member/member/boardList?pageNum=${pu.pageNum-1}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${pu.startPageNum}" end="${pu.endPageNum }">
			<c:choose>
				<c:when test="${i==pu.pageNum }">
					<a href="/lol/member/member/boardList?pageNum=${i}"><span style='color:blue'>[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="/lol/member/member/boardList?pageNum=${i}"><span style='color:gray'>[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pu.endPageNum<pu.totalPageCount }">
			<a href="/lol/member/member/boardList?pageNum=${pu.pageNum+1}">[다음]</a>
		</c:if>
	</div>
</div>