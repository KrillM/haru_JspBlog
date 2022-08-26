<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<div class="container">
    <form>
        <div class="form-group">
            <input type="text" id="title" class="form-control" placeholder="제목을 입력하세요">
        </div>
        <div class="form-group">
            <textarea rows="5" id="content" class="form-control summernote"></textarea>
        </div>
    </form>
    <div style="text-align:end">
        <button id="btn-save" class="btn btn-primary">글 쓰기</button>
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