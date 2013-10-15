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
    <g:set var="websiteSlug" value="${website ? website.slug : 'troilova'}"/>
    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/static/static/skolka/img/ico/${websiteSlug}/144x144.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/static/static/skolka/img/ico/${websiteSlug}/114x114.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/static/static/skolka/img/ico/${websiteSlug}/72x72.png">

    <link rel="shortcut icon" href="/static/static/skolka/img/ico/${websiteSlug}/favicon.ico">
</head>

<body>
<r:layoutResources />
<div id="horizon">
    <div class="container" id="main-container">
        <div class="row-fluid content-block">
            <div class="span6">
                <frodr:link page="${homepageTroilovaPage}">
                    <r:img uri="/static/skolka/img/small/logo-troilova-s-textem-400x400.png"
                           alt="logo - přejít na stránky školky Troilova" title="Přejít na stránky školky Troilova"/>
                </frodr:link>
            </div>
            <div class=" span6">
                <frodr:link page="${homepageUtulnaPage}">
                    <r:img uri="/static/skolka/img/small/logo-utulna-s-textem-400x400.png" alt="logo - přejít stránky školky Útulná" title="Přejít na stránky školky Útulná" />
                </frodr:link>
            </div>
        </div>
    </div> <!-- /container -->
</div>
</body>
</html>

