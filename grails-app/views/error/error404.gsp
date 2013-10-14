<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 6.9.13
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Mateřská škola Troilova a Útulná</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<r:require modules="root"/>
<r:layoutResources/>

<style>
#horizon {
    color: white;
    text-align: center;
    position: absolute;
    top: 15%;
    left: 0px;
    width: 100%;
    height: 1px;
    overflow: visible;
    visibility: visible;
    display: block;
}

#main-container {
    padding: 40px 40px 40px 40px;
}

.content-block {
    padding: 10px;
}

.row-fluid {
    margin: 0;
}

</style>


<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
 <script src="static/js/html5shiv.js"></script>
<![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="static/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="static/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="static/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="static/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="static/ico/favicon.png">
</head>

<body>
<r:layoutResources />
<div id="horizon">
    <div class="container" id="main-container">
        <div class="row-fluid content-block">
            <h1>Stránka nenalezena</h1>
            <p>Litujeme, ale požadovaná stránka není na serveru
            k dispozici. Omlouváme se za způsobené potíže.</p>


            <h2>Možné příčiny chyby a jak dále postupovat:</h2>
            <p>Je možné, že stránka byla přesunuta na jiné místo, nebo již byla ze serveru odstraněna.</p>
            <p>Zkontrolujte také prosím, zdali vámi požadovaná adresa stránky neobsahuje překlep.</p>
            <p>Pokud se domníváte, že by požadovaná stránka měla existovat, nebo naleznete nefunkční odkaz, kontaktujte nás.</p>
            <p>Můžete <frodr:link page="${rootPage}">pokračovat zde na rozcestník školek</frodr:link></p>
        </div>
    </div> <!-- /container -->
</div>
</body>
</html>

