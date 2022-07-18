<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" type="text/css" href="/css/menu.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="/board/list">Board</a></li>
        <li><a href="/login/login">login</a></li>
        <li><a href="/register/add">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
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
    <button id="writeBtn" class="btn-write" onclick="location.href='/board/write'"><i class="fa fa-pencil"></i> 글쓰기</button>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="board" items="${list}">
            <tr>
                <td>${board.bno}</td>
                <td><a href="/board/read?bno=${board.bno}&page=${page}&pageSize=${pageSize}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.reg_date}</td>
                <td>${board.view_cnt}</td>
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