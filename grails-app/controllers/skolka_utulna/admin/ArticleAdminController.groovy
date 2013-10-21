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
import org.springframework.web.servlet.ModelAndView
import skolka_utulna.ArticleCommand
import skolka_utulna.ArticleStatusEnum
import frod.routing.domain.HttpMethodEnum
import frod.routing.domain.PageType
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.UrlTypeEnum
import skolka_utulna.MenuItemCommand
import skolka_utulna.MainMenuItem
import skolka_utulna.MenuItem

class ArticleAdminController {

    static namespace = 'admin'

    static layout = "admin"

    ArticleService articleService

    CommandObjectService commandObjectService

    MainMenuItemService mainMenuItemService

    def messageSource

    private def getWebsite() {
        return Website.findBySlug(params.websiteSlug)
    }

    def index() {
        Website website = getWebsite()

        def allMainMenus = mainMenuItemService.findAllArticleBasedByWebsite(website)

        [allMainMenus: allMainMenus, website: website]
    }

    def saveSort() {
        def data = JSON.parse(params.data)
        def items = []
        data.each {
            MenuItem item = MenuItem.get(it.id)
            item.position = it.position
            items.add(item)
        }
        items.each {item->
            if (items.last() == item) {
                item.save(flush:false)
            } else {
                item.save(flush:false)
            }
        }
        render([status: 'ok'] as JSON)
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

    def newArticle() {
        cache false
        def errors = []
        def parentId = params.id
        def article = new Article()
        if (request.post) {
            Page parentPage = Page.findById(params.newParentId)
            MainMenuItem mainMenuItem = MainMenuItem.findByPage(parentPage)

            ArticleCommand articleCommand = commandObjectService.create(ArticleCommand, [:])
            articleCommand.headline = params.headline ? params.headline : null
            articleCommand.text = params.text ? params.text : null
            articleCommand.published = new Date()
            articleCommand.status = ArticleStatusEnum.PUBLISHED

            MenuItemCommand menuItemCommand = commandObjectService.create(MenuItemCommand, [:]);
            menuItemCommand.mainMenuItemId = mainMenuItem.id
            menuItemCommand.putAfterId = mainMenuItem.menuItems.last().id
            menuItemCommand.title = params.headline

            PageCommand pageCommand = commandObjectService.create(PageCommand, [:])
            pageCommand.domainId = website.homepage.domain.id
            pageCommand.httpMethod = HttpMethodEnum.GET
            pageCommand.langPart = ''
            pageCommand.pageTypeId = PageType.findBySlug('article').id
            pageCommand.parentId = Long.parseLong(params.newParentId)
            pageCommand.requestType = RequestTypeEnum.REGULAR
            pageCommand.urlPart = '/'+params.slug
            pageCommand.urlType = UrlTypeEnum.FROM_PARENT

            pageCommand.validate()
            menuItemCommand.validate()
            articleCommand.validate()

            if (!pageCommand.hasErrors() && !menuItemCommand.hasErrors() && !articleCommand.hasErrors()) {
                articleService.create(articleCommand, menuItemCommand, pageCommand)
                flash.message = 'Článek vytvořen'
                redirect(action: 'index', params: [websiteSlug: website.slug])
            } else {
                errors += articleCommand.getErrors().allErrors.collect{messageSource.getMessage(it,null)}
                errors += menuItemCommand.getErrors().allErrors.collect{messageSource.getMessage(it,null)}
                errors += pageCommand.getErrors().allErrors.collect{messageSource.getMessage(it,null)}
                bindData(article, articleCommand)
            }
        }
        return new ModelAndView("/articleAdmin/edit",
                [website: website, errors:errors, parentId: parentId, article: article]
        )
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
