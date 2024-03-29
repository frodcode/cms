<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 4.10.13
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<g:set var="title" value="${article?.id ? 'Editace článku '+article.headline : 'Vytvoření nového článku'}" />
<head>
  <title>${title}</title>
    <script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
</head>
<body>
<frodadmin:angularTemplates dirs="/static/skolka/admin/js"/>
<div id="content">
    <div id="content-header">
        <h1>${title}</h1>
    </div>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <skolka:messages errors="${errors}" />
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-align-justify"></i>
                        </span>
                        <h5>Článek</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <g:form class="form-horizontal" action="${article.id ? 'save' : 'newArticle'}" params="[websiteSlug: website.slug, id : parentId]">
                            <div class="control-group">
                                <label class="control-label">Nadpis</label>
                                <div class="controls">
                                    <g:textField name="headline" value="${article?.headline}" id="headline"/><input type="hidden" id="slug" name="slug"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Text</label>
                                <div class="controls">
                                    <g:textArea name="text" rows="" cols="" value="${article?.text}"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <g:hiddenField name="id" value="${article?.page?.id}"/>
                                <g:hiddenField name="newParentId" value="${parentId}" />
                                <g:submitButton name="save" value="Uložit" class="btn btn-primary"/>
                            </div>
                            </div>
                        </g:form>
                    </div>
                <g:link action="index" params="[websiteSlug: website.slug]">Zpět na seznam článků</g:link>
                </div>


        </div>
    </div>
</div>
<script>
    updateSlug = function() {
        $("#slug").val(webalize($("#headline").val()))
    }
    $("#headline").keyup(function() {
        updateSlug();
    });
    updateSlug();
</script>
<!--<script type="text/javascript" src="/static/static/skolka/libs/tinymce/tinymce.min.js">
</script>-->
<script type="text/javascript">
    tinymce.init({
        selector: "textarea",
        min_height: 350,
        plugins: "table,autolink,link,code",
        content_css: '//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css'
    });
</script>

</body>
</html>