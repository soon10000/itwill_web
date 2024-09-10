<%@page import="com.itwill.lab04.model.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="header.jspf"%>

    <main>
        <h1>JSTL(JSP Standard Tag Library)</h1>
        <%--
        JSTL 라이브러리 사용하기
            1. pom.xml 파일에 의존성(dependency)을 추가
                - jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-jpi:3.0.0
                - org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1
            2. JSTL을 사용하는 JSP 파일에서 <%@ taglib prefix ="" uri="" %> 지시문을 설정
         --%>


        <%
        String[] sns = { "인*", "싸이월드", "얼굴책", "X" };
        pageContext.setAttribute("sns", sns);
        %>

        <h2>스크립트릿, 식 사용한 리스트</h2>
        <ul>
            <%
            for (String s : sns) {
            %>
            <li><%=s%></li>
            <%
            }
            %>
        </ul>

        <h2>JSTL, EL을 사용한 리스트</h2>
        <ul>
            <c:forEach items="${ sns }" var="s">
                <!--JSTL에서 반복문은 forEach 태그 -->
                <li>${ s }</li>
            </c:forEach>
        </ul>

        <%
        ArrayList<Contact> data = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
        	data.add(new Contact(i, "name_" + i, "phone_" + i, "email_" + i));
        }
        pageContext.setAttribute("contactList", data);
        %>

        <h2>scriptlet, expression 사용한 테이블</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>

                <%
                for (Contact c : data) {
                %>
                <tr>
                    <td><%=c.getId()%></td>
                    <td><%=c.getName()%></td>
                    <td><%=c.getPhone()%></td>
                    <td><%=c.getEmail()%></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>


        <h2>JSTL, EL 사용한 테이블 사용한 테이블</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${ contactList }">
                    <tr>
                        <td>${ c.id }</td> <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음 --%>
                        <td>${ c.name }</td>
                        <td>${ c.phone }</td>
                        <td>${ c.email }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h2>URL 태그</h2>
        <a href="result.jsp?username=guest&color=crimson"> 클릭1 </a> <br/>
        
        <%-- 질의 문자열  요청 파라미터 값에 특수 기호가 포함될 때 --%>
        <c:url value="result.jsp" var="url">
            <c:param name="username" value="guest" />
            <c:param name="color" value="crimson" /> 
        </c:url>
        <a href="${ url }">클릭2</a>

    </main>
</body>
</html>