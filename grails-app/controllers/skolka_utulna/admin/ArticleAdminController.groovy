package skolka_utulna.admin

import grails.converters.JSON
import skolka_utulna.Website
import skolka_utulna.MainMenuItemService
import skolka_utulna.Article
import frod.routing.domain.Page

class ArticleAdminController {

    static namespace = 'admin'

    static layout = "admin"

    MainMenuItemService mainMenuItemService

    private def getWebsite() {
        return Website.findBySlug(params.websiteSlug)
    }

    def index() {
        Website website = getWebsite()

        def allMainMenus = mainMenuItemService.findAllArticleBasedByWebsite(website)

        [allMainMenus: allMainMenus, website: website]
    }

    def edit() {
        Website website = getWebsite()
        Page page = Page.get(params.id)
        if (!page) {
            response.sendError(404)
            return
        }
        Article article = Article.findByPage(page);
        assert article.page

        [articleData: article as JSON, article: article, website: website]
    }

    def save() {
        render([status:'ok'] as JSON)
    }
}
