<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table id="table-1">
    <tr>
        <td><h3> BidProduct: Home</h3><h4>( MVC )</h4></td>
    </tr>
</table>

<p>This is the Home page for MyMyDearFriend BidProduct: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='listAllBidProduct.jsp'>List</a> all Bid Products. <br><br></li>

    <li>
        <form METHOD="post" ACTION="bidProduct.do">
            <b>輸入競標商品編號 (如 3):</b>
            <input type="text" name="bidProductId">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </form>
    </li>

    <jsp:useBean id="bidProductService" scope="page" class="bidproduct.service.BidProductService"/>

    <li>
        <form METHOD="post" ACTION="bidProduct.do">
            <b>選擇競標商品編號:</b>
            <select size="1" name="bidProductId">
                <c:forEach var="bidProductVO" items="${bidProductService.all}">
                <option value="${bidProductVO.bidProductId}">${bidProductVO.bidProductId}
                    </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </form>
    </li>

    <li>
        <form METHOD="post" ACTION="bidProduct.do">
            <b>選擇競標商品名稱:</b>
            <select size="1" name="bidProductId">
                <c:forEach var="bidProductVO" items="${bidProductService.all}">
                <option value="${bidProductVO.bidProductId}">${bidProductVO.name}
                    </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </form>
    </li>
</ul>


<h3>競標商品管理</h3>

<ul>
    <li><a href="addBidProduct.jsp">Add</a> a new Bid Product.</li>
</ul>

</body>
</html>
