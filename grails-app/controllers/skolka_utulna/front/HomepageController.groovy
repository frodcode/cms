package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.Article
import skolka_utulna.WebsiteService
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.Website
import skolka_utulna.MainMenuItem
import skolka_utulna.MenuItemService
import skolka_utulna.MainMenuItemService
import frod.routing.service.RoutingService
import org.codehaus.groovy.grails.plugins.GrailsPluginUtils

class HomepageController {

    WebsiteService websiteService
    MainMenuItemService mainMenuItemService
    RoutingService routingService

    def index() {
        Page page = Page.get(params.pageId)
        Website website = Website.findByHomepage(page)

        Article homepageArticle = Article.findByPage(page)
        return new ModelAndView("/$website.slug/homepage",
                [homepageArticle: homepageArticle,
                        website: website,
                        naseTridyPage: mainMenuItemService.findPageForMainMenuItemWithSlug('nase_tridy'),
                        fotogaleriePage: routingService.getSingleton('gallery'),
                        jidelnicekPage: routingService.getSingleton('meal'),
                        oSkolcePage: mainMenuItemService.findPageForMainMenuItemWithSlug('o_skolce')])
    }
}
