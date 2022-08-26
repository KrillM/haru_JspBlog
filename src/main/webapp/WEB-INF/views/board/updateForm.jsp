<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
            <input value="${board.title}" type="text" id="title" class="form-control" placeholder="제목을 입력하세요">
        </div>
        <div class="form-group">
            <textarea rows="5" id="content" class="form-control summernote">${board.content}</textarea>
        </div>
    </form>
    <div style="text-align:end">
        <button id="btn-update" class="btn btn-warning">글 수정</button>
    </div>
</div>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>
<script src="/js/board.js"></script>
<%@ include file="../fragments/footer.jsp"%>