package skolka_utulna

import frod.routing.domain.Page

/**
 * User: freeman
 * Date: 19.8.13
 */
@grails.validation.Validateable
class ArticleCommand {

    String headline

    String text

    Date published

    Integer menuItemId

    ArticleStatusEnum status = ArticleStatusEnum.DRAFT

    static constraints = {
        importFrom Article
    }

}

