<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:set var="title" value="${news.name? news.name : 'Vytvoření aktuality'}"/>
    <title>${title}</title>
</head>

<body>
<div id="content">
    <div id="content-header">
        <h1>${title}</h1>
    </div>

    <div class="container-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="icon-align-justify"></i>
                    </span>
                    <h5>Informace o aktualitě</h5>
                </div>
                <div class="widget-content nopadding">
                    <g:form action="save" params="[websiteSlug: website.slug]" class="form-horizontal" method="post">
                        <div class="control-group">
                            <label class="control-label">Název aktuality</label>
                            <div class="controls">
                                <g:textField name="name" value="${news.name}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Datum</label>
                            <div class="controls">
                                <input name="dateString" type="text" data-date="${news.date.format('d.M.yyyy')}" data-date-format="d.m.yyyy" value="${news.date.format('d.M.yyyy')}" class="datepicker" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Zpráva</label>
                            <div class="controls">
                                <g:textArea name="content" value="${news.content}" rows="" cols="" id="content_news"/>
                            </div>
                        </div>
                        <div class="form-actions">
                            <g:hiddenField name="id" value="${news.id}"/>
                            <input type="submit" value="Uložit" class="btn btn-primary">
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>