modules = {
    root {
        resource url: '/static/skolka/css/bootstrap.css'
        resource url: '/static/skolka/css/main.css'
        resource url: '/static/skolka/libs/fancybox/jquery.fancybox.css?v=2.1.5'
        resource url: '/static/skolka/libs/fancybox/helpers/jquery.fancybox-buttons.css?v=1.0.5'
        resource url: '/static/skolka/libs/fancybox/helpers/jquery.fancybox-thumbs.css?v=1.0.7'
        resource url: '/static/skolka/css/bootstrap-responsive.css'
    }

    skolka {
        dependsOn 'root'
        resource url: '/static/skolka/js/jquery-1.10.2.min.js'
        resource url: '/static/skolka/js/bootstrap.js'
        resource url: '/static/skolka/libs/fancybox/jquery.fancybox.pack.js?v=2.1.5'
        resource url: '/static/skolka/libs/fancybox/helpers/jquery.fancybox-buttons.js?v=1.0.5'
        resource url: '/static/skolka/libs/fancybox/helpers/jquery.fancybox-media.js?v=1.0.6'
        resource url: '/static/skolka/libs/fancybox/helpers/jquery.fancybox-thumbs.js?v=1.0.7'
    }

}