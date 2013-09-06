package skolka_utulna.front

import skolka_utulna.ArticleService
import frod.routing.service.PageService
import frod.routing.domain.Page
import skolka_utulna.Website
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.Article
import skolka_utulna.MainMenuItem
import skolka_utulna.MenuItem

class ArticleController {

    ArticleService articleService

    def index() {
        Page page = Page.get(params.pageId)
        MenuItem menuItem = MenuItem.findByPage(page)
        Website website = menuItem.mainMenuItem.website
        Article article = Article.findByPage(page)
        return new ModelAndView("/$website.slug/article",
                [article: article])
    }
}
