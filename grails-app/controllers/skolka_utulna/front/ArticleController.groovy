package skolka_utulna.front

import skolka_utulna.ArticleService
import frod.routing.service.PageService
import frod.routing.domain.Page
import skolka_utulna.Website
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.Article
import skolka_utulna.MainMenuItem
import skolka_utulna.MenuItem
import skolka_utulna.WebsiteService

class ArticleController {

    ArticleService articleService

    WebsiteService websiteService

    def index() {
        Page page = Page.get(params.pageId)
        MenuItem menuItem = MenuItem.findByPage(page)
        Website website = websiteService.getWebsite(page)
        Article article = Article.findByPage(page)
        return new ModelAndView("/$website.slug/article",
                [article: article, website: website])
    }
}
