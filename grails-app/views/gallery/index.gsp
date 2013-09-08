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
    <meta name="layout" content="default" />
</head>
<body>
<div class="row-fluid content-block">

    <div class="span12">
        <ul class="gallery">
            <g:each in="${allGroups}" var="group">
                <g:set var="media" value="${thumbnails.containsKey(group.id) ? thumbnails[group.id] : null}"/>
                %{--<g:set var="imgLink" value="${media ? createLink([controller: 'imageService', id: keyParser.getUrlPart(media.mainImage, [new ResizeAdjustment(200, 200, Scalr.Mode.AUTOMATIC)])]) : null}"/>--}%
                <a href="?${group.name}">
                    <span>${group.name}</span>
                    %{--<img src="'.$item['image'].'"></a>--}%
                </a>
            </g:each>
        </ul>
    <?php } ?>
    </div>
</div>
</body>
</html>