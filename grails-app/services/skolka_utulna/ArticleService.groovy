package skolka_utulna

import frod.routing.service.PageCommand
import frod.routing.domain.Page
import frod.routing.service.PageService
import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod

class ArticleService {

    PageService pageService

    def create(ArticleCommand articleCommand, PageCommand pageCommand) {
        Page page = pageService.createPage(pageCommand)
        Article article = new Article()

        //BindDynamicMethod bind = new BindDynamicMethod()
        //def args =  [ page, articleCommand.properties, [exclude:['page']] ] // for example
        //bind.invoke( page, 'bind', (Object[])args)
        article.properties = articleCommand.properties
        article.page = page
        article.save(flush:true)
        return article
    }
}
