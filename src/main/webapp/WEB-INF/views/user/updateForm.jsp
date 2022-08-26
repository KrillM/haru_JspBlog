<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fragments/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="mb-3 row">
            <label for="username" class="col-sm-2 col-form-label">아이디</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" value="${principal.user.username}" readonly>
            </div>
        </div>
        <div class="mb-3 row">
            <label for="email" class="col-sm-2 col-form-label">이메일 수정</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" value="${principal.user.email}">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">비밀번호 수정</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요">
            </div>
        </div>
    </form>
    <div style="text-align:end">
        <button id="btn-update" class="btn btn-primary">회원정보수정</button>
    </div>
</div>
<script src="/js/user.js"></script>
<%@ include file="../fragments/footer.jsp"%>