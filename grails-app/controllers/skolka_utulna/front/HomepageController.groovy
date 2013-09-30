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
import skolka_utulna.news.NewsService
import skolka_utulna.MenuItem

class HomepageController {

    WebsiteService websiteService
    MainMenuItemService mainMenuItemService
    RoutingService routingService

    NewsService newsService

    def index() {
        Page page = Page.get(params.pageId)
        Website website = Website.findByHomepage(page)
        def homepageNews = newsService.findLast(grailsApplication.config.skolka.homepageNewsCount)

//        def newsMenuItem = MenuItem.where {
//            page.pageType.slug == 'newsPageType' && mainMenuItem.website == website
//        }.find()
        def newsMenuItem = MenuItem.find("FROM MenuItem m JOIN FETCH m.page p JOIN FETCH p.pageType pt JOIN FETCH m.mainMenuItem mmi WHERE pt.slug = :pageTypeSlug AND mmi.website = :website", [pageTypeSlug: 'news', website: website])
        dump(newsMenuItem)
        def newsPage = newsMenuItem?.page

        Article homepageArticle = Article.findByPage(page)
        return new ModelAndView("/$website.slug/homepage",
                [homepageArticle: homepageArticle,
                        website: website,
                        naseTridyPage: mainMenuItemService.findPageForMainMenuItemWithSlug('nase_tridy'),
                        fotogaleriePage: routingService.getSingleton('gallery'),
                        jidelnicekPage: routingService.getSingleton('meal'),
                        oSkolcePage: mainMenuItemService.findPageForMainMenuItemWithSlug('o_skolce'),
                        homepageNews: homepageNews,
                        newsPage: newsPage]
        )
    }
}
