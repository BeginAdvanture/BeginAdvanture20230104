<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding= "UTF-8"%>

<c:set var="pageTitle" value="게시물 수정" />
<%@ include file = "../common/head.jspf%>
<script>
  let ArticleModify__submitDone = false;
  function ArticleModify__submit(form){
    if(ArticleModify__submitDone){
    return;
  }
    form.body.value = form.body.value.trim();
    if(form.body.value.length == 0){
      alert('내용을 입력하세요.');
      form.body.focus();
      return;
    }
    ArticleModify__submitDone = true;
    form.submit();
}
</script>
<section class="mt-5 con-min-width">
  <div class="con mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../article/doModify" onsubmit="ArticleModify__submit(this); return false;">
      <input type="hidden" name="id" value="${article.id}">

      <table border="1">
        <colgroup>
          <col width="200"/>
        </colgroup>
        <tbody>
        <tr>
          <th>번호 </th>
          <td>
            <div class="badge badge-primary">
            ${article.id}
            </div>
          </td>
        </tr>
        <tr>
          <th>작성날짜</th>
          <td>${article.regDate}</td>
        </tr>
        <tr>
          <th>수정날짜</th>
          <td>${article.updateDate}</td>
        </tr>
        <tr>
          <th>조회</th>
          <span class="badge badge-primary article-detail__hit-count">${article.hitCount}</span>
        </tr>
        <tr>
          <th>추천</th>
          <span class="badge badge-primary">${article.goodReactionPoint}</span>
        </tr>
        <tr>
          <th>작성자</th>
          <td>${article.extra__writerName}</td>
        </tr>
        <tr>
          <th>제목</th>
          <td>
            <input name ="title" class="input input-bordered w-96 max-w-xs" type="text" placeholder="제목" value="${article.title}">
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <textarea class="textarea textarea-ghost w-full" name="body" rows="10" placeholder="내용" >${article.body}</textarea>
          </td>
        </tr>
        <tr>
          <th>수정</th>
          <td>
            <button type ="submit" class="btn btn-primary">로그인</button>
            <button type="text" onclick="history.back();" class="btn btn-outline btn-secondary">뒤로가기</button>
          </td>
        </tr>
        </tbody>

      </table>
    </form>
    <div class="btn:s">
      <button class="btn btn-link"> type="button" onclick="history.back()">뒤로가기</button>
      <a class ="btn btn-link" href="../article/modify?id=${article.id}">게시물 수정</a>
      <c:if test="article.extra__actorCanModify">
      <a class="btn btn-link"> href="../article/modify?id=${article.id}">게시물 수정</a>
      </c:if>
      <c:if test="article.extra__actorCanDelete">
        <a class="btn btn-link"> onclick ="if(confirm('정말 삭제하시겠습니까?')==false) return false" href="../article/doDelete?id=${article.id}">게시물 삭제</a>
      </c:if>

    </div>
  </div>

</section>
<%@ include file = "../common/foot.jspf%>