<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 27.9.13
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
<frodadmin:angularTemplates dirs="/static/skolka/admin/js"/>
<div id="content">
    <div id="content-header">
        <h1>Jídelníček od <g:formatDate date="${editingWeek.sinceDate}" format="dd. MM." style="LONG" /> do <g:formatDate date="${editingWeek.toDate}" format="dd. MM. yyyy" style="LONG" /></h1>

    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <detail-page>

                </detail-page>
                <g:link action="index" params="[websiteSlug: website.slug]">Zpět na kalendář</g:link>
            </div>
        </div>
    </div>
</div>

<script>
    app.invoke(function (appModuleFactory, mealModuleFactory) {
        var dailyMenuData = mealModuleFactory(${dailyMenuData}, '${createLink(action: 'save', params: ['websiteSlug': website.slug])}');
        appModuleFactory.add(dailyMenuData);
    });
</script>
</body>
</html>