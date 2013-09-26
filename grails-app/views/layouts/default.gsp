<%--
  Created by IntelliJ IDEA.
  User: freeman
  Date: 6.9.13
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Mateřská škola Útulná</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Mateřská škola">
    <meta name="author" content="František Odehnal">

    <!-- Le styles -->
    <r:require modules="skolka"/>
    <r:layoutResources/>

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
<div class="container" id="main-container">
    <div class="row-fluid">
        <div class="pull-left">
            <skolka:head/>
        </div>
        <div class="pull-right back-to-cross">
            <h3><a href="../" class="btn btn-success">&laquo; zpět na výběr školky</a></h3>
        </div>
    </div>
    <div class="masthead">
    <skolka:mainMenu/>
    </div>
    <g:layoutBody />

    <footer class="footer">
        <p>&copy; Mateřská škola Útulná 2013</p>
    </footer>
</div> <!-- /container -->

<script>
    $(document).ready(function() {
        var slider = $('.carousel').carousel({
            interval: 444000
        })
                .bind('slid', function() {
                    var index = $(this).parent().find("div.active").index();
                    $(this).find("li").removeClass('active').eq(index).addClass('active');
                });

        $(".pagination a").click(function(e){
            var index = $(this).parent().index();
            slider.carousel(index);
            e.preventDefault();
        });

        $(".fancybox").attr('rel', 'gallery').fancybox({type: 'image'});
    });
</script>
<r:layoutResources/>
</body>
</html>
