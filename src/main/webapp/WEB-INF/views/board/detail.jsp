<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<div class="container">
    <button class="btn btn-light" onclick="history.back()">뒤로</button>

    <c:if test="${board.user.id == principal.user.id}">
		<a class="btn btn-warning" href="/board/${board.id}/updateForm">수정</a>
        <button class="btn btn-danger" id="btn-delete">삭제</button>
    </c:if>
    <br/><br/>

    <div>
        글 번호: <span id="id"><i>${board.id} </i></span> written by: <span><i>${board.user.username} </i></span>
    </div>
    <br/>

    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <br/>

    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-body">
                <textarea class="form-control" id="reply-content" rows="1"></textarea>
            </div>
            <div class="card-footer" style="text-align:end">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">Comments</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">written by: ${reply.user.username} &nbsp;</div>
                        <c:if test="${reply.user.id eq principal.user.id}">
                            <button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
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