package skolka_utulna

import frod.routing.service.PageCommand
import frod.routing.domain.Page
import frod.routing.service.PageService
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class ArticleService {

    PageService pageService

    MenuItemService menuItemService

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

    public def findAllChildrenFor(Article article) {
        def pages = Page.find("FROM Page WHERE parent = :parent", [parent: article.page])
        return Article.find("FROM  Article WHERE page IN (:pages) ORDER BY position ASC", [pages: pages])
    }



}
