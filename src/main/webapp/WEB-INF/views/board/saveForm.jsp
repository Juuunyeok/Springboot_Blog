
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<div class="container">

<form>
    <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" class="form-control" placeholder="Enter Title" id="title">
    </div>

    <div class="form-group">
        <label for="content">Content:</label>
        <textarea class="form-control summernote" rows="5" id="content"></textarea>
    </div>

    <script>
        $('.summernote').summernote({
            placeholder: '글을 입력하세요',
            tabsize: 2,
            height: 300
        });
    </script>

</form>
<button id="btn-save" class="btn btn-primary">글 올리기</button>
</div>
<script src = "/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>