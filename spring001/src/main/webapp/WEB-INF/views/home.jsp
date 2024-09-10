<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring 01</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <header>
        <h1>Home</h1>
        <h2>${now}</h2>
        <!-- <img alt="리버스모카" src="/spring01/images/1.jpg" />  절대 경로이지만 컨텍스트투트가 바뀌면 다 바뀌기 때문에 위험한 경로 설정-->
        <!-- <img alt="리버스모카"  src=".images/1.jpg" />  현재 위치에 따른 상대경로이기 때문에 컨텍스트 루트가 바뀌어도 문제가 되지 않음. -->
        <c:url var="moka" value="/images/1.jpg" />
        <img alt="리버스모카" src="${moka}" />
        <!--  <img src="http://localhost:8080/spring01/images/1.jpg" />  -->
        
    </header>
    
    <main>
        <h1>Contents</h1>
        <nav>
            <ul>
                <li>
                    <c:url var="exPage" value="/example" />
                    <a href="${exPage}">컨트롤러 예제</a>
                </li>
                <li>
                    <c:url var="testPage"  value="/test" />
                    <a href="${testPage}">테스트 페이지</a>
                </li>
                <li>
                    <c:url var="forwardPage"  value="/test2"/>
                    <a href="${forwardPage}">포워드</a>
                </li>
                <li>
                    <c:url var="redirectPage" value="/test3" />
                    <a href="${redirectPage}">리다이렉트</a>
                </li>
                <li>
                    <c:url var="rest1" value="/rest1" />
                    <a href="${rest1}"> REST 1 </a>
                </li>
                <li>
                    <c:url var="rest2" value="/rest2"  />
                    <a href="${rest2}"> REST 2 </a>
                </li>
                <li>
                    <c:url var="rest3" value="/rest3"  />
                    <a href="${rest3}"> REST Controller 3 </a>
                </li>    
                <li>
                    <c:url var="rest4" value="/rest4"  />
                    <a href="${rest4}"> REST Controller 4 </a>
                </li>                            
            </ul>
        </nav>
    </main>
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>