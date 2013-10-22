<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 11.10.13
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Stránka nenalezena</title>
  <meta name="layout" content="null" />
    <r:require modules="root"/>
    <r:layoutResources/>
    <style>
#main-container {
    padding: 40px 40px 40px 40px;
}
    </style>
</head>

<body>
<div class="container" id="main-container">
<div class="row-fluid content-block">

    <div class="span12">
        <h1>Stránka nenalezena</h1>
        <p>Litujeme, ale požadovaná stránka není na serveru
        k dispozici. Omlouváme se za způsobené potíže.</p>
        <p>Stránka nemůže být zobrazena z následujících důvodů:</p>
        <ul>
            <li>Stránka byla odstraněna.
            <li>Adresa byla chybně napsána nebo vložena do adresního řádku prohlížeče.</li>
        </ul>
        <a href="/">Přejít na domovskou stránku</a>
    </div>
</div>
    </div>
</body>
</html>
