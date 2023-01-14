<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding= "UTF-8"%>

<c:set var="pageTitle" value="게시물 리스트" />
<%@ include file = "../common/head.jspf%>
<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table border="1">
        <colgroup>
          <col width="80"/>
          <col width="150"/>
          <col width="150"/>
          <col width="150"/>
          <col />
        </colgroup>
        <thead>
        <tr>
          <th>번호</th>
          <th>작성 날짜</th>
          <th>업데이트 날짜</th>
          <th>작성자</th>
          <th>제목</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
          <tr>
            <th>${article.id}"</th>
            <th>${article.regDate}"</th>
            <th>${article.updateDate}"</th>
            <th>${article.extra__writerName}"</th>
            <th>${article.title}"</th>
            <th><a class="hover: underline" href="../article/detail?id=${article.id}" ></a></th>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

</section>
<%@ include file = "../common/foot.jspf%>