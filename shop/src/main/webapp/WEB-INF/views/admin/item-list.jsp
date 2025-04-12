<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



    <table>
        <thead>
            <tr>
                <th>Item No</th>
                <th>Title</th>
                <th>Color</th>
                <th>Gender</th>
                <th>Category</th>
                <th>Details</th>
                <th>Average Star</th>
                <th>Review Count</th>
                <th>Likes</th>
                <th>Price</th>
                <th>Content</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${list}">
                <tr>
                    <td>${item.itemNo}</td>
                    <td>${item.itemTitle}</td>
                    <td>${item.itemColor}</td>
                    <td>${item.itemGender}</td>
                    <td>${item.itemCategory}</td>
                    <td>${item.itemDetail}</td>
                    <td>${item.itemAveStar}</td>
                    <td>${item.itemReviewsCount}</td>
                    <td>${item.itemLike}</td>
                    <td>${item.itemPrice}</td>
                    <td>${item.itemContent}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<div>
    <form action="item-list" method="get">
        <select name="column" class="field">
        <option value="" <c:if test="${empty param.column}">selected</c:if>>선택하세요</option>
            <option value="item_title" ${param.column == 'item_title' ? 'selected' : ''}>이름</option>
            <option value="item_color" ${param.column == 'item_color' ? 'selected' : ''}>컬러</option>
        </select>
        <input type="text" name="keyword" value="${param.keyword}" class="field">
        <button class="btn btn-positive">검색</button>

        <button type="submit" name="highStar" value="true" >별점순</button>
        <button type="submit" name="recent" value="true">최신순</button>
    </form>
</div>

