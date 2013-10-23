class UrlMappings {
    static excludes = ['/admin/css/*', '/admin/img/*', '/admin/js/*', '/static/*', '/css/*', '/plugins/*', '/favicon.ico', '/tinymce/*']
    static mappings = {
        name dynamicImage: "/dynamic-media/image/$id?"{
            controller = 'ImageService'
            action = 'index'
            constraints {
                // apply constraints here
            }
        }
        name myAccount: "/$websiteSlug/admin/account"{
            controller = "User"
            action = "myAccount"
            constraints {
                // apply constraints here
            }
        }
        "/login/$action"{
            controller = 'Login'
            constraints {
                // apply constraints here
            }
        }
        "/logout/$action"{
            controller = 'Logout'
            constraints {
                // apply constraints here
            }
        }

        "/$websiteSlug/admin"{
            controller = 'MealAdmin'
            action = 'index'
            namespace = 'admin'
            constraints {
                // apply constraints here
            }
        }
        name classic_admin: "/$websiteSlug/admin/$controller/$action/$yearNumber?/$monthNumber?"{
            namespace = 'admin'
            constraints {
                // apply constraints here
            }
        }
        "/$websiteSlug/admin/$controller/$action/$id?"{
            namespace = 'admin'
            constraints {
                // apply constraints here
            }
        }
//        "/superadmin/$controller/$action/$id?"{
//            namespace = 'superadmin'
//            constraints {
//                controller(nullable:false, blank:false)
//                action(nullable:false, blank:false)
//            }
//        }
        "/**"{
            controller = "Front"
            action = "route"
            constraints {
                // apply constraints here
            }
        }
//        "404"(controller:'errors', action:'error404')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
