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
    <title></title>
    <meta name="layout" content="default"/>
</head>

<body>
<div class="row-fluid content-block">

    <div class="span12">
        <ul class="gallery">
            <g:each in="${allGroups}" var="group">
                <li>
                    <g:set var="media" value="${thumbnails.containsKey(group.id) ? thumbnails[group.id] : null}"/>
                    <a href="?galerie=${group.name}">
                        <span>${group.name}</span>
                        <g:if test="${media}">
                            <frodm:img media="${thumbnails[group.id]}"
                                       adjustments="${[new ResizeAdjustment(260, 195, Scalr.Mode.AUTOMATIC)]}"/>
                        </g:if>
                    </a>
                </li>
            </g:each>
        </ul>
    <?php } ?>
    </div>
</div>
</body>
</html>