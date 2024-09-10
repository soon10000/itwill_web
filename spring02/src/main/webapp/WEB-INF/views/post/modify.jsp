<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring Legacy 02</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post Modify" /> <%-- scope의 기본값은 page --%>
        <%@ include file="../fragments/header.jspf" %>
        
        <main>
            <div class="mt-2 card">
                <div class="card-header">
                        <h2>포스트 수정하기</h2>
                </div>
                <div class="card-body">
                    <form id="modifyForm">
                        <div class="mt-2">
                            <label for="id" class="form-label">번호</label>
                            <input name="id" id="id" class="form-control" type="text" value="${post.id}" readonly /> <!-- readonly는 수정이 안 되고 오직 읽기만 가능한 기능 -->
                        </div>
                        <div class="mt-2">
                            <label for="title" class="form-label">제목</label>
                            <input name="title" id="title" class="form-control" type="text" value="${post.title}" />
                        </div>
                        <div class="mt-2">
                            <label for="content" class="form-label">내용</label>
                            <textarea name="content" id="content" class="form-control" rows="5" >${post.content}</textarea>
                        </div>
                        <div class="mt-2">
                            <label for="author" class="form-label">작성자</label>
                            <input id="author" class="form-control" type="text" value="${post.author}" readonly />
                        </div>
                    </form>
                </div>
                
                <div class="card-footer">
                    <button id="btnDelete" class="btn btn-outline-danger">삭제</button>
                    <button id="btnUpdate" class="btn btn-outline-success">업데이트</button>
                </div>
                
            </div>
        </main>
    </div>
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <c:url var="post_modify_js" value="/js/post_modify.js" />
    <script src="${post_modify_js}" > </script> <!-- ../js/post_modify.js 선생님한테 물어보기 현재의 상위 폴더는 views이기 때문에 WEB-INF 하위에 있는게 아닌지? -->
</body>
</html>