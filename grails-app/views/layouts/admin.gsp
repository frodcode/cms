<%@ page import="skolka_utulna.Website" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <r:require modules="adminskolka,tinymce"/>
    <title><g:layoutTitle default="CMS"/></title>
    <g:layoutHead />
    <r:layoutResources/>
    <style>
#header h1 {
    background: url("/static/static/skolka/img/ico/${params.websiteSlug}/favicon.ico") no-repeat;
}
    </style>
</head>
<g:set var="layoutWebsite" value="${params.websiteSlug ? Website.findBySlug(params.websiteSlug) : null}"/>
<body ng-app="app">
<div id="header">
    <h1><a href=#">Školka</a></h1>
</div>
<g:if test="${layoutWebsite?.id}">
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse" ><a href="${Website.findBySlug(params.websiteSlug).homepage.url}"><i class="icon icon-star"></i> <span class="text">Zobrazit stránky</span></a></li>
        <li class="btn btn-inverse" ><g:link mapping="myAccount" params="[websiteSlug: params.websiteSlug]"><i class="icon icon-user"></i> <span class="text">Nastavení účtu</span></g:link></li>
        <li class="btn btn-inverse"><g:link controller="logout" action="index"><i class="icon icon-share-alt"></i> <span class="text">Odhlásit</span></g:link></li>
    </ul>
</div>
<div id="sidebar">
    <skolka:adminMenu/>

</div>
</g:if>
<r:layoutResources/>
<g:layoutBody/>
<script>
app.invoke(function (appModuleFactory) {
    appModuleFactory.createAppModule();
});
</script>
</body>
</html>
