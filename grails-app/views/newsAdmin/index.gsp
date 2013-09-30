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
    <title>Aktuality</title>
</head>
<body>
<div id="content">
    <div id="content-header">
        <h1>Aktuality (max posledních ${count})</h1>

    </div>

    <div class="container-fluid" ng-controller="CalendarCtrl">
        <div class="row-fluid">

            <div class="span12">
                <skolka:messages/>
                <g:link action="detail" params="[websiteSlug: website.slug]" class="btn"><i class="icon-plus"></i> Přidat novinku</g:link>
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Název</th>
                                <th>Datum</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <g:each in="${allNews}" var="news">
                                <tr>
                                    <td>${news.id}</td>
                                    <td>${news.name}</td>
                                    <td><g:formatDate date="${news.date}" format="d. M. yyyy" style="LONG"/></td>
                                    <td><g:link action="detail" params="[id: news.id, websiteSlug: website.slug]"
                                                class="btn btn-primary"><i
                                                class="icon-pencil icon-white"></i> upravit</g:link>
                                        <g:form action="delete" useToken="true" params="[websiteSlug: website.slug]" style="margin-top: 15px" onclick="return(confirm('Opravdu smazat?'))">
                                            <g:hiddenField name="id" value="${news.id}"/>
                                            <g:submitButton name="delete" class="btn btn-danger" value="smazat"/>
                                        </g:form>
                                    </td>
                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>