<html lang="cz">
<head>
    <title>Změna hesla</title>
    <meta name="layout" content="admin" />
</head>
<body>
<div id="content">
    <div id="content-header">
        <h1>Můj účet</h1>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
            <skolka:messages/>
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon">
                            <i class="icon-align-justify"></i>
                        </span>
                        <h5>Nastavení účtu ${userInstance.username}</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <g:form mapping="myAccount" params="[websiteSlug: params.websiteSlug]" method="POST" class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label">Původní heslo</label>
                            <div class="controls">
                                <input type="password" name="original_password">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Nové heslo</label>

                            <div class="controls">
                                <input type="password" name="password">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">Nové heslo podruhé</label>

                            <div class="controls">
                                <input type="password" name="password2">
                            </div>
                        </div>
                            <div class="form-actions">
                                <g:hiddenField name="id" value="${userInstance.id}"/>
                                <button type="submit" class="btn btn-primary">Uložit</button>
                            </div>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

