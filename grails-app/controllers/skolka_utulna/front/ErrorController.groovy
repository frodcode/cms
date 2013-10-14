package skolka_utulna.front

import frod.routing.service.RoutingService
import frod.routing.service.PageService
import frod.routing.domain.Page

class ErrorController {

    PageService pageService

    def error404() {
        def rootPage = Page.where {
            pageType.slug == 'Root'
        }.find()
        [rootPage: rootPage]
    }
}
