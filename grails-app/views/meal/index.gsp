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
  <title></title>
    <meta name="layout" content="default" />
</head>
<body>
<div class="row-fluid content-block">
    <h1>Jídelníček od 24.6.2013 do 28.6.2013</h1>
    <table class="table">
        <g:each in="${weeklyMenu.dailyMenus}" var="dailyMenu">
            <tr>
                <td class="day" colspan=3><b><g:formatDate date="${dailyMenu.date}" format="EEEE" style="LONG" /></b></td>
            </tr>
            <tr>
                <g:each in="${dailyMenu.menus}" var="menu">
                    <td><B>${menu.type}</B><br />${menu.name}</td>
                </g:each>
            </tr>
        </g:each>
    </table>
</div>


</body>
</html>