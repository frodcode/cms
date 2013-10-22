<html lang="cz">
<head>
    <title>Přihlášení do administrace</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <r:require modules="adminlogin"/>
    <r:layoutResources/>
    <style>
    input[type=text], input[type=password] {
        height: 30px;
    }
    </style>
</head>
<body>
<div id="logo">
</div>

<div class="container-fluid" ng-controller="CalendarCtrl">
    <div class="row-fluid">

        <div class="span12" style="text-align: center">

        </div>
    </div>
</div>

<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${postUrl}" method="POST">
        <p>Na tuto operaci nemáte dostatečná oprávnění.</p>
        <p><g:link controller="logout" action="index"><i class="icon icon-share-alt"></i> <span class="text">Odhlásit</span></g:link></p>
    </form>
    <r:layoutResources/>
</div>
</body>
</html>

