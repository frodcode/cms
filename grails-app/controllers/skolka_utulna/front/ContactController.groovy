package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.MainMenuItem
import skolka_utulna.Website
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.Article

class ContactController {

    def index() {
        Page page = Page.get(params.pageId)
        MainMenuItem mainMenuItem = MainMenuItem.findByPage(page)
        Website website = mainMenuItem.website
        Article article = Article.findByPage(page)
        return new ModelAndView("/$website.slug/contact", [website: website, article: article])
    }
}
