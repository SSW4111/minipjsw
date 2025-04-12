<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <h1>item등록</h1>
   <form action="add" method="post"  enctype="multipart/form-data">
   <input type="file" name="attach" multiple>
     <br>이름
   <input type="text" name="itemTitle">
     <br>카테고리
   <input type="text" name="itemCategory">
     <br>성별 F/M  hidden value(f/m)
   <input type="text" name="itemGender">
     <br>상세카테고리
   <input type="text" name="itemDetail">
   <br>컬러
   <input type="text" name="itemColor">
   <br>가격
   <input type="text" name="itemPrice">
     <br>내용 얘썸머노트
   <input type="text" name="itemContent">
   <br>
  <button type="submit">등록</button>
   </form>