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
    application {
        resource url: 'js/application.js'
    }

    adminskolka {
        dependsOn 'admintypical'
        resource url: '/static/skolka/libs/moment/moment.min.js'
        resource url: '/static/skolka/libs/moment/cs.js'
        resource url: '/static/skolka/common/Application.js'
        resource url: '/static/skolka/common/appModuleFactory.js'
//        resource url: '/static/skolka/common/datePickerCz.js'
        resource url: '/static/routing/page/model/PageNode.js'
        resource url: '/static/routing/page/model/PageNodeFactory.js'
        resource url: '/static/routing/page/model/UrlTypeEnum.js'
        resource url: '/static/routing/page/PageCtrl.js'
        resource url: '/static/routing/page/PageDirective.js'
        resource url: '/static/routing/page/config/PageConfig.js'
        resource url: '/static/routing/page/pageModuleFactory.js'

        resource url: '/static/routing/webalize/WebalizeCtrl.js'
        resource url: '/static/routing/webalize/WebalizeDirective.js'
        resource url: '/static/routing/webalize/webalizeFunction.js'
        resource url: '/static/routing/webalize/webalizeModuleFactory.js'

        resource url: '/static/skolka/admin/js/save/saveModuleFactory.js'
        resource url: '/static/skolka/admin/js/save/SaveButtonDirective.js'
        resource url: '/static/skolka/admin/js/save/SaveState.js'

        resource url: '/static/skolka/common/data/DataCollector.js'

        resource url: '/static/skolka/admin/js/meal/dailyMenu/DailyMenuCtrl.js'
        resource url: '/static/skolka/admin/js/meal/dailyMenu/DailyMenuDirective.js'
        resource url: '/static/skolka/admin/js/meal/mealModuleFactoryFactory.js'
        resource url: '/static/skolka/admin/js/meal/detailPage/DetailPageCtrl.js'
        resource url: '/static/skolka/admin/js/meal/detailPage/DetailPageDirective.js'
        resource url: '/static/skolka/admin/js/meal/model/DailyMenu.js'
        resource url: '/static/skolka/admin/js/meal/model/Menu.js'

        resource url: '/static/skolka/admin/js/article/editPage/EditPageCtrl.js'
        resource url: '/static/skolka/admin/js/article/editPage/EditPageDirective.js'
        resource url: '/static/skolka/admin/js/article/articleModuleFactoryFactory.js'

        resource url: '/static/skolka/admin/js/appConfig.js'

        resource url: '/static/skolka/admin/css/back.css'
    }
}