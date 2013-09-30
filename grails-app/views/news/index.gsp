<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 6.9.13
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Aktuality</title>
    <meta name="layout" content="default" />
</head>
<body>
<div class="row-fluid content-block">
    <div class="span3 clearfix submenu">
        <skolka:submenu/>
    </div>
    <div class="span9">
    <h1>Aktuality</h1>
        <table class="table">
            <g:each in="${allNews}" var="news">
                <tr>
                    <td><g:formatDate date="${news.date}" format="d. M. yyyy" style="LONG"/></td>
                    <td>${news.name}</td>
                    <td>${news.content}</td>
                </tr>
            </g:each>
        </table>
    </div>
</div>


</body>
</html>