<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <!-- TODO: 회원 가입 양식(form) -->
    <div class="contaner-fluid">
        <c:set var="pageTitle"  value="회원가입"  />
        <%@ include file="../fragments/header.jspf" %>
        <main>
            <div class="mt-2 card">
                <div class="mt-2 card-header">
                    <h2>회원가입 양식</h2>
                </div>
                <div class="mt-2 card-body">
                    <c:url var="signup" value="/user/signup" />
                    <form name="signup" method="post" action="signup">
                        <div class="mt-2">
                            <input name="userid" class="form-control" type="text" placeholder="ID" required autofocus />
                        </div>
                        <div class="mt-2">
                            <input name="password" class="form-control" type="password" placeholder="비밀번호" required />
                        </div>
                        <div class="mt-2">
                            <input name="email" class="form-control" type="email" placeholder="이메일" required />
                        </div>
                        <div class="mt-2">
                            <input class="form-control btn btn-outline-success" type="submit" value="가입하기" />
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>