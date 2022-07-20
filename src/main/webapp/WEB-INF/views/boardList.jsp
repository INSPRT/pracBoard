<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/css/menu.css"/>
</head>
<body>
<script>
    let msg = "${param.msg}";
    if(msg=="LIST_ERR")  alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
    if(msg=="READ_ERR")  alert("삭제되었거나 없는 게시물입니다.");
    if(msg=="DEL_ERR")   alert("삭제되었거나 없는 게시물입니다.");
    if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
    if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
    if(msg=="MOD_OK")    alert("성공적으로 수정되었습니다.");
</script>
<div style="text-align:center">
    <button id="writeBtn" onclick="location.href='/board/write'">신청하기</button>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>신청제목</th>
            <th>좋아요</th>
            <th>신청일</th>
            <th>신청자</th>
            <th>답변여부</th>
        </tr>
        <c:forEach var="board" items="${list}">
            <tr>
                <td>${board.bno}</td>
                <td><a href="/board/read?bno=${board.bno}&page=${page}&pageSize=${pageSize}">${board.title}</a></td>
                <td>${board.like_cnt}</td>
                <td>${board.report_date}</td>
                <td>${board.reporter}</td>
                <td>${board.is_pinned}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div class="paging-container">
        <div class="paging">
            <c:if test="${ph.totalCnt==null || ph.totalCnt==0}">
                <div> 게시물이 없습니다. </div>
            </c:if>
            <c:if test="${ph.totalCnt!=null && ph.totalCnt!=0}">
                <c:if test="${ph.showPrev}">
                    <a class="page" href="/board/list?page=${ph.beginPage-1}">&lt;</a>
                </c:if>
                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <a class="page ${i == ph.page ? "paging-active" : ""}" href="/board/list?page=${i}">${i}</a>
                </c:forEach>
                <c:if test="${ph.showNext}">
                    <a class="page" href="/board/list?page=${ph.endPage+1}">&gt;</a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>