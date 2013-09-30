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
        def menuItem = menuItemService.findItemByPage(page)
        if (website) {
            return website
        } else if (menuItem) {
            return menuItem.mainMenuItem.website
        } else {
            return MainMenuItem.findByPage(page).website
        }
    }

    def findViaMainMenuPageByWebsiteAndPageTypeSlug(Website website, String pageTypeSlug) {
        MainMenuItem mainMenuItem = MainMenuItem.find("FROM MainMenuItem mmi JOIN FETCH mmi.page p JOIN FETCH p.pageType pt WHERE pt.slug = :pageTypeSlug AND mmi.website = :website", [pageTypeSlug: pageTypeSlug, website: website])
        return mainMenuItem.page
    }

    def findViaMenuItemPageByWebsiteAndPageTypeSlug(Website website, String pageTypeSlug) {
        MenuItem menuItem = MenuItem.find("FROM MenuItem m JOIN FETCH m.page p JOIN FETCH p.pageType pt JOIN FETCH m.mainMenuItem mmi WHERE pt.slug = :pageTypeSlug AND mmi.website = :website", [pageTypeSlug: pageTypeSlug, website: website])
        return menuItem.page

    }


}
