class UrlMappings {
    static excludes = ['/admin/css/*', '/admin/img/*', '/admin/js/*', '/static/*', '/css/*', '/plugins/*']
    static mappings = {
        name dynamicImage: "/dynamic-media/image/$id?"{
            controller = 'ImageService'
            action = 'index'
            constraints {
                // apply constraints here
            }
        }
        "/$websiteSlug/admin/$controller/$action/$yearNumber?/$monthNumber?"{
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
        "/**"{
            controller = "Front"
            action = "route"
            constraints {
                // apply constraints here
            }
        }
        "500"(view:'/error')
    }
}
