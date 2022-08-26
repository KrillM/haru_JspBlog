<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="mb-3 row">
            <label for="username" class="col-sm-2 col-form-label">아이디</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" id="username" placeholder="사용자 이름을 입력하세요">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">비밀번호</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요">
            </div>
        </div>
        <div style="text-align:end">
            <button id="btn-login" class="btn btn-primary">로그인</button>
        </div>
	</form>
</div>
<%@ include file="../fragments/footer.jsp"%>