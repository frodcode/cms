package skolka_utulna

import frod.routing.service.PageCommand
import frod.routing.domain.Page
import frod.routing.service.PageService

class ArticleService {

    PageService pageService

    def create(ArticleCommand articleCommand, PageCommand pageCommand) {
        Page page = pageService.createPage(pageCommand)
        Article article = new Article()
        article.properties = articleCommand.properties
        article.page = page
        article.save(flush:true)
        return article
    }
}
