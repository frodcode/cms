package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.MainMenuItem
import skolka_utulna.Website
import skolka_utulna.news.NewsService
import skolka_utulna.MenuItem

class NewsController {

    NewsService newsService

    def grailsApplication

    def index() {
        Page page = Page.get(params.pageId)
        MenuItem menuItem = MenuItem.findByPage(page)
        Website website = menuItem.mainMenuItem.website
        def count = grailsApplication.config.skolka.newsCount;
        def allNews = newsService.findLast(count, website)
        [allNews: allNews, website: website]
    }
}
