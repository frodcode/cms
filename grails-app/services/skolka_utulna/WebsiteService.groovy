package skolka_utulna

import frod.routing.domain.Page

class WebsiteService {

    MenuItemService menuItemService

    def getWebsiteByForwardURI(String forwardURI) {
        def tokenized = forwardURI.tokenize('/')
        if (tokenized.size() == 0) {
            throw new IllegalArgumentException(sprintf('Forward uri "%s" does not contain any website slug', forwardURI))
        }
        def slug = tokenized.first()
        Website website = Website.findBySlug(slug)
        if (!website) {
            throw new IllegalArgumentException(sprintf('Cannot find any website with slug "%s". Forward URI was "%s"', slug, forwardURI))
        }
        return website;
    }

    def getWebsite(Page page) {
        def website = Website.findByHomepage(page)
        if (website) {
            return website
        } else {
            return menuItemService.findItemByPage(page).mainMenuItem.website
        }
    }

}
