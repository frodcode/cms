<html>
<head>
    <title>${article.headline} | ${website.slug}</title>
    <meta name="layout" content="default" />
</head>
<body>
<div class="row-fluid content-block">
    <div class="span3 clearfix submenu">
        <skolka:submenu/>
    </div>
    <div class="span9">
        <h1>${article.headline}</h1>
        ${article.text}
    </div>
</div>

</body>
</html>