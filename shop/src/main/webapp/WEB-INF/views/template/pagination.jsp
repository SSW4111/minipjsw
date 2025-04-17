<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
          /*
            페이지 네비게이터 디자인
        */
        .page-navigator{
            text-align: center;
        }
        .page-navigator > a{
            text-decoration: none;
            color:#636e72;
            font-size:16px;
            padding: 0.5em;
            display: inline-block;
            min-width: 2.5em;
            text-align: center;
            font-weight:bold;
            /* border : 1px solid transparent; */
        }
        .page-navigator > a:hover,
        .page-navigator > a.on 
        {
            /* border : 1px solid #636e72; */
            box-shadow : 0 0 2px 0 #0984e3;
            color:#0984e3
        }

         /*
            float box 디자인
            - 가상 선택자(virtual selector)을 이용해서 영역의 끝 지점을 생성
            - 시작 지점(::befor), 끝지점(::after)을 생성할 수 있음
            - 원래 없는 영역이기 때문에 생성을 위해서 내용물을 작성해야함
        */
        .float-box::after{
            content : "";
            display:block;
            clear:both;
        }
        .float-box > .float-left{
            float:left;
        }
        .float-box > .float-right{
            float:right;
        }
        
        .flex-box{
            display:flex;
            flex-direction: row;
        }
        .inline-flex-box{
            display:inline-flex;
            flex-direction: row;
        }

        .flex-box.flex-vertical,
        .inline-flex-box.flex-vertical
        {
            flex-direction:column;
        }

        .flex-box.flex-center,
        .inline-flex-box.flex-center{
            justify-content: center;
            align-items: center;
        }
        .flex-box> .flex-fill,
        .inline-flex-box > .flex-fill{
            flex-grow: 1;
        }
</style>
<!-- 페이지 네비게이터 : pageVO에 기반하여 처리하도록 구현 -->
<div class="page-navigator">


<!-- 이전-->
<c:if test="${adminItemVO.hasPrevBlock()}">
	<a href="item-list?page=${adminItemVO.prevBlock}&${adminItemVO.parameters}">
	&lt;
	</a>
</c:if>
 
<!-- 숫자 -->
<c:forEach var="i" begin="${adminItemVO.startBlock}" end="${adminItemVO.finishBlock}" step="1">
	<c:choose>
		<c:when test="${adminItemVO.page ==i}">
	<a class="on">${i}</a>
	</c:when>
	<c:otherwise>
	<a href="item-list?page=${i}&${adminItemVO.parameters}">${i}</a>
	</c:otherwise>
	</c:choose>
	</c:forEach>


<!-- 다음 -->
<c:if test="${adminItemVO.hasNextBlock()}">
	<a href ="item-list?page=${adminItemVO.nextBlock}&${adminItemVO.parameters}">
	&gt;
	</a>
</c:if>

</div>