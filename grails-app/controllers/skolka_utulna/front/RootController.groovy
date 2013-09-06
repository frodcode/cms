package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.Website
import skolka_utulna.WebsiteService

class RootController {

    WebsiteService websiteService

    def index() {
        def homepageUtulnaPage = websiteService.findHomepageForWebsite(Website.findBySlug('utulna'))
        if (!homepageUtulnaPage) {
            throw IllegalStateException('Website utulna does not have homepage')
        }
        [homepageUtulnaPage: homepageUtulnaPage]
    }
}
