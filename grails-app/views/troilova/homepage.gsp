<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 6.9.13
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="default" />
</head>
<body>

<div class="row-fluid content-block">
    <div class="span8">
        <article class="clearfix">
            <h1>O školce</h1>
            <p>Troilova popis - chybí dodělat</p>
            <p class="pull-right"><frodr:link page="${oSkolcePage}" class="btn btn-success">Zobrazit více o školce &raquo;</frodr:link></p>
        </article>

    </div>
    <div class="span4 colored-box">
        <r:img uri="/static/skolka/img/utulna2.jpg" alt="foto školky"/>
    </div>
</div>


<div class="row-fluid content-block">
    <div class="span4 colored-box">
        <h3><frodr:link page="${naseTridyPage}">Naše třídy</frodr:link></h3>
        <frodr:link page="${naseTridyPage}">
            <r:img uri="/static/skolka/img/tridy.jpg" alt="tridy"/>
        </frodr:link>
    </div>

    <div class="span4 colored-box">
        <h3><a href="index.php?page=foto">Fotogalerie</a></h3>
        <frodr:link page="${fotogaleriePage}">
            <r:img uri="/static/skolka/img/fotogalerie.jpg" alt="fotogalerie"/>
        </frodr:link>
    </div>
    <div class="span4 colored-box">
        <h3><frodr:link page="${jidelnicekPage}">Jídelníček</frodr:link></h3>
        <frodr:link page="${jidelnicekPage}"><r:img uri="/static/skolka/img/jidelna.jpg" alt="jidelnicek"/></frodr:link>
    </div>
</div>
<div class="row-fluid content-block">
    <div class="span8 carousel slide">
        <div class="row-fluid">
            <div class="span6">
                <h3>Aktuality</h3>
            </div>
            <!-- Carousel nav -->
            <div class="span6">
                <div class="pagination pull-right">
                    <ul>
                        <g:each in="${homepageNews}" var="news" status="i">
                            <g:set var="number" value="${i + 1}"/>
                            <g:set var="active" value="${i == 0 ? 'active' : ''}"/>
                            <li class="${active}"><a href="#"  data-slide-to="${i}">${number}</a></li>
                        </g:each>
                    </ul>
                </div>

            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <!-- Carousel items -->
                <div class="carousel-inner">
                    <g:each in="${homepageNews}" var="news" status="i">
                        <g:set var="active" value="${i == 0 ? 'active' : ''}"/>
                        <div class="${active} item">
                        <h4>${news.name}</h4>

                        <p class="date"><g:formatDate date="${news.date}" format="d. M. yyyy" style="LONG"/></p>

                        <p>${news.content}</p>

                        <p class="pull-right"><frodr:link page="${newsPage}">Zobrazit všechny aktuality</frodr:link></p>
                        </div>
                    </g:each>
                </div>

            </div>
        </div>
    </div>
    <div class="span4 contact">
        <h3>Kontaktujte nás</h3>
        <address>
            <strong>MŠ Troilova</strong><br>
            Útulná 6/2099<br>
            Praha 10 100 00<br>
            <strong>Telefony:</strong><br>

            274 770 013<br>
            602 539 338 - ředitelka školky<br>
            724 585 436 - omluvy, paní Gregorová<br>
            <a href="mailto:mts.utulna@seznam.cz">mts.utulna@seznam.cz</a>
        </address>
    </div>
</div>
</body>
</html>