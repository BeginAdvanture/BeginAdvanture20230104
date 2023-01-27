<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding= "UTF-8"%>

<c:set var="pageTitle" value="게시물 내용" />
<%@ include file = "../common/head.jspf%>
<section class="mt-5 con-min-width">
  <div class="con mx-auto px-3">
    <div class="table-box-type-1">
      <table border="1">
        <colgroup>
          <col width="200"/>
        </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>
              <div class="badge badge-primary"> ${article.id}</div>

            </td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.regDateForPrint}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.updateDateForPrint}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.body}</td>
          </tr>
        </tbody>

      </table>
    </div>
    <div class="btn:s">
      <button class ="btn btn-link" type="button" onclick="history.back()">뒤로가기</button>
      <c:if test="article.extra__actorCanModify">
      <a class ="btn btn-link" href="../article/modify?id=${article.id}">게시물 수정</a>
      </c:if>
      <c:if test="article.extra__actorCanDelete">
        <a class ="btn btn-link" onclick ="if(confirm('정말 삭제하시겠습니까?')==false) return false" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
      </c:if>

    </div>
  </div>

</section>
<%@ include file = "../common/foot.jspf%>