package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.Website
import skolka_utulna.WebsiteService

class RootController {

    WebsiteService websiteService

    def index() {
        def homepageUtulnaPage = Website.findBySlug('utulna').homepage
        def homepageTroilovaPage = Website.findBySlug('troilova').homepage
        if (!homepageUtulnaPage) {
            throw IllegalStateException('Website utulna does not have homepage')
        }
        [homepageUtulnaPage: homepageUtulnaPage, homepageTroilovaPage: homepageTroilovaPage]
    }
}
