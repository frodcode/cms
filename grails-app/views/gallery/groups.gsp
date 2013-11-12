<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 8.9.13
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.imgscalr.Scalr; frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fotogalerie | ${website.name}</title>
    <meta name="layout" content="default"/>
</head>

<body>
<div class="row-fluid content-block">

    <div class="span12">
        <ul class="gallery">
            <g:if test="${allGroups.size() == 0}">
                <g:if test="${website.slug == 'troilova'}">
                    <p>Fotografie si můžete prohlédnout <a href="http://mstroilova.rajce.idnes.cz/" target="_blank">na rajčeti</a>.</p>
                </g:if>
                <g:if test="${website.slug == 'utulna'}">
                    <p>Fotografie si můžete prohlédnout <a href="http://msutulna.rajce.idnes.cz/" target="_blank">na rajčeti</a>.</p>
                </g:if>

            </g:if>
            <g:each in="${allGroups}" var="group">
                <li>
                    <g:set var="media" value="${thumbnails.containsKey(group.id) ? thumbnails[group.id] : null}"/>
                    <a href="?galerie=${group.id}">
                        <span>${group.name}</span>
                        <g:if test="${media}">
                            <frodm:img media="${thumbnails[group.id]}"
                                       adjustments="${[new ResizeAdjustment(260, 195, Scalr.Mode.AUTOMATIC)]}"/>
                        </g:if>
                    </a>
                </li>
            </g:each>
        </ul>
    </div>
</div>
</body>
</html>