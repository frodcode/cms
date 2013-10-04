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
        def homepageNews = newsService.findLast(grailsApplication.config.skolka.homepageNewsCount, website)
        def newsPage = websiteService.findViaMenuItemPageByWebsiteAndPageTypeSlug(website, 'news')
        def fotogaleriePage = websiteService.findViaMainMenuPageByWebsiteAndPageTypeSlug(website, 'gallery')
        def jidelnicekPage = websiteService.findViaMainMenuPageByWebsiteAndPageTypeSlug(website, 'meal')

        Article homepageArticle = Article.findByPage(page)
        return new ModelAndView("/$website.slug/homepage",
                [homepageArticle: homepageArticle,
                        website: website,
                        naseTridyPage: mainMenuItemService.findBySlugAndWebsite('nase_tridy', website).page,
                        fotogaleriePage: fotogaleriePage,
                        jidelnicekPage: jidelnicekPage,
                        oSkolcePage: mainMenuItemService.findBySlugAndWebsite('o_skolce', website).page,
                        homepageNews: homepageNews,
                        newsPage: newsPage]
        )
    }
}
