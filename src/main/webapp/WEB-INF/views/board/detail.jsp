
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br/><br/>
    <div>
        글번호 : <sapn id ="id"><i> ${board.id} </i></sapn>
        작성자 : <sapn id ="id"><i> ${board.user.username} </i></sapn>
    </div>

    <br/><br/>

    <div class="form-group">
        <h3>${board.title}</h3>
    </div>

    <hr />

    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr />

<button id="btn-save" class="btn btn-primary">글 올리기</button>
</div>
<script src = "/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>