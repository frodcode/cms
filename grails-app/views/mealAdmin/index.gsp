<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 26.9.13
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
<div id="content">
    <div id="content-header">
        <h1>Jídelníček na <g:formatDate date="${currentMonth}" format="MMMM yyyy" style="LONG" /></h1>

    </div>
    <div class="container-fluid" ng-controller="CalendarCtrl">
        <div class="row-fluid">
            <div class="span12">
                <div class="pagination">
                    <ul>
                        <li class=""><g:link action="index" controller="MealAdmin" params="[websiteSlug: website.slug, monthNumber: previousMonth.month, yearNumber: previousMonth.year]"> &laquo; Předchozí měsíc</g:link></li>
                        <li class=""><g:link action="index" controller="MealAdmin" params="[websiteSlug: website.slug, monthNumber: nextMonth.month, yearNumber: nextMonth.year]">Další měsíc &raquo; </g:link></li>
                    </ul>
                </div>
                <table class="table calendar">
                <thead>
                    <tr class="">
                        <th>Pondělí</th>
                        <th>Úterý</th>
                        <th>Středa</th>
                        <th>Čtvrtek</th>
                        <th >Pátek</th>
                        <th></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${monthMenu.getWeekMenus()}" var="weekMenu">

                    <tr>
                        <g:each in="${weekMenu.dailyMenus}" var="dailyMenu">
                            <td>
                                <g:link controller="MealAdmin" action="detail" params="[websiteSlug: website.slug, id: weekMenu.sinceDate.format('yyyy-MM-dd')]">
                                    <span class="date">${dailyMenu.date.date}</span>
                                </g:link>
                                <div class="menus">
                                <g:each in="${dailyMenu.menus}" var="menu">

                                    <span>
                                        <g:if test="${menu.name}">
                                            <i class="icon-ok"></i>  ${menu.type}
                                        </g:if>

                                    </span>

                                </g:each>
                                </div>
                            </td>
                        </g:each>
                        <td><g:link controller="MealAdmin" action="detail" params="[websiteSlug: website.slug, id: weekMenu.sinceDate.format('yyyy-MM-dd')]">Upravit pro tento týden</g:link></td>
                    </tr>

                </g:each>
                </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>