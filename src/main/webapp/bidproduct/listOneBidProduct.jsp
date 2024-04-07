<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bidproduct.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    BidProductVO bidProductVO = (BidProductVO) request.getAttribute("bidProductVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
    <title>競標商品資料 - listOneBidProduct.jsp</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 1000px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr><td>
        <h3>競標商品資料 - listOneBidProduct.jsp</h3>
        <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>競標商品編號</th>
        <th>賣家編號</th>
        <th>商品名稱</th>
        <th>商品類別</th>
        <th>商品描述</th>
        <th>起標價</th>
        <th>開始時間</th>
        <th>結束時間</th>
        <th>審核狀態</th>
        <th>競標狀態</th>
    </tr>
    <tr>
        <td><%= bidProductVO.getBidProductId() %></td>
        <td><%= bidProductVO.getSellerId() %></td>
        <td><%= bidProductVO.getName() %></td>
        <td><%= bidProductVO.getCategoryId() %></td>
        <td><%= bidProductVO.getDescription() %></td>
        <td><%= bidProductVO.getStartPrice() %></td>
        <td><%= bidProductVO.getStartTime() %></td>
        <td><%= bidProductVO.getEndTime() %></td>
        <td><%= bidProductVO.getReviewStatus() %></td>
        <td><%= bidProductVO.getBidStatus() %></td>
    </tr>
</table>

</body>
</html>