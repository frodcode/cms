<%@ page contentType="text/html;charset=UTF-8" %>
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

<body ng-app="app">
<div id="header">
    <h1><a href="http://wbpreview.com/previews/WB0F35928/dashboard.html">Školka</a></h1>
</div>
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav btn-group">
        <li class="btn btn-inverse" ><g:link mapping="myAccount" params="[websiteSlug: params.websiteSlug]"><i class="icon icon-user"></i> <span class="text">Nastavení účtu</span></g:link></li>
        <li class="btn btn-inverse"><g:link controller="logout" action="index"><i class="icon icon-share-alt"></i> <span class="text">Odhlásit</span></g:link></li>
    </ul>
</div>
<div id="sidebar">
    <skolka:adminMenu/>

</div>
<r:layoutResources/>
<g:layoutBody/>
<script>
app.invoke(function (appModuleFactory) {
    appModuleFactory.createAppModule();
});
</script>
</body>
</html>