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
        <h1>Jídelníček</h1>
        <div class="btn-group">
            <a class="btn btn-large tip-bottom" title="Manage Files"><i class="icon-file"></i></a>
            <a class="btn btn-large tip-bottom" title="Manage Users"><i class="icon-user"></i></a>
            <a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a>
            <a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>
        </div>
    </div>
    <div id="breadcrumb">
        <a href="index.html#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="index.html#" class="current">Dashboard</a>
    </div>
    <div class="container-fluid" ng-controller="CalendarCtrl">
        <div class="row-fluid">
            <div class="span12">
                Tady je angular promenna: {{message}} a link je {{loadCalendarLink}}
                <table class="fc-border-separate">
                <thead>
                    <tr class="fc-first fc-last">
                        <th class="fc-sun fc-widget-header fc-first" style="width: 243px;">Pondělí</th>
                        <th class="fc-mon fc-widget-header" style="width: 242px;">Úterý</th>
                        <th class="fc-tue fc-widget-header" style="width: 242px;">Středa</th>
                        <th class="fc-wed fc-widget-header" style="width: 242px;">Čtvrtek</th>
                        <th class="fc-thu fc-widget-header" style="width: 242px;">Pátek</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${monthMenu.getWeekMenus()}" var="weekMenu">
                    <tr class="fc-week0 fc-first">
                        <g:each in="${weekMenu.dailyMenus}" var="dailyMenu">
                            <td class="fc-sun fc-widget-content fc-day0 fc-first">
                                <g:each in="${dailyMenu.menus}" var="menu">
                                    <span>
                                        <g:if test="${menu.name}">
                                            <i class="icon-ok"></i>
                                        </g:if>
                                        ${menu.type}
                                    </span>
                                </g:each>
                            </td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    app.invoke(function (appModuleFactory, mealModuleFactory) {
        var mealModuleFactory = mealModuleFactory('This should be the link');
        appModuleFactory.add(mealModuleFactory);
    });
</script>
</body>
</html>