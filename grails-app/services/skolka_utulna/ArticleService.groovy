package skolka_utulna

import frod.routing.service.PageCommand
import frod.routing.domain.Page
import frod.routing.service.PageService
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod
import frod.utils.commandObject.CommandObjectService

class ArticleService {

    PageService pageService

    MenuItemService menuItemService

    CommandObjectService commandObjectService

    def create(ArticleCommand articleCommand, MenuItemCommand menuItemCommand, PageCommand pageCommand) {
        Page page = pageService.createPage(pageCommand)
        MenuItem menuItem = menuItemService.create(menuItemCommand)
        menuItem.page = page

        Article article = new Article()
        article.properties = articleCommand.properties
        article.menuItem = menuItem
        article.page = page

        if (!articleCommand.published && articleCommand.status == ArticleStatusEnum.PUBLISHED) {
            article.publish()
        }
        page.save()
        menuItem.save()
        article.save(flush: true)
        return article
    }

    def edit(def pageId, def name, def slug, String text) {
        Page page = Page.get(pageId);
        Article article = Article.findByPage(page)

        PageCommand pageCommand = commandObjectService.create(PageCommand)
        pageCommand.domainId = page.domain.id
        pageCommand.httpMethod = page.httpMethod
        pageCommand.langPart = page.langPart
        pageCommand.pageTypeId = page.pageType.id
        pageCommand.parentId = page.parent.id
        pageCommand.requestType = page.requestType
        pageCommand.urlPart = '/'+slug
        pageCommand.urlType = page.urlType
        pageCommand.id = page.id

        def item;
        item = MenuItem.findByPage(page)
        if (!item) {
            item = MainMenuItem.findByPage(page)
        }
        if (!item) {
            throw new IllegalArgumentException(sprinf('Cannot find menu item for page "%s"', page.url))
        }
        item.title = name
        pageService.editPage(pageCommand)
        article.headline = name
        article.text = text
        article.save(flush: true)
    }

    public Article findArticleByPageId(Number pageId)
    {
        Page page = Page.get(pageId)
        return Article.findByPage(page)
    }

    public Article findParentArticleFor(Number articleId) {
        Article childArticle = Article.get(articleId)
        if (!childArticle) {
            throw new IllegalArgumentException(sprintf('Cannot find parent article for article with id "%s". Article with this ID does not exist', articleId))
        }
        return Article.findByPage(childArticle.page.parent)
    }

    public def deleteByPageId(def pageId)
    {
        Page page = Page.get(pageId)
        Article article = Article.findByPage(page)
        MenuItem menuItem = MenuItem.findByPage(article.page)
        MainMenuItem mainMenuItem = menuItem.mainMenuItem
        article.delete(flush:true)
        menuItem.delete(flush:true)
        mainMenuItem.removeFromMenuItems(menuItem);

        def allItems = mainMenuItem.menuItems.sort{it.position}
        allItems.each { item ->
            item.position = allItems.indexOf(item) + 1
            item.save()
        }
        page.delete(flush:true)
    }



}
