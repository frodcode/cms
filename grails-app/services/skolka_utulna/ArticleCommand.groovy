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

    ArticleStatusEnum status = ArticleStatusEnum.DRAFT

    static constraints = {
        // todo dodelat
        importFrom Article
    }

}

