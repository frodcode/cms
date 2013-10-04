package skolka_utulna.admin

import grails.converters.JSON
import skolka_utulna.Website
import skolka_utulna.MainMenuItemService
import skolka_utulna.Article
import frod.routing.domain.Page
import frod.routing.service.PageCommand
import frod.utils.commandObject.CommandObjectService
import frod.routing.service.PageService
import skolka_utulna.ArticleService

class ArticleAdminController {

    static namespace = 'admin'

    static layout = "admin"

    ArticleService articleService

    CommandObjectService commandObjectService

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
        Website website = getWebsite()
        if (params.id) {
            Page page = Page.get(params.id)
            if (!page) {
                response.sendError(404)
                return
            }
            articleService.edit(params.id, params.headline, params.slug, params.text)
            flash.message = 'Článek změněn'
        }
        redirect(action: 'index', params: [websiteSlug: website.slug])
    }

}
