<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding= "UTF-8"%>

<c:set var="pageTitle" value="게시물 내용" />
<%@ include file = "../common/head.jspf%>
<script>
  const params = {};
  param.id = parseInt('${param.id}');
</script>
<script>
  function ArticleDetail__increaseHitCount(){
  const localStorageKey = 'article__'+${param.id}+'__viewDone';

  if(localStorage.getItem(localStorageKey)){
    return ;
  }
  localStorage.setItem(localStorageKey,true);

  $.get('../usr/article/doIncreaseHitCountRd',
    {
      id: params.id,
      ajaxMode: 'Y'
    }, function(data){
        $('.article-detail__hit-count').empty().html(data1.data1);
    }, 'json');
  }
  $(function){

     ArticleDetail__increaseHitCount();
  })
</script>

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
            <th>조회</th>
            <span class="badge badge-primary article-detail__hit-count">${article.hitCount}</span>
          </tr>
          <tr>
            <th>추천</th>
            <td>
              <div class="flex items-center">
                <span class="badge badge-primary ">${article.goodReactionPoint}</span>
                &nbsp;
                <c:if test="${actorCanMakeReaction}">
                <a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary btn-outline">
                  좋아요 👍
                </a>
                &nbsp;
                <a href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary btn-outline">
                  싫어요 😓
                </a>
                </c:if>

                <c:if test="${actorCanCancelGoodReaction}">
                  <a href="/usr/reactionPoint/doCancelGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary ">
                    좋아요 👍 (취소)
                  </a>
                  &nbsp;
                  <a onclick="alert(this.title); return false;" title="먼저 좋아요를 취소해주세요." href="#" class="btn btn-xs btn-primary btn-outline">
                    싫어요 😓
                  </a>
                </c:if>

                <c:if test="${actorCanCancelBadReaction}">
                  <a onclick="alert(this.title); return false;" title="먼저 싫어요를 취소해주세요." class="btn btn-xs btn-primary btn-outline"  href="#">
                    좋아요 👍 (취소)
                  </a>
                  &nbsp;
                  <a  href= href="/usr/reactionPoint/doCancelBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}" class="btn btn-xs btn-primary">
                    싫어요 😓
                  </a>
                </c:if>

              </div>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.forPrintBody}</td>
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
<script>
  let ReplyWrite__submitFormDone = false;
  function ReplyWrite__submitForm(form){
  if( ReplyWrite__submitFormDone) {
  return;
  }
  //좌우공백 제거
  form.body.value = form.body.value.trim();
  if(form.body.value.length ==0){
      alert('댓글을 입력해주세요.');
      .form.body.focus();
      return;
    }

   if(form.body.value.length <2 ){
      alert('댓글내용을 2자 이상 입력하세요.');
      .form.body.focus();
      return;
    }
    ReplyWrite__submitFormDone = true;
    form.submit();
  }
</script>
<section class="mt-5 con-min-width">
  <div class="con mx-auto px-3">
    <h1>댓글 작성</h1>
    <c:if test="${rq.logined}">
    <form class="table-box-type-1" method="POST" action="../reply/doWrite"
          onsubmit="ReplyWrite__submitForm(this); return false;">
      <input type="hidden" name="relTypeCode" value="article">
      <input type="hidden" name="relId" value="${article.id}">
      <table border="1">
        <colgroup>
          <col width="200"/>
        </colgroup>
        <tbody>
        <tr>
          <th>작성자</th>
          <td>
            ${rq.loginedMember.nickname}
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <textarea class="textarea textarea-ghost w-full" name="body" rows="5" placeholder="댓글 내용"></textarea>
          </td>
        </tr>
        <div class="flex justify-end my-3" >
          <button type ="submit" class="btn btn-primary">댓글 작성</button>
        </div>
        </tbody>
      </table>
    </form>>
    </c:if>
    <c:if test="${rq.notLogined}">
      <a class="link link-primary" href="/usr/member/login">로그인</a> 후 이용해주세요.
    </c:if>
  </div>
</section>>

<section class="mt-5 con-min-width">
  <div class="con mx-auto px-3">
    <h1>댓글 개수(${replies.size()})</h1>
    <div class="reply-list mt-3">
      <c:forEach var="reply" items="${replies}">
          <div class="reply-list__head">
            <div class="flex flex-col">
              <span class="font-bold">${reply.extra__writerName}</span>
              <span>${reply.forPrintType2RegDate}</span>
              <div class="flex">
                <span>추천 : ${reply.goodReactionPoint}</span>
                <div class="modifyAndDelete-btn ml-auto">
                  <c:if test="reply.extra__actorCanModify">
                    <a href="../reply/modify?id=${reply.id}">수정</a>
                  </c:if>
                  <c:if test="reply.extra__actorCanDeete">
                    <a onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false" href="../reply/doDelete?id=${reply.id}">삭제</a>
                  </c:if>
              </div>
              </div>
            </div>
          </div>
          <div class="reply-list__body border-b border-gray-300 mb-3">
            <div clas=" flex-items-center my-3">
              ${reply.forPrintBody}
            </div>
          </div>
        </c:forEach>
    </div>
  </div>
</section>
<%@ include file = "../common/foot.jspf%>