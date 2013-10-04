<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 4.10.13
  Time: 14:12
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
        <h1>Editace článku ${article.headline}</h1>

    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <article-edit-page>

                </article-edit-page>
                <g:link action="index" params="[websiteSlug: website.slug]">Zpět na seznam článků</g:link>
            </div>
        </div>
    </div>
</div>

<script>
    app.invoke(function (appModuleFactory, articleModuleFactory) {
        var articleModule = articleModuleFactory(${articleData}, '${createLink(action: 'save', params: ['websiteSlug': website.slug])}');
        appModuleFactory.add(articleModule);
    });
</script>
</body>
</html>