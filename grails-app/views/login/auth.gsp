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
            <skolka:messages/>
        </div>
    </div>
</div>

<div id="loginbox">
    <form id="loginform" class="form-vertical" action="${postUrl}" method="POST">
        <p>Pro pokračování se prosím přihlašte.</p>

        <div class="control-group">
            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span><input type="text" name="j_username" placeholder="Uživatelské jméno" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-lock"></i></span><input type="password" name="j_password" placeholder="Heslo" />
                </div>
            </div>
        </div>
        %{--<p id="remember_me_holder">--}%
            %{--<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>--}%
            %{--<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>--}%
        %{--</p>--}%
        <div class="form-actions">
            <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Přihlásit" /></span>
        </div>
    </form>
    <r:layoutResources/>
</div>
</body>
</html>

