<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 8.9.13
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="frod.media.image.thumbnail.adjustment.crop.CropAdjustment; org.imgscalr.Scalr; frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fotogalerie ${group.name} | ${website.name}</title>
    <meta name="layout" content="default"/>
</head>

<body>
<div class="row-fluid content-block">
<div class="span12">
    <h1>Fotogalerie ${group.name}</h1>
    <ul class="photos">
        <g:each in="${media}" var="m">
        <li>
            <a class="fancybox" href="${frodm.imgUri([media: m, adjustments: [new ResizeAdjustment(1000, 800, Scalr.Mode.AUTOMATIC)]])}" rel="gallery">
            <frodm:img media="${m}"
                       adjustments="${[new ResizeAdjustment(198, 166, Scalr.Mode.AUTOMATIC)]}"/>
            </a>
        </li>
        </g:each>
    </ul>
</div>

</div>
</body>
</html>