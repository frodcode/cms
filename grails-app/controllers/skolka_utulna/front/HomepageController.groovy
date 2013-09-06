package skolka_utulna.front

import frod.routing.domain.Page
import skolka_utulna.Article
import skolka_utulna.WebsiteService
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.Website

class HomepageController {

    WebsiteService websiteService

    def index() {
        Page page = Page.get(params.pageId)
        Website website = websiteService.getWebsiteByForwardURI(request.forwardURI)

        Article homepageArticle = Article.findByPage(page)

        return new ModelAndView("/$website.slug/homepage", [homepageArticle: homepageArticle, website: website])
    }
}
